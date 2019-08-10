package com.pavel.vovniuk.dishes.repository;

import com.pavel.vovniuk.dishes.dto.*;
import com.pavel.vovniuk.dishes.entity.DishContainsProduct;
import com.pavel.vovniuk.dishes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DishContainsProductRepository extends JpaRepository<DishContainsProduct, Long> {

    @Query("SELECT NEW com.pavel.vovniuk.dishes.dto.ProductsAndDishes (d.dish, d.product) from DishContainsProduct  as d group by d.product")
    List<ProductsAndDishes> findAllByDishId();

    List<ProductsAndDishes> findAllByProduct(Product product);

    ProductsAndDishes findAllByProductName(String name);

    Set<ProductsAndDishes> findDishContainsProductByProductName(String name);

    /**
     * @param id
     * @return wyszukiwanie nazw productów i dania po Id dania
     */
    @Query("SELECT NEW com.pavel.vovniuk.dishes.dto.ProduktTitleDishName (d.dish.title, d.product.name) from DishContainsProduct as d where d.dish.id = :dishId group by d.product")
    List<ProduktTitleDishName> findProductsByDish(@Param("dishId") Long id);

    /**
     * @param id
     * @return nazwy dania i sumę cen na produkty po Id dania
     */
    @Query("SELECT NEW com.pavel.vovniuk.dishes.dto.SumPricesAllProductsForDish (d.dish.title, sum(d.product.price)) from DishContainsProduct as d where d.dish.id = :dishId group by d.dish")
    List<SumPricesAllProductsForDish> SumPriceAllProductsForDish(@Param("dishId") Long id);

}
