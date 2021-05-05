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
import java.util.List;
import java.util.Optional;

public class UserService {
    private final DaoHelperFactory daoHelperFactory;

    public UserService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            Optional<User> userOptional = dao.findByLoginAndPassword(login, password);
            return userOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getUsers() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            List<User> users = dao.findAll();
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> getUserByLogin(String login) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            Optional<User> user = dao.findByLogin(login);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void disableUser(User user) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            user.setEnabled(false);
            UserDao dao = helper.createUserDao();
            dao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
