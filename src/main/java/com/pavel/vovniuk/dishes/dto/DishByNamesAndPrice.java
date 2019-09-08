package com.pavel.vovniuk.dishes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pavel.vovniuk.dishes.entity.Product;

import java.util.List;

public class DishByNamesAndPrice {

    // zrobyty string


    private List<String> nameOfProducts;
    private double price;

    public DishByNamesAndPrice() {
    }

    public DishByNamesAndPrice(List<String> nameOfProducts, double price) {
        this.nameOfProducts = nameOfProducts;
        this.price = price;
    }

    public List<String> getNameOfProducts() {
        return nameOfProducts;
    }

    public void setNameOfProducts(List<String> nameOfProducts) {
        this.nameOfProducts = nameOfProducts;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
