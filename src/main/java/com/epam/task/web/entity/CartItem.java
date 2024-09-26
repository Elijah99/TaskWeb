package com.epam.task.web.entity;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return dish.equals(cartItem.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dish);
    }
}
