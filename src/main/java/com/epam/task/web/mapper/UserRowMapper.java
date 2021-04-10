package com.epam.task.web.mapper;

import com.epam.task.web.entity.Role;
import com.epam.task.web.entity.User;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRowMapper implements RowMapper<User> {

    private final static String ID = "id";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String ROLE = "role";
    private final static String POINTS = "points";
    private final static String MONEY = "money";


    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        BigInteger id = (BigInteger) resultSet.getObject(ID);
        String login = resultSet.getString(LOGIN);
        String password = resultSet.getString(PASSWORD);
        String roleString = resultSet.getString(ROLE);
        Role role = getRoleFromString(roleString);
        double points = resultSet.getDouble(POINTS);
        BigDecimal money = resultSet.getBigDecimal(MONEY);
        return new User(id, login, password, role, points, money);
    }

    private Role getRoleFromString(String text) throws SQLException {
        Optional<Role> roleOptional = Role.fromString(text);
        if (roleOptional.isPresent()) {
            return roleOptional.get();
        } else {
            throw new SQLException("Unknown role");
        }
    }
}
