package com.pavel.vovniuk.dishes.dto;

public class FindDishByPrice {
    private String title;
    private double dishPrice;

    public FindDishByPrice(String title, double dishPrice) {
        this.title = title;
        this.dishPrice = dishPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }
}
