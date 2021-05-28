package com.epam.task.web.command;

import com.epam.task.web.service.DishService;
import com.epam.task.web.service.MenuService;
import com.epam.task.web.service.UserService;

public class CommandFactory {

    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String CREATE_ORDER = "create-order";
    private final static String LOAD_MENU = "load-menu";
    private final static String ADD_MENU = "add-menu";
    private final static String USERS_CRUD = "users-crud";
    private final static String DISABLE_USER = "disable-user";
    private final static String ADD_TO_CART = "add-to-cart";
    private final static String DELETE_FROM_CART = "delete-from-cart";
    private final static String CART = "cart";
    private final static String DISHES_LIST = "dishesList";
    private final static String PAYMENT = "payment";


    private final static String MAIN = "main";
    private final static String LOGIN_PAGE_COMMAND = "login-page";
    private final static String ADMIN_PAGE_COMMAND = "admin-page";
    private final static String CART_PAGE_COMMAND = "cart-page";


    private final static String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private final static String MAIN_PAGE = "WEB-INF/view/index.jsp";
    private final static String ADMIN_PAGE = "WEB-INF/view/admin-page.jsp";
    private final static String CART_PAGE = "WEB-INF/view/cart.jsp";
    private final static String PAYMENT_PAGE = "WEB-INF/view/payment.jsp";

    public Command create(String type) {
        switch (type) {
            case LOGIN:
                return new LoginCommand(new UserService());
            case MAIN:
                return new ShowPageCommand(MAIN_PAGE);
            case LOGIN_PAGE_COMMAND:
                return new ShowPageCommand(LOGIN_PAGE);
            case ADMIN_PAGE_COMMAND:
                return new ShowPageCommand(ADMIN_PAGE);
            case LOGOUT:
                return new LogoutCommand(MAIN_PAGE);
            case LOAD_MENU:
                return new LoadMenuCommand(new MenuService());
            case CREATE_ORDER:
                return new CreateOrderCommand();
            case ADD_MENU:
                return new AddMenuCommand();
            case CART_PAGE_COMMAND:
                return new CartCommand(new DishService());
            case USERS_CRUD:
                return new UsersCrudCommand(new UserService());
            case DISABLE_USER:
                return new DisableUserCommand(new UserService());
            case ADD_TO_CART:
                return new AddToCartCommand();
            case DELETE_FROM_CART:
                return new DeleteFromCartCommand();
            case DISHES_LIST:
                return new GetDishesByPageCommand(new DishService());
            case PAYMENT:
                return new ShowPageCommand(PAYMENT_PAGE);
            default:
                throw new IllegalArgumentException("Unknown type of Command: " + type);

        }
    }
}
