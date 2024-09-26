package com.epam.task.web.dao;

import com.epam.task.web.connection.ProxyConnection;
import com.epam.task.web.entity.Order;
import com.epam.task.web.mapper.OrderRowMapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

public class OrderDao extends AbstractDao<Order> {

    private static final String TABLE_NAME = "Orders";

    private static final String CREATE = "INSERT INTO Orders (user_id, creation_date, status) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Orders SET user_id = ?, creation_date = ?, status = ? WHERE id = ?";
    private static final String SELECT_BY_CREATION_DATE = "SELECT 8 FROM Orders WHERE creation_date = ?";


    public OrderDao(ProxyConnection connection) {
        super(connection, new OrderRowMapper(), TABLE_NAME);
    }

    @Override
    public void create(Order order) throws DaoException {
        executeUpdate(CREATE,
                order.getUserId(),
                order.getCreationDate(),
                order.getStatus());
    }

    @Override
    public void update(Order order) throws DaoException {
        executeUpdate(UPDATE,
                order.getUserId(),
                order.getCreationDate(),
                order.getStatus(),
                order.getId());
    }

    public Optional<Order> findByCreationDate(Timestamp date) throws DaoException {
        return executeSingleResultQuery(SELECT_BY_CREATION_DATE,date);
    }
}
