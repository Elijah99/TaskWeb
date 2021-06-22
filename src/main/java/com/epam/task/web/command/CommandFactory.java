package com.epam.task.web.command;

import com.epam.task.web.service.DishService;
import com.epam.task.web.service.MenuService;
import com.epam.task.web.service.UserService;

public class CommandFactory {

    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String CREATE_ORDER = "create-order";
    private final static String LOAD_MENU = "load-menu";
    private final static String ADD_DISH_TO_MENU = "add-dish-to-menu";
    private final static String USERS_CRUD = "users-crud";
    private final static String DISABLE_USER = "disable-user";
    private final static String ADD_TO_CART = "add-to-cart";
    private final static String DELETE_FROM_CART = "delete-from-cart";
    private final static String CART = "cart";
    private final static String DISHES_LIST = "dishesList";
    private final static String PAYMENT = "payment";
    private final static String MANAGE_USERS = "manage-users";
    private final static String SAVE_USER = "save-user";

    private final static String MAIN = "main";
    private final static String LOGIN_PAGE_COMMAND = "login-page";
    private final static String MANAGE_USERS_PAGE_COMMAND = "manage-users-page";
    private final static String CART_PAGE_COMMAND = "cart-page";
    private final static String PAYMENT_PAGE_COMMAND = "payment-page";
    private final static String ADD_DISH_PAGE_COMMAND = "add-dish-page";
    private final static String PAYMENT_SUCCESS_PAGE_COMMAND = "payment-success-page";

    private final static String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private final static String MAIN_PAGE = "WEB-INF/view/index.jsp";
    private final static String MANAGE_USERS_PAGE = "WEB-INF/view/manage-users.jsp";
    private final static String CART_PAGE = "WEB-INF/view/cart.jsp";
    private final static String PAYMENT_PAGE = "WEB-INF/view/payment.jsp";
    private final static String ADD_DISH_PAGE = "WEB-INF/view/add-dish.jsp";
    private final static String PAYMENT_SUCCESS_PAGE = "WEB-INF/view/payment-success.jsp";

    public Command create(String type) {
        switch (type) {
            case LOGIN:
                return new LoginCommand(new UserService());
            case MAIN:
                return new ShowPageCommand(MAIN_PAGE);
            case LOGIN_PAGE_COMMAND:
                return new ShowPageCommand(LOGIN_PAGE);
            case MANAGE_USERS:
                return new UsersCrudCommand(new UserService());
            case MANAGE_USERS_PAGE_COMMAND:
                return new ShowPageCommand(MANAGE_USERS_PAGE);
            case LOGOUT:
                return new LogoutCommand(MAIN_PAGE);
            case LOAD_MENU:
                return new LoadMenuCommand(new MenuService());
            case CREATE_ORDER:
                return new CreateOrderCommand();
            case ADD_DISH_PAGE_COMMAND:
                return new ShowPageCommand(ADD_DISH_PAGE);
            case ADD_DISH_TO_MENU:
                return new AddDishToMenuCommand();
            case CART_PAGE_COMMAND:
                return new CartCommand(new DishService());
            case DISABLE_USER:
                return new InvertUserEnable(new UserService());
            case ADD_TO_CART:
                return new AddToCartCommand();
            case DELETE_FROM_CART:
                return new DeleteFromCartCommand();
            case DISHES_LIST:
                return new GetDishesByPageCommand(new DishService());
            case PAYMENT_PAGE_COMMAND:
                return new ShowPageCommand(PAYMENT_PAGE);
            case PAYMENT_SUCCESS_PAGE_COMMAND:
                return new ShowPageCommand(PAYMENT_SUCCESS_PAGE);
            case SAVE_USER:
                return new SaveUserCommand();
            default:
                throw new IllegalArgumentException("Unknown type of Command: " + type);

        }
    }
}
