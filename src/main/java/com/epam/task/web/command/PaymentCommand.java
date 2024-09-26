package com.epam.task.web.command;

import com.epam.task.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentCommand implements Command{

    private final static String MAIN = "main";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResult.redirect(MAIN);
    }
}
