package com.epam.task.web.command;

import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.Menu;
import com.epam.task.web.service.DishService;
import com.epam.task.web.service.MenuService;
import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AddMenuCommand implements Command {

    MenuService menuService = new MenuService();
    DishService dishService = new DishService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String textPrice = request.getParameter("price");
        String textDate = request.getParameter("date");
        Dish dish = new Dish(name,description,new BigDecimal(textPrice));

        Optional<Menu> menuForDate = menuService.getMenuByDate(textDate);

        if (menuForDate.isPresent()) {
            List<Dish> dishes = menuForDate.get().getDishes();
            dishes.add(dish);
        }

        return CommandResult.forward("/controller?command=admin-page");
    }
}
