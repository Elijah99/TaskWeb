package com.epam.task.web.command;

import com.epam.task.web.entity.CartItem;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.Order;
import com.epam.task.web.entity.User;
import com.epam.task.web.service.DishService;
import com.epam.task.web.service.OrderService;
import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AddToCartCommand implements Command {

    private final static String MAIN_PAGE = "controller?command=load-menu";
    private final DishService dishService = new DishService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String idDishString = request.getParameter("idDish");
        BigInteger idDish = BigInteger.valueOf(Long.parseLong(idDishString));
        int quantity = Integer.parseInt(request.getParameter("quantityDishes"));

        Optional<Dish> dish = null;
        try {
            dish = dishService.getDishById(idDish);
            if (dish.isPresent()) {
                HttpSession session = request.getSession();
                addToCart(session, new CartItem(dish.get(), quantity));
                updateCartPrice(request, dish.get().getPrice(), quantity);
            } else {
                throw new ServiceException("Error while add to cart");
            }
        } catch (ServiceException e) {
            return CommandResult.redirect(MAIN_PAGE);
        }

        return CommandResult.forward(MAIN_PAGE);
    }

    private void addToCart(HttpSession session, CartItem itemToAdd) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<CartItem>();
        }
        if (cart.contains(itemToAdd)) {
            int indexOfItem = cart.indexOf(itemToAdd);
            CartItem itemInCart = cart.get(indexOfItem);
            int updatedQuantity = itemInCart.getQuantity() + itemToAdd.getQuantity();
            itemInCart.setQuantity(updatedQuantity);
            cart.set(indexOfItem,itemInCart);
        } else {
            cart.add(itemToAdd);
        }
        session.setAttribute("cart", cart);
    }

    private void updateCartPrice(HttpServletRequest request, BigDecimal price, int quantity) {
        HttpSession session = request.getSession();
        BigDecimal cartPrice = (BigDecimal) session.getAttribute("cartPrice");
        BigDecimal quantityBigDecimal = new BigDecimal(quantity);
        if (cartPrice == null) {
            cartPrice = price.multiply(quantityBigDecimal);
        } else {
            cartPrice = cartPrice.add(price.multiply(quantityBigDecimal));
        }
        session.setAttribute("cartPrice", cartPrice);
    }
}
