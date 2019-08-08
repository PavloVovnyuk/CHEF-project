package com.pavel.vovniuk.dishes.dto;

public class SumPriceAllProductsForDish {

    private String title;
    private double priceSumAllProducts;


    public SumPriceAllProductsForDish(String title, double priceSumAllProducts) {
        this.title = title;
        this.priceSumAllProducts = priceSumAllProducts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPriceSumAllProducts() {
        return priceSumAllProducts;
    }

    public void setPriceSumAllProducts(double priceSumAllProducts) {
        this.priceSumAllProducts = priceSumAllProducts;
    }
}
