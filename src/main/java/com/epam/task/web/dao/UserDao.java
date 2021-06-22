package com.epam.task.web.dao;

import com.epam.task.web.connection.ProxyConnection;
import com.epam.task.web.entity.User;
import com.epam.task.web.mapper.UserRowMapper;

import java.math.BigInteger;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private static final String TABLE_NAME = "Users";

    private static final String FIND_BY_ID = "SELECT * FROM Users WHERE BINARY id = ?";
    private static final String FIND_BY_LOGIN = "SELECT * FROM Users WHERE BINARY login = ?";
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM Users WHERE BINARY login = ? AND BINARY password = md5(?)";
    private static final String CREATE = "INSERT INTO Users (login, password, role, points, money, isEnabled) VALUES (?, md5(?), ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Users SET login = ?, password = ?, role = ?, points = ?, money = ?, isEnabled = ? WHERE id = ?";


    public UserDao(ProxyConnection connection) {
        super(connection, new UserRowMapper(), TABLE_NAME);
    }

    public Optional<User> findById(BigInteger id) throws DaoException {
        return executeSingleResultQuery(FIND_BY_ID, id);
    }

    public Optional<User> findByLogin(String login) throws DaoException {
        return executeSingleResultQuery(FIND_BY_LOGIN, login);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) throws DaoException {
        return executeSingleResultQuery(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

    @Override
    public void create(User user) throws DaoException {
        executeUpdate(CREATE,
                user.getLogin(),
                user.getPassword(),
                user.getRole().getValue(),
                user.getPoints(),
                user.getMoney(),
                user.isEnabled());
    }

    @Override
    public void update(User user) throws DaoException {
        executeUpdate(UPDATE,
                user.getLogin(),
                user.getPassword(),
                user.getRole().getValue(),
                user.getPoints(),
                user.getMoney(),
                user.isEnabled(),
                user.getId());
    }
}
