package com.epam.task.web.command;

import com.epam.task.web.entity.User;
import com.epam.task.web.service.ServiceException;
import com.epam.task.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Optional;

public class SaveUserCommand implements Command {

    private static final String MANAGE_USERS_PAGE = "controller?command=manage-users";

    private UserService userService = new UserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("username");
        String moneyText = request.getParameter("money");
        String pointsText = request.getParameter("points");

        Optional<User> userOptional = userService.getUserByLogin(username);
        User user = userOptional.get();

        user.setMoney(new BigDecimal(moneyText));
        user.setPoints(Double.parseDouble(pointsText));

        userService.save(user);

        return CommandResult.forward(MANAGE_USERS_PAGE);
    }
}
