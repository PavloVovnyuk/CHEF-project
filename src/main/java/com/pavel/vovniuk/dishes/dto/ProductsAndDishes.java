package com.pavel.vovniuk.dishes.dto;

import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.entity.Product;

public class ProductsAndDishes {
    private Dish dish;
    private Product product;

    public ProductsAndDishes(Dish dish, Product product) {
        this.dish = dish;
        this.product = product;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
