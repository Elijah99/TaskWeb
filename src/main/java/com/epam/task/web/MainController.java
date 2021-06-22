package com.epam.task.web;

import com.epam.task.web.command.Command;
import com.epam.task.web.command.CommandFactory;
import com.epam.task.web.command.CommandResult;
import com.epam.task.web.service.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MainController extends HttpServlet {

    private final CommandFactory factory = new CommandFactory();
    private final static Logger LOGGER = Logger.getLogger(MainController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        process(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String command = request.getParameter("command");
        Command action = factory.create(command);
        try {
            CommandResult commandResult = action.execute(request, response);
            String page = commandResult.getPage();
            boolean isRedirect = commandResult.isRedirect();

            if (isRedirect) {
                response.sendRedirect(page);
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServletException(e);
        }
    }

}

