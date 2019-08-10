package com.pavel.vovniuk.dishes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_array")
public class Product {
    @Id
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_title")
    private String name;
    @Column(name = "product_price")
    private double price;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<DishContainsProduct> dishContainsProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DishContainsProduct> getDishContainsProducts() {
        return dishContainsProducts;
    }

    public void setDishContainsProducts(List<DishContainsProduct> dishContainsProducts) {
        this.dishContainsProducts = dishContainsProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", dishContainsProducts=" + dishContainsProducts +
                '}';
    }
}
