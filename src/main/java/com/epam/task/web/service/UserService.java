package com.epam.task.web.service;

import com.epam.task.web.connection.ConnectionPool;
import com.epam.task.web.dao.DaoException;
import com.epam.task.web.dao.DaoHelper;
import com.epam.task.web.dao.DaoHelperFactory;
import com.epam.task.web.dao.UserDao;
import com.epam.task.web.entity.Role;
import com.epam.task.web.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private final DaoHelperFactory daoHelperFactory;

    public UserService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            Optional<User> userOptional = dao.findByLoginAndPassword(login, password);
            helper.endTransaction();
            return userOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
