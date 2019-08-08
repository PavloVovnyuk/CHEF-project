package com.pavel.vovniuk.dishes.repository;

import com.pavel.vovniuk.dishes.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findDishByTitle(String title);
    List<Dish> findByDishesCategory (String dishesCategory);

    @Query(" SELECT d FROM Dish d " +
            "  WHERE 1=1 " +
            " AND (:title is Null or d.title = :title) " +
            " AND (:dishesCategory is null or d.dishesCategory = :dishesCategory)"
    )
   List<Dish> findDishByUsingFilters(@Param("title") String title,
                                             @Param("dishesCategory") String  dishesCategory);


}
