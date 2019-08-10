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

    /**
     *
     * @param title
     * @return danie po tytułu
     */
    public List<Dish> findDishByTitle(String title) {
        List<Dish> dishList = dishRepository.findDishByTitle(title);
        return dishList;
    }

    /**
     *
     * @return wszystkie dania
     */
    public List<Dish> findDishAll() {
        List<Dish> dishList = dishRepository.findAll();
        return dishList;
    }

    /**
     *
     * @param dishesCategory
     * @return danie po kategorji
     */

    public List<Dish> findByDishesCategory(String dishesCategory) {
        List<Dish> dishes = dishRepository.findByDishesCategory(dishesCategory);
        return dishes;
    }

    /**
     *
     * @param id
     * @return danie po id
     */
    public Optional<Dish> findById(Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        return dish;
    }

    /**
     * usuwanie dania po Id
     * @param id
     */
    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }
}
