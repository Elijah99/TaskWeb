package com.epam.task.web.entity;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public class Order extends Entity {

    public static final String TABLE_NAME = "Orders";

    private BigInteger userId;
    private Timestamp creationDate;
    private Timestamp orderDate;
    private OrderStatus status;
    private PaymentType paymentType;
    private double rating;
    private String comment;
    private List<CartItem> dishes;

    public Order() {
    }

    public Order(BigInteger userId, Timestamp creationDate, List<CartItem> dishes) {
        this.userId = userId;
        this.creationDate = creationDate;
        this.dishes = dishes;
    }

    public Order(BigInteger id, BigInteger userId, Timestamp creationDate,
                 Timestamp orderDate, OrderStatus status, PaymentType paymentType,
                 double rating, String comment) {
        super(id);
        this.userId = userId;
        this.creationDate = creationDate;
        this.orderDate = orderDate;
        this.status = status;
        this.paymentType = paymentType;
        this.rating = rating;
        this.comment = comment;
    }

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

    public List<CartItem> getDishes() {
        return dishes;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setDishes(List<CartItem> dishes) {
        this.dishes = dishes;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
}
