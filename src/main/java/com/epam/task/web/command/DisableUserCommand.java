package com.epam.task.web.command;

import com.epam.task.web.entity.User;
import com.epam.task.web.service.ServiceException;
import com.epam.task.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class DisableUserCommand implements Command {

    private final static String ADMIN_PAGE = "controller?command=admin-page";

    UserService service;


    public DisableUserCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String login = request.getParameter("login");
        Optional<User> user = service.getUserByLogin(login);
        if (user.isPresent()) {
            service.disableUser(user.get());
        }
        List<User> users = service.getUsers();
        request.getSession().setAttribute("users", users);

        return CommandResult.redirect(ADMIN_PAGE);
    }
}
