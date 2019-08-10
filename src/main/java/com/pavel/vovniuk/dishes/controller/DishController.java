package com.pavel.vovniuk.dishes.controller;

import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.repository.DishRepository;
import com.pavel.vovniuk.dishes.service.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DishController {

    @Autowired
    DishesService dishesService;
    @Autowired
    DishRepository dishRepository;

    List<Dish> dishesList;

    /**
     * @return wszystkie dania
     */
    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> allDishes() {
        dishesList = dishesService.findDishAll();
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    /**
     * @param title
     * @return danie po tytulie
     */
    @GetMapping("/dishesBy/{title}")
    public ResponseEntity<List<Dish>> findDishesByTitle(@PathVariable("title") String title) {
        dishesList = dishesService.findDishByTitle(title);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    /**
     * @param dishesCategory
     * @return danie po kategorii
     */
    @GetMapping("/dishesBy/category/{dishesCategory}")
    public ResponseEntity<List<Dish>> findByCategory(@PathVariable("dishesCategory") String dishesCategory) {
        dishesList = dishesService.findByDishesCategory(dishesCategory);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    /**
     * @param id
     * @return danie po Id
     */

    @GetMapping("/dishBy/{id}")
    public ResponseEntity<Dish> findDishById(@PathVariable("id") Long id) {
        Optional<Dish> dishOptional = dishesService.findById(id);
        Dish dish = dishOptional.orElse(null);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    /**
     * @param id
     * @return usuwanie po Id
     */
    @GetMapping("/deleteBy/{id}")
    public ResponseEntity<List<Dish>> deleteDishById(@PathVariable("id") Long id) {
        dishesService.deleteById(id);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    /**
     * @param dish
     * @return nowe danie
     */
    @PostMapping("/add/dish")
    public ResponseEntity<List<Dish>> setTask(@RequestBody Dish dish) {
        dish = dishRepository.save(dish);
        dishesList.add(dish);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    /**
     * @param title
     * @param dishesCategory
     * @return dania po tytulie i kategorii
     */
    @GetMapping("/filtruj/{title}/{dishesCategory}")
    public ResponseEntity<List<Dish>> filtruj(@PathVariable("title") String title,
                                              @PathVariable("dishesCategory") String dishesCategory) {
        dishesList = dishRepository.findDishByUsingFilters(title, dishesCategory);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }
}

