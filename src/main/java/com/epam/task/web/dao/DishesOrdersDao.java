package com.epam.task.web.dao;

import com.epam.task.web.connection.ProxyConnection;
import com.epam.task.web.entity.DishesOrders;
import com.epam.task.web.mapper.DishesOrdersRowMapper;
import com.epam.task.web.mapper.RowMapper;

public class DishesOrdersDao extends AbstractDao<DishesOrders> {

    private static final String TABLE_NAME = "Dishes_Orders";
    private static final String INSERT = "INSERT INTO Dishes_Orders(order_id, dish_id, number) VALUES(?, ?, ?)";

    public DishesOrdersDao(ProxyConnection connection) {
        super(connection, new DishesOrdersRowMapper(), TABLE_NAME);
    }

    @Override
    public void create(DishesOrders entity) throws DaoException {
        executeUpdate(INSERT,
                entity.getIdOrder(),
                entity.getIdDish(),
                entity.getNumber());
    }

    @Override
    public void update(DishesOrders entity) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
