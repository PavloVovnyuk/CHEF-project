package com.pavel.vovniuk.dishes.dto;

public class DishPrice {
  private double price;

    public DishPrice() {
    }

    public DishPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
