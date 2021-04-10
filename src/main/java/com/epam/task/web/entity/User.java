package com.epam.task.web.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class User extends Entity {
    private String login;
    private String password;
    private Role role;
    private double points;
    private BigDecimal money;

    public User(BigInteger id, String login, String password, Role role, double points, BigDecimal money) {
        super.setId(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.points = points;
        this.money = money;
    }

    public User() {
    }

    public User(String login, String password, Role role, double points, BigDecimal money) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.points = points;
        this.money = money;
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
}
