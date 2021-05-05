package com.epam.task.web.command;

import com.epam.task.web.service.MenuService;
import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMenuCommand implements Command {

    MenuService service = new MenuService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        return CommandResult.forward("/controller?command=admin-page");
    }
}
