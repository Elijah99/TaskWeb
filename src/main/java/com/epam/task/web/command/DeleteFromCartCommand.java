package com.epam.task.web.command;

import com.epam.task.web.entity.CartItem;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.service.DishService;
import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        if (session.getAttribute("cart") == null) {
            cart = new ArrayList<CartItem>();
        }

        cart.removeIf(item -> item.getDish().getId().equals(idDish));

        return CommandResult.forward(CART_PAGE);
    }

}
