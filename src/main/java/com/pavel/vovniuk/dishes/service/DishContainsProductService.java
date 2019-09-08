package com.pavel.vovniuk.dishes.service;
import com.pavel.vovniuk.dishes.dto.*;
import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.entity.DishContainsProduct;
import com.pavel.vovniuk.dishes.entity.Product;
import com.pavel.vovniuk.dishes.repository.DishContainsProductRepository;
import com.pavel.vovniuk.dishes.repository.DishRepository;
import com.pavel.vovniuk.dishes.repository.ProductRepository;
import org.hibernate.type.SerializableToBlobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class DishContainsProductService {
    @Autowired
    DishContainsProductRepository dishContainsProductRepository;
    @Autowired
    DishRepository dishRepository;
    @Autowired
    ProductRepository productRepository;


    /**
     * @return wszystkie dania i produkty
     */
    public List<ProductsAndDishes> findAllByDishAndProduct() {
        List<ProductsAndDishes> listDishesAndProducts = dishContainsProductRepository.findAllBy();
        return listDishesAndProducts;
    }

    /**
     * @param dishId
     * @return szystkie producty dla dania po Id dania
     */
    public Set<Product> findAllProductsByDish(Long dishId) {
        Set<Product> products = new LinkedHashSet<>();
        Set<ProductsAndDishes> listProductsAndDishes = dishContainsProductRepository.findDishContainsProductsByDishId(dishId);
        for (ProductsAndDishes productsAndDishes : listProductsAndDishes) {
            Dish dish = productsAndDishes.getDish();
            List<DishContainsProduct> dishContainsProductsList = dish.getDishContainsProducts();
            for (DishContainsProduct dishContainsProduct : dishContainsProductsList) {
                Product product = dishContainsProduct.getProduct();
                products.add(product);
            }
        }
        return products;
    }

    /**
     * @param name
     * @return liste dań po nazwie produkctu
     */

    public List<Dish> findDishesAndProductsByProductName(ProductName name) {
        List<Dish> dishes = new ArrayList<>();
        List<ProductsAndDishes> productsAndDishesList = dishContainsProductRepository.findAllByProductName(name.getName());
        for (ProductsAndDishes dishesList : productsAndDishesList) {
            Dish dish = dishesList.getDish();
            dishes.add(dish);
        }
        return dishes;
    }

    /**
     *
     * @param names
     * @return wyszukiwanie dan za lista nazw produktow
     */
    public List<Dish> findDishesAndProductsByStringNamesList(List<String> names) {
        List<Dish> dishes = new ArrayList<>();
        for (String name : names) {
            List<ProductsAndDishes> productsAndDishes = dishContainsProductRepository.findDishContainsProductByProductName(name);
            for (ProductsAndDishes productsAndDishesList : productsAndDishes) {
                Dish dish = productsAndDishesList.getDish();
                dishes.add(dish);
            }
        }
        return dishes;
    }

    /**
     * @param productsNames
     * @return wszystkie dania po liscie nazw productów
     */
    public List<Dish> findDishesAndProductsByProductsNamesList(ProductsNames productsNames) {
        List<String> nameOfProducts = productsNames.getNameOfProducts();
        List<Dish> dishes = findDishesAndProductsByStringNamesList(nameOfProducts);
        return dishes;
    }

    /**
     *
     * @param dishes
     * @return dania z obliczoną ceną
     */
    static List<Dish> calkulateDishPrice(List<Dish> dishes) {
        List<Dish> dishesWithPrice = new ArrayList<>();
        Double dishPrice;
        for (Dish dish : dishes) {
            List<DishContainsProduct> dishContainsProducts = dish.getDishContainsProducts();
            double sum = 0;
            for (DishContainsProduct products : dishContainsProducts) {
                dishPrice = products.getProduct().getPrice();
                sum += dishPrice;
            }
            dish.setDishPrice(sum);
            dishesWithPrice.add(dish);
        }
        return dishesWithPrice;
    }

    /**
     * @param name
     * @return dania po nazwie produktu oraz obliczenie ceny dania
     */
    public List<Dish> findDishByProductNameAndCalculateDishPrice(ProductName name) {
        List<Dish> dishes = findDishesAndProductsByProductName(name);
        List<Dish> dishesWithPrice = calkulateDishPrice(dishes);
        return dishesWithPrice;
    }

    /**
     * @param listOfNamesAndPrice
     * @return dania po cenie i liście nazw produktów
     */
    public Set<Dish> findDishContainsProductsByProductNamesListAndPrice(DishByNamesAndPrice listOfNamesAndPrice) {
        Set<Dish> dishesForUser = new LinkedHashSet<>();
        List<String> nameOfProducts = listOfNamesAndPrice.getNameOfProducts();
        List<Dish> dishes = findDishesAndProductsByStringNamesList(nameOfProducts);
        List<Dish> listOfDishes = calkulateDishPrice(dishes);
        System.out.println(listOfNamesAndPrice.getPrice() + " cena od usera");
        for (Dish dish : listOfDishes) {
            if (dish.getDishPrice()>=0&&dish.getDishPrice()<=listOfNamesAndPrice.getPrice()) {
                dishesForUser.add(dish);
            }
        }
        return dishesForUser;
    }

//    public List<Prod> productsToBy(DishByNamesAndPrice listOfNamesAndPrice) {
//        List<Prod> listProductToByu = new ArrayList<>();
//        Set<Dish> dishes = findDishContainsProductsByProductNameAndPrice(listOfNamesAndPrice);
//        for (Dish dish : dishes) {
//            List<DishContainsProduct> dishContainsProducts = dish.getDishContainsProducts();
//            for (DishContainsProduct productList : dishContainsProducts) {
//                for (ProductName productListFromUser : listOfNamesAndPrice.getNameOfProducts()) {
//                    if (!(productList.getProduct().getNameOfProducts().contains(productListFromUser.getNameOfProducts()))) {
//                        listProductToByu.add(productList.getProduct());
//                    }
//                }
//            }
//        }
//        return listProductToByu;
//    }

    /**
     * @param id
     * @return nazwe productu oraz danie po Id dania
     * metody z Qery
     */

    public List<ProduktTitleDishName> findProductsByDish(Long id) {
        List<ProduktTitleDishName> dishById = dishContainsProductRepository.findProductsByDish(id);
        return dishById;
    }

    /**
     * @param id
     * @return nazwe dania i sumę cen na produkty po Id dania
     * metody z Qery
     */
    public List<SumPricesAllProductsForDish> priceAllProductsForDish(Long id) {
        List<SumPricesAllProductsForDish> priceListForDish = dishContainsProductRepository.SumPriceAllProductsForDish(id);
        return priceListForDish;
    }
}



