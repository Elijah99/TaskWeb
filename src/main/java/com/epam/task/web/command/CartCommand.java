package com.epam.task.web.command;

import com.epam.task.web.entity.CartItem;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.Menu;
import com.epam.task.web.service.DishService;
import com.epam.task.web.service.MenuService;
import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartCommand implements Command {

    private final DishService dishService;
    private final static String CART_PAGE = "WEB-INF/view/cart.jsp";

    public CartCommand(DishService dishService) {
        this.dishService = dishService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        List<CartItem> items = (List<CartItem>) session.getAttribute("cart");
        BigDecimal cartPrice = new BigDecimal(0);
        if (items != null) {
            for (CartItem item : items) {
                Dish dish = item.getDish();
                BigDecimal dishPrice = dish.getPrice();
                BigDecimal quantity = new BigDecimal(item.getQuantity());
                cartPrice = cartPrice.add(dishPrice.multiply(quantity));
            }
        }
        request.setAttribute("cartDishes", items);
        request.setAttribute("cartPrice", cartPrice);
        return CommandResult.forward(CART_PAGE);
    }

}
