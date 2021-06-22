package com.epam.task.web.entity;

import java.math.BigInteger;

public class DishesOrders extends Entity {

    public static final String TABLE_NAME = "DishesOrders";

    private BigInteger idDish;
    private BigInteger idOrder;
    private BigInteger number;

    public DishesOrders(BigInteger idOrder, BigInteger idDish, int number) {
        this.idDish = idDish;
        this.idOrder = idOrder;
        this.number = BigInteger.valueOf(number);
    }

    public DishesOrders(BigInteger id, BigInteger idOrder, BigInteger idDish, BigInteger number) {
        super(id);
        this.idDish = idDish;
        this.idOrder = idOrder;
        this.number = number;
    }

    public BigInteger getIdDish() {
        return idDish;
    }

    public BigInteger getIdOrder() {
        return idOrder;
    }

    public BigInteger getNumber() {
        return number;
    }
}
