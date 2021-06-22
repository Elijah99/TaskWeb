package com.epam.task.web.command;

import com.epam.task.web.entity.CartItem;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.Order;
import com.epam.task.web.entity.User;
import com.epam.task.web.service.DishService;
import com.epam.task.web.service.OrderService;
import com.epam.task.web.service.ServiceException;
import com.epam.task.web.service.UserService;

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

public class CreateOrderCommand implements Command {

    private final static String PAYMENT_SUCCESS_PAGE = "controller?command=payment-success-page";
    private final static String PAYMENT_PAGE = "controller?command=payment-page";
    private final OrderService orderService = new OrderService();
    private final UserService userService = new UserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Order order = new Order();
        HttpSession session = request.getSession();
        List<CartItem> items = (List<CartItem>) session.getAttribute("cart");
        BigDecimal cartPrice = (BigDecimal) session.getAttribute("cartPrice");

        BigInteger userId = (BigInteger) session.getAttribute("userId");
        BigDecimal userMoney = (BigDecimal) session.getAttribute("userMoney");
        if(userMoney.compareTo(cartPrice)<0){
            request.setAttribute("notEnoughMoney", cartPrice.subtract(userMoney));
            return CommandResult.forward(PAYMENT_PAGE);
        }

        orderService.saveOrder(items, userId);

        userMoney = userMoney.subtract(cartPrice);

        updateSessionValues(session,userMoney);
        updateUser(userId,userMoney);

        return CommandResult.redirect(PAYMENT_SUCCESS_PAGE);
    }

    private void updateUser(BigInteger userId, BigDecimal userMoney) throws ServiceException {
        Optional<User> userOptional = userService.getUserById(userId);
        User user = userOptional.get();
        user.setMoney(userMoney);
        userService.save(user);
    }

    private void updateSessionValues(HttpSession session, BigDecimal userMoney){
        session.setAttribute("cartPrice", null);
        session.setAttribute("cart", null);
        session.setAttribute("userMoney", userMoney);
    }
}
