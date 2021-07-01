package com.epam.task.web.dao;

import com.epam.task.web.connection.ConnectionPool;

import java.sql.SQLException;

public class DaoHelperFactory {

    public DaoHelper create() {
        ConnectionPool pool = ConnectionPool.getInstance();
        return new DaoHelper(pool.getConnection());
    }
}
