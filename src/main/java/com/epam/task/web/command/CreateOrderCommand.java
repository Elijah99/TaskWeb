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
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CreateOrderCommand implements Command {

    private final static String MAIN_PAGE = "controller?command=main";
    private final OrderService orderService = new OrderService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Order order = new Order();
        HttpSession session = request.getSession();
        List<CartItem> items = (List<CartItem>) session.getAttribute("cart");
        List<Dish> dishes = new ArrayList<>();
        for(CartItem item: items){
            dishes.add(item.getDish());
        }
        order.setDishes(dishes);
        order.setCreationDate(new Timestamp(System.currentTimeMillis()));
        order.setUserId((BigInteger) request.getSession().getAttribute("userId"));
        orderService.saveOrder(order);

        return CommandResult.redirect(MAIN_PAGE);
    }


}
