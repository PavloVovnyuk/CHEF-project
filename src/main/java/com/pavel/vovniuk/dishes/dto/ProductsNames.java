package com.pavel.vovniuk.dishes.dto;

import java.util.List;

public class ProductsNames {

    private List<String> nameOfProducts;

    public ProductsNames(List<String> names) {
        this.nameOfProducts = names;
    }

    public List<String> getNameOfProducts() {
        return nameOfProducts;
    }

    public void setNameOfProducts(List<String> nameOfProducts) {
        this.nameOfProducts = nameOfProducts;
    }

    public ProductsNames() {


    }
}
