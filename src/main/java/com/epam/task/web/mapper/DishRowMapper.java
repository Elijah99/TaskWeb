package com.epam.task.web.mapper;

import com.epam.task.web.entity.Dish;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DishRowMapper implements RowMapper<Dish> {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_PATH = "image_path";
    private static final String PRICE = "price";

    @Override
    public Dish mapRow(ResultSet resultSet) throws SQLException {
        BigInteger id = (BigInteger) resultSet.getObject(ID);
        String name = resultSet.getString(NAME);
        String description = resultSet.getString(DESCRIPTION);
        String imagePath = resultSet.getString(IMAGE_PATH);
        BigDecimal price = resultSet.getBigDecimal(PRICE);
        return new Dish(id, name, description, imagePath, price);
    }
}
