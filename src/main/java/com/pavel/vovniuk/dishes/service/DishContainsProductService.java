package com.pavel.vovniuk.dishes.service;

import com.pavel.vovniuk.dishes.dto.*;
import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.entity.DishContainsProduct;
import com.pavel.vovniuk.dishes.entity.Product;
import com.pavel.vovniuk.dishes.repository.DishContainsProductRepository;
import com.pavel.vovniuk.dishes.repository.DishRepository;
import com.pavel.vovniuk.dishes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return dishContainsProductRepository.findAllBy();
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
     * @param dishes
     * @return dania z obliczoną ceną
     */
    private List<Dish> calkulateDishPrice(List<Dish> dishes) {
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
     * @param dish
     * @return productList
     */

    public List<Product> productListFromDish(Dish dish) {
        List<Product> productsList = new ArrayList<>();
        List<DishContainsProduct> dishContainsProducts = dish.getDishContainsProducts();
        for (DishContainsProduct dishContainsProduct : dishContainsProducts) {
            Product product = dishContainsProduct.getProduct();
            productsList.add(product);
        }
        return productsList;
    }

    /**
     * @param dish
     * @return posortowaną liste nazw produktów
     */
    public List<String> productsNamesFromDish(Dish dish) {
        List<Product> products = productListFromDish(dish);
        List<String> productsNames = new ArrayList<>();
        products.stream().forEach(pr -> productsNames.add(pr.getName()));
        Collections.sort(productsNames);
        return productsNames;
    }

    /**
     * @param listOfNamesAndPrice
     * @return dania po cenie i liście nazw produktów
     */
    public Set<Dish> findDishContainsProductsByProductNamesListAndPrice(DishByNamesAndPrice listOfNamesAndPrice) {
        List<String> nameOfProductsSorting = listOfNamesAndPrice.getNameOfProducts();
        Collections.sort(nameOfProductsSorting);
        Set<Dish> dishesForUser = new LinkedHashSet<>();
        List<String> nameOfProducts = listOfNamesAndPrice.getNameOfProducts();
        List<Dish> dishes = findDishesAndProductsByStringNamesList(nameOfProducts);
        List<Dish> listOfDishes = calkulateDishPrice(dishes);
        for (Dish dish : listOfDishes) {
            if (dish.getDishPrice() >= 0 && dish.getDishPrice() <= listOfNamesAndPrice.getPrice() || productsNamesFromDish(dish).equals(nameOfProductsSorting)) {
                dishesForUser.add(dish);
            }
        }
        return dishesForUser;
    }

    /**
     * @param products
     * @param listOfNamesAndPrice
     * @return produkty do kupienia
     */
    public List<String> productsNameToCompare(List<Product> products, DishByNamesAndPrice listOfNamesAndPrice) {
        List<String> productsToBuy = new ArrayList<>();
        List<String> nameOfProducts = listOfNamesAndPrice.getNameOfProducts();
        for (Product product : products) {
            if (!(nameOfProducts.contains(product.getName()))) {
                productsToBuy.add(product.getName());
            }
        }
        return productsToBuy;
    }

    /**
     * @param products
     * @param listOfNamesAndPrice
     * @return suma pieniedzy w zaleznosci od listy produktow
     */
    public Double priceToSpent(List<Product> products, DishByNamesAndPrice listOfNamesAndPrice) {
        double sum = 0;
        double result = 0;
        List<String> productNames = new ArrayList<>();
        List<String> nameOfProductsFromUser = listOfNamesAndPrice.getNameOfProducts();
        for (Product product : products) {
            String productName = product.getName();
            productNames.add(productName);
        }
        Collections.sort(productNames);
        Collections.sort(nameOfProductsFromUser);
        if (productNames.equals(nameOfProductsFromUser)) {
            result = listOfNamesAndPrice.getPrice();
        } else {
            for (Product product : products) {
                if (!(nameOfProductsFromUser.contains(product.getName()))) {
                    sum += product.getPrice();
                }
            }
            result = listOfNamesAndPrice.getPrice() - sum;
        }

        return result;
    }

    /**
     * @param listOfDishes
     * @param listOfNamesAndPrice
     * @return producty do kupienia oraz informacje o pieniądzach
     */
    public Set<ProductsToBuyAndPriceToSpent> differentOfMoney(List<Dish> listOfDishes, DishByNamesAndPrice listOfNamesAndPrice) {
        Set<ProductsToBuyAndPriceToSpent> productsToBuyAndPriceToSpentList = new LinkedHashSet<>();
        for (Dish dish : listOfDishes) {
            List<Product> products = productListFromDish(dish);
            List<String> productsSetToBuy = productsNameToCompare(products, listOfNamesAndPrice);
            Double price = priceToSpent(products, listOfNamesAndPrice);
            ProductsToBuyAndPriceToSpent productsToBuyAndPriceToSpentObject = new ProductsToBuyAndPriceToSpent();
            productsToBuyAndPriceToSpentObject.setDish(dish);
            if (price == 0) {
                productsToBuyAndPriceToSpentObject.setNotUsedMoney(listOfNamesAndPrice.getPrice());
            } else if (price > 0) {
                productsToBuyAndPriceToSpentObject.setExcessMoney(price);
            } else if (price < 0) {
                productsToBuyAndPriceToSpentObject.setLackMoney(price);
            }
            productsToBuyAndPriceToSpentObject.setProductsToBuy(productsSetToBuy);
            productsToBuyAndPriceToSpentList.add(productsToBuyAndPriceToSpentObject);
        }
        return productsToBuyAndPriceToSpentList;

    }

    /**
     * @param listOfNamesAndPrice
     * @return informacje o zakupie w całaści
     */
    public Set<ProductsToBuyAndPriceToSpent> productsToBuyAndPriceToSpent(DishByNamesAndPrice listOfNamesAndPrice) {
        List<String> nameOfProducts = listOfNamesAndPrice.getNameOfProducts();
        Collections.sort(nameOfProducts);
        List<Dish> dishes = findDishesAndProductsByStringNamesList(nameOfProducts);
        List<Dish> listOfDishes = calkulateDishPrice(dishes);
        Set<ProductsToBuyAndPriceToSpent> productsToBuyAndPriceToSpents = differentOfMoney(listOfDishes, listOfNamesAndPrice);
        return productsToBuyAndPriceToSpents;
    }


    // Metody z wykorzystaniem Qery

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



