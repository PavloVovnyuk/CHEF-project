package com.pavel.vovniuk.dishes.dto;

import com.pavel.vovniuk.dishes.entity.Dish;

import java.util.List;

public class ProductsToBuyAndPriceToSpent {
    private Dish dish;

    private List<String> productsToBuy;

    private Double lackMoney;

    private Double excessMoney;

    private Double notUsedMoney;

    public ProductsToBuyAndPriceToSpent() {
    }

    public ProductsToBuyAndPriceToSpent(Dish dish) {
        this.dish = dish;
    }

    public ProductsToBuyAndPriceToSpent(Double amoneyIsEnough) {
        this.notUsedMoney = amoneyIsEnough;
    }

    public ProductsToBuyAndPriceToSpent(List<String> productsToBuy, Double lackMoney, Double excessMoney) {
        this.productsToBuy = productsToBuy;
        this.lackMoney = lackMoney;
        this.excessMoney = excessMoney;

    }

    public List<String> getProductsToBuy() {
        return productsToBuy;
    }

    public void setProductsToBuy(List<String> productsToBuy) {
        this.productsToBuy = productsToBuy;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Double getLackMoney() {
        return lackMoney;
    }

    public void setLackMoney( Double lackMoney) {
        this.lackMoney = lackMoney;
    }

    public Double getExcessMoney() {
        return excessMoney;
    }

    public void setExcessMoney(Double excessMoney) {
        this.excessMoney = excessMoney;
    }

    public Double getNotUsedMoney() {
        return notUsedMoney;
    }

    public void setNotUsedMoney(Double notUsedMoney) {
        this.notUsedMoney = notUsedMoney;
    }
}


