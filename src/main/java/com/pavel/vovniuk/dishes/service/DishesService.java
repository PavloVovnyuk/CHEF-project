package com.pavel.vovniuk.dishes.service;

import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishesService {

    @Autowired
    DishRepository dishRepository;

    public void saveDish(Dish dish) {
        dishRepository.save(dish);
    }

    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }

    public List<Dish> findDishByTitle(String title) {
        List<Dish> dishList = dishRepository.findDishByTitle(title);
        return dishList;
    }

    public List<Dish> findDishAll() {
        List<Dish> dishList = dishRepository.findAll();
        return dishList;
    }

    public List<Dish> findByDishesCategory(String dishesCategory) {
        List<Dish> dishes = dishRepository.findByDishesCategory(dishesCategory);
        return dishes;
    }

    public Optional<Dish> findById(Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        return dish;
    }
}
