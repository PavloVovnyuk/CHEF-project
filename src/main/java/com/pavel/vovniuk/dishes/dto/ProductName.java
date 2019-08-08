package com.pavel.vovniuk.dishes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pavel.vovniuk.dishes.entity.Product;

public class ProductName {
    private String name;

    public ProductName() {
    }
    public ProductName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
