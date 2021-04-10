package com.epam.task.web.dao;

import com.epam.task.web.connection.ProxyConnection;
import com.epam.task.web.entity.Menu;
import com.epam.task.web.mapper.MenuRowMapper;

import java.sql.Date;
import java.util.Optional;

public class MenuDao extends AbstractDao<Menu> {

    private static final String TABLE_NAME = "Menu";
    private static final String SELECT_BY_DATE = "SELECT * FROM Menu WHERE date = ?";

    public MenuDao(ProxyConnection connection) {
        super(connection, new MenuRowMapper(), TABLE_NAME);
    }

    public Optional<Menu> findMenuByDate(Date date) throws DaoException {
        return executeSingleResultQuery(SELECT_BY_DATE, date);
    }

    @Override
    public void create(Menu entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Menu entity) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
