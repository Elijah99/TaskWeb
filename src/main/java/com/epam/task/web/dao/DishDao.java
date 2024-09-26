package com.epam.task.web.dao;

import com.epam.task.web.connection.ProxyConnection;
import com.epam.task.web.entity.Dish;
import com.epam.task.web.mapper.DishRowMapper;

import java.math.BigInteger;
import java.util.List;

public class DishDao extends AbstractDao<Dish> {

    private static final String TABLE_NAME = "Dishes";
    private static final String CREATE = "INSERT INTO Dishes (name, description, price) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Dishes SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String SELECT_BY_MENU_ID =
            "SELECT Dishes.* FROM Dishes INNER JOIN Menu_Dishes AS MD ON Dishes.id = MD.dish_id INNER JOIN Menu M ON MD.menu_id = M.id WHERE menu_id = ?;";

    public DishDao(ProxyConnection connection) {
        super(connection, new DishRowMapper(), TABLE_NAME);
    }

    public List<Dish> findByMenuId(BigInteger id) throws DaoException {
        return executeQuery(SELECT_BY_MENU_ID, id);
    }

    @Override
    public void create(Dish dish) throws DaoException {
        executeUpdate(CREATE,
                dish.getName(),
                dish.getDescription(),
                dish.getPrice());
    }

    @Override
    public void update(Dish dish) throws DaoException {
        executeUpdate(UPDATE,
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getId());
    }

}
