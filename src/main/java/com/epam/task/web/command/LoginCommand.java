package com.epam.task.web.command;

import com.epam.task.web.service.ServiceException;
import com.epam.task.web.service.UserService;
import com.epam.task.web.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService service;
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private final static String LOGIN_PAGE = "controller?command=login-page";
    private final static String MAIN_PAGE = "controller?command=main";

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(final HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        Optional<User> userOptional = service.login(login, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.isEnabled()) {
                request.getSession().setAttribute("userId", user.getId());
                request.getSession().setAttribute("userRole", user.getRole().getValue());
                request.getSession().setAttribute("userLogin", user.getLogin());
                return CommandResult.redirect(MAIN_PAGE);
            } else {
                request.setAttribute("errorMessage", "User is blocked");
                return CommandResult.forward(LOGIN_PAGE);
            }
        } else {
            request.setAttribute("errorMessage", " Invalid username or password");
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

}
