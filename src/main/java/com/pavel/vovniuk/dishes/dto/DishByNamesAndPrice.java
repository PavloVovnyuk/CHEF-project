package com.pavel.vovniuk.dishes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pavel.vovniuk.dishes.entity.Product;

import java.util.List;

public class DishByNamesAndPrice {


    private List<ProductName> name;
    private double price;

    private Product product;

    public DishByNamesAndPrice(){
    }

    public DishByNamesAndPrice(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public DishByNamesAndPrice(List<ProductName> name, double price) {
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
        return "DishByNamesAndPrice{" +
                "name=" + name +
                ", price=" + price +
                '}';
    }
}
