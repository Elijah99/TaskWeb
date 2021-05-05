package com.epam.task.web.entity;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

public class Menu extends Entity {

    public static final String TABLE_NAME = "Menu";

    private Date date;
    private List<Dish> dishes;

    public Menu(BigInteger id, Date date, List<Dish> dishes) {
        super(id);
        this.date = date;
        this.dishes = dishes;
    }

    public Menu(BigInteger id, Date date) {
        super(id);
        this.date = date;
    }

    public Menu() {
    }

    public Date getDate() {
        return date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
