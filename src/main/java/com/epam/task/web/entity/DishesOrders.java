package com.epam.task.web.entity;

import java.math.BigInteger;

public class DishesOrders extends Entity {

    public static final String TABLE_NAME = "DishesOrders";

    private BigInteger idDish;
    private BigInteger idOrder;
    private int number;

    public DishesOrders(BigInteger idOrder, BigInteger idDish) {
        this.idDish = idDish;
        this.idOrder = idOrder;
    }

    public DishesOrders(BigInteger id, BigInteger idOrder, BigInteger idDish) {
        super(id);
        this.idDish = idDish;
        this.idOrder = idOrder;
    }

    public BigInteger getIdDish() {
        return idDish;
    }

    public BigInteger getIdOrder() {
        return idOrder;
    }
}
