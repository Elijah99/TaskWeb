package com.epam.task.web.command;

import com.epam.task.web.entity.CartItem;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.service.DishService;
import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteFromCartCommand implements Command {

    private final static String CART_PAGE = "controller?command=cart-page";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String idDishString = request.getParameter("idDish");
        BigInteger idDish = BigInteger.valueOf(Long.parseLong(idDishString));

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<CartItem>();
        }
        BigDecimal priceOfItem = getPriceOfItem(cart, idDish);
        cart.removeIf(item -> item.getDish().getId().equals(idDish));

        BigDecimal cartPrice = (BigDecimal) session.getAttribute("cartPrice");
        cartPrice = cartPrice.subtract(priceOfItem);
        session.setAttribute("cartPrice", cartPrice);

        return CommandResult.redirect(CART_PAGE);
    }

    private BigDecimal getPriceOfItem(List<CartItem> cart, BigInteger id) {
        for (CartItem item : cart) {
            if (item.getDish().getId().equals(id)) {
                BigDecimal price = item.getDish().getPrice();
                int quantity = item.getQuantity();
                return price.multiply(new BigDecimal(quantity));
            }
        }
        return null;
    }

}
