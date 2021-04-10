package com.epam.task.web.command;

import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.Menu;
import com.epam.task.web.service.MenuService;
import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class LoadMenuCommand implements Command {

    private final MenuService menuService;
    private static final String MAIN_PAGE = "controller?command=main";

    public LoadMenuCommand(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Optional<Menu> currentDateMenu = menuService.getMenuByCurrentDate();
        if (currentDateMenu.isPresent()) {
            List<Dish> dishes = currentDateMenu.get().getDishes();
            request.setAttribute("dishes", dishes);
        }
        return CommandResult.forward(MAIN_PAGE);
    }

}
