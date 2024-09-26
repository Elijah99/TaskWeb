package com.epam.task.web.command;

import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.Menu;
import com.epam.task.web.service.DishService;
import com.epam.task.web.service.MenuService;
import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class AddDishToMenuCommand implements Command {

    MenuService menuService = new MenuService();
    DishService dishService = new DishService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String textPrice = request.getParameter("price");
            System.err.println(name + " | " + new String(description.getBytes(), StandardCharsets.UTF_8) + " | " + textPrice);
        Dish dish = new Dish(name,description,new BigDecimal(textPrice));
        dishService.saveDish(dish);

        Optional<Menu> menuForDate = menuService.getMenu();

        if (menuForDate.isPresent()) {
            List<Dish> dishes = menuForDate.get().getDishes();
            dishes.add(dish);
        }

        return CommandResult.redirect(request.getRequestURI() + "?command=add-dish-page");
    }
}
