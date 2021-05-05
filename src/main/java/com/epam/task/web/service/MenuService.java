package com.epam.task.web.service;

import com.epam.task.web.dao.*;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.Menu;
import com.epam.task.web.entity.User;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class MenuService {
    DaoHelperFactory daoHelperFactory;

    public MenuService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public Optional<Menu> getMenuByDate(String textDate) throws ServiceException{
        Date date = Date.valueOf(textDate);
        return getMenuByDate(date);
    }

    public Optional<Menu> getMenuByDate(Date date) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            MenuDao menuDao = helper.createMenuDao();
            DishDao dishDao = helper.createDishDao();

            Optional<Menu> menuOptional = menuDao.findMenuByDate(date);
            if (menuOptional.isPresent()) {
                BigInteger idMenu = menuOptional.get().getId();
                List<Dish> dishes = dishDao.findByMenuId(idMenu);
                menuOptional.get().setDishes(dishes);
            }
            helper.endTransaction();
            return menuOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Menu> getMenuByCurrentDate() throws ServiceException {
        long time = Calendar.getInstance().getTimeInMillis();
        Date date = new Date(time);
        return getMenuByDate(date);
    }

}
