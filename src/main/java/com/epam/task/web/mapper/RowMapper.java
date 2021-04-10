package com.epam.task.web.mapper;

import com.epam.task.web.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Entity> {
    T mapRow(ResultSet resultSet) throws SQLException;
}
