package com.epam.task.web.dao;


import com.epam.task.web.connection.ProxyConnection;
import com.epam.task.web.entity.Entity;
import com.epam.task.web.mapper.RowMapper;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {

    private static final String SELECT_ALL = "SELECT * FROM %s";
    private static final String SELECT_BY_ID = "SELECT * FROM %s where id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM %s where id = ?";

    private final ProxyConnection connection;
    private final RowMapper<T> mapper;
    private final String tableName;

    public AbstractDao(ProxyConnection connection, RowMapper<T> mapper, String tableName) {
        this.connection = connection;
        this.mapper = mapper;
        this.tableName = tableName;
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; ++i) {
            statement.setObject(i + 1, params[i]);
        }

        return statement;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {

            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.mapRow(resultSet);
                entities.add(entity);
            }

            return entities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Optional<T> executeSingleResultQuery(String query, Object... params) throws DaoException {

        List<T> entities = executeQuery(query, params);

        if (entities.size() > 1) {
            throw new DaoException("There are more than one rows were found: " + entities.size());
        }

        if (entities.size() > 0) {
            return Optional.of(entities.get(0));
        }

        return Optional.empty();
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<T> findAll() throws DaoException {
        String query = String.format(SELECT_ALL, tableName);
        return executeQuery(query);
    }

    @Override
    public void deleteById(BigInteger id) throws DaoException {
        String query = String.format(DELETE_BY_ID, tableName);
        executeUpdate(query, id);
    }

    @Override
    public void save(T entity) throws DaoException {

        if (entity.getId() == null) {
            create(entity);
        } else {
            update(entity);
        }
    }

    public abstract void create(T entity) throws DaoException;

    public abstract void update(T entity) throws DaoException;

    @Override
    public Optional<T> findEntityById(BigInteger id) throws DaoException {
        String query = String.format(SELECT_BY_ID, tableName);
        return executeSingleResultQuery(query, id);
    }
}
