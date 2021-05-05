package com.epam.task.web.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class User extends Entity {

    public static final String TABLE_NAME = "Users";

    private String login;
    private String password;
    private Role role;
    private double points;
    private BigDecimal money;
    private boolean isEnabled;

    public User(BigInteger id, String login, String password, Role role, double points, BigDecimal money, boolean isEnabled) {
        super.setId(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.points = points;
        this.money = money;
        this.isEnabled = isEnabled;
    }

    public User() {
    }

    public User(String login, String password, Role role, double points, BigDecimal money, boolean isEnabled) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.points = points;
        this.money = money;
        this.isEnabled = isEnabled;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public double getPoints() {
        return points;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
