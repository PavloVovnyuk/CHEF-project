package com.pavel.vovniuk.dishes.controller;

import com.pavel.vovniuk.dishes.dto.*;
import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.entity.Product;
import com.pavel.vovniuk.dishes.service.DishContainsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Controller
public class DishContainsProductController implements Serializable {
    @Autowired
    DishContainsProductService dishContainsProductService;

    @GetMapping("/getAll/dishAndProducts")
    public ResponseEntity<List<ProductsAndDishes>> findAllDishAndProduct() {
        List<ProductsAndDishes> listDishAndProducts = dishContainsProductService.findAllByDishAndProduct();
        return new ResponseEntity<>(listDishAndProducts, HttpStatus.OK);
    }

    @GetMapping("/productsBy/dish/{dishId}")
    public ResponseEntity<List<Product>> findProductsByDish(@PathVariable("dishId") Long id) {
        List<Product> productsList = dishContainsProductService.findAllProductsByDish(id);
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @GetMapping("/dishBy/name")
    public ResponseEntity<Dish> dishWithPrice(ProductName name) {
        Dish dish = dishContainsProductService.findDishByProductNameAndCalculateDishPrice(name);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PostMapping("/dishBy/product")
    public ResponseEntity<List<ProductsAndDishes>> findDishByProducts(@RequestBody Product product) {
        List<ProductsAndDishes> dishList = dishContainsProductService.findDishByProduct(product);
        return new ResponseEntity<>(dishList, HttpStatus.OK);
    }

    @PostMapping("/dishBy/productName")
    public ResponseEntity<Dish> findDishByProductName(@RequestBody ProductName name) {
        Dish dish = dishContainsProductService.findAllByProductName(name);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PostMapping("/dishesBy/productsName/price")
    public ResponseEntity<Set<Dish>> findDishesByProducts(@RequestBody DishByNamesAndPrice listNamesAndPrice) {
        System.out.println(listNamesAndPrice);
        Set<Dish> dishList = dishContainsProductService.findDishContainsProductsByProductNameAndPrice(listNamesAndPrice);
        return new ResponseEntity<>(dishList, HttpStatus.OK);
    }

    // nie wiem czy potrzebna
    @PostMapping("/productstoby")
    public ResponseEntity<List<Product>> productsToByu(@RequestBody DishByNamesAndPrice listNamesAndPrice) {
        List<Product> productsListToByu = dishContainsProductService.productsToBy(listNamesAndPrice);
        return new ResponseEntity<>(productsListToByu, HttpStatus.OK);
    }

    @GetMapping("/productsBy/Dish/{dishId}")
    public ResponseEntity<List<ProduktTitleDishName>> findAllProductForDish(@PathVariable("dishId") Long id) {
        List<ProduktTitleDishName> productsByDish = dishContainsProductService.findProductsByDish(id);
        return new ResponseEntity<>(productsByDish, HttpStatus.OK);
    }

    @GetMapping("/dishPriceBy/{dishId}")
    public ResponseEntity<List<SumPricesAllProductsForDish>> fullPriceForDish(@PathVariable("dishId") Long id) {
        List<SumPricesAllProductsForDish> priceAllProductsForDishes = dishContainsProductService.priceAllProductsForDish(id);
        return new ResponseEntity<>(priceAllProductsForDishes, HttpStatus.OK);
    }
}
