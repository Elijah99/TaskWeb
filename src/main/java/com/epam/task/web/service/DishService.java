package com.epam.task.web.service;

import com.epam.task.web.dao.*;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishService {

    private final static int DISHES_ON_PAGE = 3;

    private final DaoHelperFactory daoHelperFactory;

    public DishService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public Optional<Dish> getDishById(BigInteger id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            DishDao dao = helper.createDishDao();
            Optional<Dish> dish = dao.findEntityById(id);
            helper.endTransaction();
            return dish;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Dish> getDishesById(BigInteger... values) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            DishDao dao = helper.createDishDao();
            ArrayList<Dish> dishes = new ArrayList<>();
            for (BigInteger id : values) {
                Optional<Dish> dish = dao.findEntityById(id);
                dish.ifPresent(dishes::add);
            }
            helper.endTransaction();
            return dishes;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Dish> getDishesByPage(long page) throws ServiceException {
        page--;
        if (page < 0) {
            throw new ServiceException("Illegal page");
        }
        long skipped = page * DISHES_ON_PAGE;
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            return dao.getWithLimit(skipped, DISHES_ON_PAGE);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Dish> getAll() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public static int getDishesOnPage() {
        return DISHES_ON_PAGE;
    }

    public void saveDish(Dish dish) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            dao.save(dish);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
