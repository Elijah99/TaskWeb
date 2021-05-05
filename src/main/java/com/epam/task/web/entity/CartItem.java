package com.epam.task.web.entity;

import java.io.Serializable;

public class CartItem implements Serializable,Cloneable {

    private Dish dish;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
