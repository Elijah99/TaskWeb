package com.epam.task.web.service;

import com.epam.task.web.dao.*;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.Menu;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class MenuService {
    DaoHelperFactory daoHelperFactory;

    public MenuService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public Optional<Menu> getMenu() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            DishDao dishDao = helper.createDishDao();

            List<Dish> dishes = dishDao.findAll();
            Optional<Menu> menu = Optional.of(new Menu(dishes));

            helper.endTransaction();
            return menu;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
