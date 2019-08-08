package com.pavel.vovniuk.dishes.repository;

import com.pavel.vovniuk.dishes.dto.*;
//import com.pavel.vovniuk.dishes.dto.SumPriceAllProductsForDish;
import com.pavel.vovniuk.dishes.entity.DishContainsProduct;
//import com.pavel.vovniuk.dishes.dto.ProduktTitleDishName;
import com.pavel.vovniuk.dishes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DishContainsProductRepository extends JpaRepository<DishContainsProduct, Long> {

    @Query("SELECT NEW com.pavel.vovniuk.dishes.dto.ProductsAndDish (d.dish, d.product) from DishContainsProduct  as d group by d.product")
    List<ProductsAndDish> findAllByDishId();

    List<ProductsAndDish>  findAllByProduct (Product product);

    ProductsAndDish findAllByProductName(String name);

    Set<ProductsAndDish> findDishContainsProductByProductName(String name);



     //      Działające metody

//    @Query("SELECT NEW com.pavel.vovniuk.dishes.dto.ProduktTitleDishName (d.dish.title, d.product.name) from DishContainsProduct as d where d.dish.id = :dishId group by d.product")
//    List<ProduktTitleDishName> findProductsByDish(@Param("dishId") Long id);
//
//    @Query("SELECT NEW com.pavel.vovniuk.dishes.dto.SumPriceAllProductsForDish (d.dish.title, sum(d.product.price)) from DishContainsProduct as d where d.dish.id = :dishId group by d.dish")
//    List<SumPriceAllProductsForDish> SumPriceAllProductsForDish(@Param("dishId") Long id);
//
//    //ta metoda nie do końca działa
//    @Query("SELECT NEW com.pavel.vovniuk.dishes.dto.FindDishByPrice (d.dish.title, sum(d.product.price)) from DishContainsProduct as d WHERE sum(d.product.price) <= :dishPrice group by d.dish")
//    List<FindDishByPrice> findAllDishByPrice(@Param("dishPrice") double price);


}
