package com.pavel.vovniuk.dishes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class DishContainsProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Dish dish;

    @JsonIgnore
    @ManyToOne
    private Product product;

    public DishContainsProduct() {
    }

    public DishContainsProduct(Dish dish, Product product) {
        this.dish = dish;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "DishContainsProduct{" +
                "id=" + id +
                ", dish=" + dish +
                ", product=" + product +
                '}';
    }
}
