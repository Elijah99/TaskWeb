package com.epam.task.web.mapper;

import com.epam.task.web.entity.Menu;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuRowMapper implements RowMapper<Menu> {

    private static final String ID = "id";
    private static final String DATE = "date";

    @Override
    public Menu mapRow(ResultSet resultSet) throws SQLException {
        BigInteger id = (BigInteger) resultSet.getObject(ID);
        Date date = resultSet.getDate(DATE);
        return new Menu(id, date);
    }
}
