package com.epam.task.web.service;

import com.epam.task.web.dao.*;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.DishesOrders;
import com.epam.task.web.entity.Order;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private DaoHelperFactory daoHelperFactory;

    public OrderService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public void saveOrder(Order order) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            OrderDao orderDao = helper.createOrderDao();
            orderDao.save(order);
            order = orderDao.findByCreationDate(order.getCreationDate()).get();
            DishesOrdersDao dishesOrdersDao = helper.createDishesOrdersDao();
            List<DishesOrders> dishesOrders = createDishesOrders(order);
            for (DishesOrders value : dishesOrders) {
                dishesOrdersDao.save(value);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private List<DishesOrders> createDishesOrders(Order order) {
        List<DishesOrders> dishesOrdersList = new ArrayList<>();
        List<Dish> dishes = order.getDishes();
        BigInteger idOrder = order.getId();
        dishes.forEach(dish -> {
            DishesOrders dishesOrders = new DishesOrders(idOrder, dish.getId());
            dishesOrdersList.add(dishesOrders);
        });
        return dishesOrdersList;
    }

}
