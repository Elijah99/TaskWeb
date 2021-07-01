package com.epam.task.web.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Dish extends Entity {

    public static final String TABLE_NAME = "Dishes";

    private String name;
    private String description;
    private String imagePath;
    private BigDecimal price;

    public Dish(BigInteger id, String name, String description, String imagePath, BigDecimal price) {
        super(id);
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

    public Dish(String name, String description, String imagePath, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

    public Dish() {
    }

    public Dish(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
