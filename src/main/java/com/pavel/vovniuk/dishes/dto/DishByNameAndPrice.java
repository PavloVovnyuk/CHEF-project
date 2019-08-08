package com.pavel.vovniuk.dishes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DishByNameAndPrice {


    private List<ProductName> name;
    private double price;

    public DishByNameAndPrice(){
    }

    public DishByNameAndPrice(List<ProductName> name, double price) {
        this.name = name;
        this.price = price;
    }

    public List<ProductName> getName() {
        return name;
    }

    public void setName(List<ProductName> name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DishByNameAndPrice{" +
                "name=" + name +
                ", price=" + price +
                '}';
    }
}
