package com.epam.task.web.command;

import com.epam.task.web.entity.User;
import com.epam.task.web.service.ServiceException;
import com.epam.task.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersCrudCommand implements Command {

    private final static String MANAGE_USERS_PAGE = "controller?command=manage-users-page";

    UserService service;

    public UsersCrudCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> users = service.getUsers();
        request.getSession().setAttribute("users", users);
        return CommandResult.forward(MANAGE_USERS_PAGE);
    }
}
