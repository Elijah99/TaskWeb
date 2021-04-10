package com.epam.task.web.command;

import com.epam.task.web.dao.DaoHelperFactory;
import com.epam.task.web.service.MenuService;
import com.epam.task.web.service.UserService;

public class CommandFactory {

    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";

    private final static String MAIN = "main";
    private final static String LOGIN_PAGE_COMMAND = "login-page";
    private final static String LOAD_MENU = "load-menu";

    private final static String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private final static String MAIN_PAGE = "WEB-INF/view/index.jsp";


    public Command create(String type) {
        switch (type) {
            case LOGIN:
                return new LoginCommand(new UserService());
            case MAIN:
                return new ShowPageCommand(MAIN_PAGE);
            case LOGIN_PAGE_COMMAND:
                return new ShowPageCommand(LOGIN_PAGE);
            case LOGOUT:
                return new LogoutCommand(MAIN_PAGE);
            case LOAD_MENU:
                return new LoadMenuCommand(new MenuService());
            default:
                throw new IllegalArgumentException("Unknown type of Command: " + type);

        }
    }
}
