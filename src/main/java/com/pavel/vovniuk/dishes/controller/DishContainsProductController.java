package com.pavel.vovniuk.dishes.controller;

//import com.pavel.vovniuk.dishes.dto.SumPriceAllProductsForDish;

import com.pavel.vovniuk.dishes.dto.DishByNameAndPrice;
import com.pavel.vovniuk.dishes.dto.ProductName;
import com.pavel.vovniuk.dishes.dto.ProductsAndDish;
//import com.pavel.vovniuk.dishes.dto.ProduktTitleDishName;
//import com.pavel.vovniuk.dishes.service.DishContainsProductService;
import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.entity.Product;
import com.pavel.vovniuk.dishes.repository.DishRepository;
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
    DishRepository dishRepository;


    @GetMapping("/getalldishandproducts")
    public ResponseEntity<List<ProductsAndDish>> findAllDishAndProduct() {
        List<ProductsAndDish> listDishAndProducts = dishContainsProductService.findAllByDishAndProduct();
        return new ResponseEntity<>(listDishAndProducts, HttpStatus.OK);
    }

    @GetMapping("/findAllProductsByDish/{dishId}")
    public ResponseEntity<List<Product>> findProductsByDish(@PathVariable("dishId") Long id) {
        List<Product> productsList = dishContainsProductService.findAllProductsByDish(id);
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @GetMapping("dishprice/{dishId}")
    public ResponseEntity<Dish> dishWithPrice(@PathVariable("dishId") Long id) {
        Dish dish = dishContainsProductService.getDishWithPrice(id);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PostMapping("finddishbyproducts")
    public ResponseEntity<List<ProductsAndDish>> findDishByProducts(@RequestBody Product product) {
        List<ProductsAndDish> dishList = dishContainsProductService.findDishByProduct(product);
        return new ResponseEntity<>(dishList, HttpStatus.OK);
    }

    @PostMapping("/dishbyproductname")
    public ResponseEntity<Dish> findDishByProductName(@RequestBody ProductName name) {
        Dish dish = dishContainsProductService.findAllByProductName(name);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PostMapping("/finddishesbyproducts")
    public ResponseEntity<Set<Dish>> findDishesByProducts(@RequestBody DishByNameAndPrice listNamesAndPrice){

        System.out.println(listNamesAndPrice);
        Set<Dish> dishList = dishContainsProductService.findDishContainsProductsByProductNameAndPrice(listNamesAndPrice);
        return new ResponseEntity<>(dishList, HttpStatus.OK);
    }
    @PostMapping("/productstoby")
    public ResponseEntity<List<Product>> productsToByu(@RequestBody DishByNameAndPrice listNamesAndPrice){
        List<Product> productsListToByu = dishContainsProductService.productsToBy(listNamesAndPrice);
        return  new ResponseEntity<>(productsListToByu, HttpStatus.OK);
    }


    //Wyszukiwanie danych za pomocą Query

//    @GetMapping("/findProductsByDish/{dishId}")
//    public ResponseEntity<List<ProduktTitleDishName>> findAllProductForDish(@PathVariable("dishId") Long id) {
//        List<ProduktTitleDishName> productsByDish = dishContainsProductService.findProductsByDish(id);
//        return new ResponseEntity<>(productsByDish, HttpStatus.OK);
//    }
//
//    @GetMapping("/fullpricefordish/{dishId}")
//    public ResponseEntity<List<SumPriceAllProductsForDish>> fullPriceForDish(@PathVariable("dishId") Long id) {
//        List<SumPriceAllProductsForDish> priceAllProductsForDishes = dishContainsProductService.priceAllProductsForDish(id);
//        return new ResponseEntity<>(priceAllProductsForDishes, HttpStatus.OK);
//    }
//
//    @GetMapping("/getalldishesbyprice/{dishPrice}")
//    public ResponseEntity<List<FindDishByPrice>> allDishByPrice(@PathVariable("dishPrice") double price) {
//        List<FindDishByPrice> allDishByPrice = dishContainsProductService.findAllDishByPrice(price);
//        return new ResponseEntity<>(allDishByPrice, HttpStatus.OK);
//    }
}
