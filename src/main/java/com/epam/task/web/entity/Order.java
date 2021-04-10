package com.epam.task.web.entity;

import java.sql.Timestamp;
import java.util.List;

public class Order extends Entity {
    private Timestamp creationDate;
    private Timestamp orderDate;
    private OrderStatus status;
    private PaymentType paymentType;
    private double rating;
    private String comment;

    private List<Dish> dishes;

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
