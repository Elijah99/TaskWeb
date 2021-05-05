package com.epam.task.web.command;

import com.epam.task.web.entity.Dish;
import com.epam.task.web.service.DishService;
import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetDishesByPageCommand implements Command {

    private final DishService service;
    private final static String PAGE_INDEX = "pageIndex";
    private final static String DISHES_LIST = "dishesList";
    private final static String DISHES_ON_PAGE = "DishesOnPage";
    private final static String DISHES_COUNT = "DishesCount";
    private final static String MAIN = "mainPage";

    public GetDishesByPageCommand(DishService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String page = (String)  session.getAttribute(PAGE_INDEX);
        int pageIndex = Integer.parseInt(page);
        List<Dish> vacancyList = service.getDishesByPage(pageIndex);
        request.setAttribute(DISHES_LIST, vacancyList);
        request.setAttribute(DISHES_ON_PAGE, service.getDishesOnPage());
        List<Dish> all = service.getAll();
        request.setAttribute(DISHES_COUNT, all.size());
        return CommandResult.redirect(MAIN);
    }
}
