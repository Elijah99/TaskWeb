package com.epam.task.web.mapper;

import com.epam.task.web.entity.Dish;
import com.epam.task.web.entity.DishesOrders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DishesOrdersRowMapper implements RowMapper<DishesOrders> {

    private static final String ID = "id";
    private static final String ID_ORDER = "id_order";
    private static final String ID_DISH = "id_dish";

    @Override
    public DishesOrders mapRow(ResultSet resultSet) throws SQLException {
        BigInteger id = (BigInteger) resultSet.getObject(ID);
        BigInteger idOrder = (BigInteger) resultSet.getObject(ID_ORDER);
        BigInteger idDish = (BigInteger) resultSet.getObject(ID_DISH);
        return new DishesOrders(id, idDish,idOrder);

    }
}
