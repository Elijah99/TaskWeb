package com.epam.task.web.service;

import com.epam.task.web.dao.*;
import com.epam.task.web.entity.CartItem;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.DishesOrders;
import com.epam.task.web.entity.Order;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private DaoHelperFactory daoHelperFactory;

    public OrderService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public void saveOrder(List<CartItem> cartItems, BigInteger userId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            OrderDao orderDao = helper.createOrderDao();
            Order order = new Order(userId, new Timestamp(System.currentTimeMillis()), cartItems);
            orderDao.save(order);
            BigInteger insertedOrderId = orderDao.getLastInsertedId();
            order.setId(insertedOrderId);

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
        List<CartItem> dishes = order.getDishes();
        BigInteger idOrder = order.getId();
        dishes.forEach(dish -> {
            DishesOrders dishesOrders = new DishesOrders(idOrder, dish.getDish().getId(),dish.getQuantity());
            dishesOrdersList.add(dishesOrders);
        });
        return dishesOrdersList;
    }

}
