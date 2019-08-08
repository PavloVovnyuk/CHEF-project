package com.pavel.vovniuk.dishes.controller;

import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.repository.DishRepository;
import com.pavel.vovniuk.dishes.service.DishesService;
import jdk.jfr.Category;
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
    //    dish/

    @GetMapping("/alldishes")
    public ResponseEntity<List<Dish>> allDishes() {
        dishesList = dishesService.findDishAll();
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    // URL: ../dishtitle/
    @GetMapping("/findbydishestitle/{title}")
    public ResponseEntity<List<Dish>> findDishesByTitle(@PathVariable("title") String title) {
        dishesList = dishesService.findDishByTitle(title);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    /**
     * method finds dishes by productas
     */
    @GetMapping("/findbydishescategory/{dishesCategory}")
    public ResponseEntity<List<Dish>> findByCategory(@PathVariable("dishesCategory") String dishesCategory) {
        dishesList = dishesService.findByDishesCategory(dishesCategory);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    @GetMapping("/findbydishesid/{id}")
    public ResponseEntity<Dish> findDishById(@PathVariable("id") Long id) {
        Optional<Dish> dishOptional = dishesService.findById(id);
        Dish dish = dishOptional.orElse(null);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @GetMapping("/deletebyid/{id}")
    public ResponseEntity<List<Dish>> deleteDishById(@PathVariable("id") Long id) {
        dishesService.deleteById(id);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }
    @PostMapping("/setdish")
    public ResponseEntity<List<Dish>> setTask(@RequestBody Dish dish) {
        dish= dishRepository.save(dish);
        dishesList.add(dish);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }

    @GetMapping("/filtruj/{title}/{dishesCategory}")
    public ResponseEntity<List<Dish>> filtruj(@PathVariable("title") String title,
                                              @PathVariable("dishesCategory") String dishesCategory) {
        dishesList = dishRepository.findDishByUsingFilters(title, dishesCategory);
        return new ResponseEntity<>(dishesList, HttpStatus.OK);
    }
}

