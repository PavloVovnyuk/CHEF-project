package com.pavel.vovniuk.dishes.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dish_array")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Long id;
    @Column(name = "dish_title")
    private String title;
    @Column(name = "dish_picture")
    private String picture;
    @Column(name = "dish_category")
    private String dishesCategory;
    @Column(name = "dish_price")
    private Double dishPrice;
    @OneToMany(mappedBy = "dish")
    List<DishContainsProduct> dishContainsProducts;

    public Dish() {
    }

    public Dish(String title, String picture, String dishesCategory, Double dishPrice, List<DishContainsProduct> dishContainsProducts) {
        this.title = title;
        this.picture = picture;
        this.dishesCategory = dishesCategory;
        this.dishPrice = dishPrice;
        this.dishContainsProducts = dishContainsProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDishesCategory() {
        return dishesCategory;
    }

    public void setDishesCategory(String dishesCategory) {
        this.dishesCategory = dishesCategory;
    }

    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public List<DishContainsProduct> getDishContainsProducts() {
        return dishContainsProducts;
    }

    public void setDishContainsProducts(List<DishContainsProduct> dishContainsProducts) {
        this.dishContainsProducts = dishContainsProducts;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", picture='" + picture + '\'' +
                ", dishesCategory='" + dishesCategory + '\'' +
                ", dishPrice=" + dishPrice +
                ", dishContainsProducts=" + dishContainsProducts +
                '}';
    }
}