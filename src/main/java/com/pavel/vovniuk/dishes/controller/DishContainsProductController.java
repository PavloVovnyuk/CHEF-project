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

    /**
     *
     * @return wszystkie dania i produkty
     */
    @GetMapping("/getall/dishesandproducts")
    public ResponseEntity<List<ProductsAndDishes>> findAllDishAndProduct() {
        List<ProductsAndDishes> listDishAndProducts = dishContainsProductService.findAllByDishAndProduct();
        return new ResponseEntity<>(listDishAndProducts, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return produkty po id dania
     */
    @GetMapping("/productsby/dishid/{dishId}")
    public ResponseEntity<Set<Product>> findProductsByDish(@PathVariable("dishId") Long id) {
        Set<Product> productsList = dishContainsProductService.findAllProductsByDish(id);
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    /**
     *
     * @param name
     *@return dania po nazwie productu
     */
    @PostMapping("getdishesby/productname")
    public ResponseEntity<List<Dish>> findDishByProductName(@RequestBody ProductName name) {
        List<Dish> dishesList = dishContainsProductService.findDishesAndProductsByProductName(name);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    /**
     *
     * @param namesObject
     * @return wszystkie dania po liscie nazw produktów
     */
    @PostMapping("getallby/productsnameslist")
    public ResponseEntity<List<Dish>> findAllByProductsNamesList(@RequestBody ProductsNames namesObject) {
        System.out.println(namesObject + " vse tsky jest");
        List<Dish> dishes = dishContainsProductService.findDishesAndProductsByProductsNamesList(namesObject);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
    /**
     *
     * @param productName
     * @return dishes with calculate price
     */
    @PostMapping("/disheswithpriceby/productname")
    public ResponseEntity<List<Dish>> findDishesByProducts(@RequestBody ProductName productName) {
        List<Dish> dishList = dishContainsProductService.findDishByProductNameAndCalculateDishPrice(productName);
        return new ResponseEntity<>(dishList, HttpStatus.OK);
    }
    @PostMapping("/finddishby/productnamesandprice")
    public ResponseEntity<Set<Dish>> findDishByProductNamesAndPrice(@RequestBody DishByNamesAndPrice listOfNamesAndPrice){
        Set<Dish> dishes = dishContainsProductService.findDishContainsProductsByProductNamesListAndPrice(listOfNamesAndPrice);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }




//    Metody z Wykorzystaniem Qery
    /**
     *
     * @param id
     * @return produkty po id dania
     * metoda Qery
     */
    @GetMapping("/productsBy/Dish/{dishId}")
    public ResponseEntity<List<ProduktTitleDishName>> findAllProductForDish(@PathVariable("dishId") Long id) {
        List<ProduktTitleDishName> productsByDish = dishContainsProductService.findProductsByDish(id);
        return new ResponseEntity<>(productsByDish, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return sume cen wszystkich produktów dla dania
     * metoda Qery
     */
    @GetMapping("/dishPriceBy/{dishId}")
    public ResponseEntity<List<SumPricesAllProductsForDish>> fullPriceForDish(@PathVariable("dishId") Long id) {
        List<SumPricesAllProductsForDish> priceAllProductsForDishes = dishContainsProductService.priceAllProductsForDish(id);
        return new ResponseEntity<>(priceAllProductsForDishes, HttpStatus.OK);
    }
}
