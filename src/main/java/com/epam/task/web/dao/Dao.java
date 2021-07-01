package com.epam.task.web.dao;

import com.epam.task.web.entity.Entity;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    List<T> findAll() throws DaoException;

    Optional<T> findEntityById(BigInteger id) throws DaoException;

    void create(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void deleteById(BigInteger id) throws DaoException;

    void save(T entity) throws DaoException;
}
