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
        List<ProductsAndDishes> listDishesAndProducts = dishContainsProductRepository.findAllByDishId();
        return listDishesAndProducts;
    }

    /**
     * @param dishId
     * @return szystkie producty dla dania po Id dania
     */
    public List<Product> findAllProductsByDish(Long dishId) {
        List<ProductsAndDishes> listProductsAndDishes = findAllByDishAndProduct();
        ProductsAndDishes dish = listProductsAndDishes.get(Math.toIntExact(dishId));
        List<DishContainsProduct> products = dish.getDish().getDishContainsProducts();
        List<Product> productsList = new ArrayList<>();
        for (DishContainsProduct productIter : products) {
            Product product = productIter.getProduct();
            productsList.add(product);
        }
        return productsList;
    }

    /**
     * @param product
     * @return dania i produkty po produkcie
     */
    public List<ProductsAndDishes> findDishByProduct(Product product) {
        System.out.println(product);
        List<ProductsAndDishes> dishesByProductList = dishContainsProductRepository.findAllByProduct(product);
        return dishesByProductList;
    }

    /**
     * @param name
     * @return danie po produkcie
     */

    public Dish findAllByProductName(ProductName name) {
        ProductsAndDishes dishByProduct = dishContainsProductRepository.findAllByProductName(name.getName());
        Dish dish = dishByProduct.getDish();
        return dish;
    }

    /**
     * @param name
     * @return dania po nazwie produktu oraz obliczenie ceny dania
     */
    public Dish findDishByProductNameAndCalculateDishPrice(ProductName name) {
        Dish dish = findAllByProductName(name);
        Double dishPrice;
        List<DishContainsProduct> dishContainsProducts = dish.getDishContainsProducts();
        double sum = 0;
        for (DishContainsProduct products : dishContainsProducts) {
            dishPrice = products.getProduct().getPrice();
            sum += dishPrice;
        }
        dish.setDishPrice(sum);
        return dish;
    }

    /**
     * @param listOfNamesAndPrice
     * @return dania po cenie i liście nazw produktów
     */
    public Set<Dish> findDishContainsProductsByProductNameAndPrice(DishByNamesAndPrice listOfNamesAndPrice) {
        Set<Dish> dishList = new LinkedHashSet<>();
        for (ProductName namesAndPrice : listOfNamesAndPrice.getName()) {
            double sumproductPrice = 0;
            Set<ProductsAndDishes> disheByProducts = dishContainsProductRepository.findDishContainsProductByProductName(namesAndPrice.getName());
            for (ProductsAndDishes productsAndDish : disheByProducts) {
                Dish dish = productsAndDish.getDish();
                List<DishContainsProduct> productsList = dish.getDishContainsProducts();
                for (DishContainsProduct product : productsList) {
                    double productPrice = product.getProduct().getPrice();
                    sumproductPrice += productPrice;
                }
                dish.setDishPrice(sumproductPrice);

                if (dish.getDishPrice() <= listOfNamesAndPrice.getPrice() || (namesAndPrice.getName().contains((CharSequence) dish.getDishContainsProducts()))) {
                    System.out.println(listOfNamesAndPrice.getPrice());
                    dishList.add(dish);
                }
            }
        }
        return dishList;
    }

    public List<Product> productsToBy(DishByNamesAndPrice listOfNamesAndPrice) {
        List<Product> listProductToByu = new ArrayList<>();
        Set<Dish> dishes = findDishContainsProductsByProductNameAndPrice(listOfNamesAndPrice);
        for (Dish dish : dishes) {
            List<DishContainsProduct> dishContainsProducts = dish.getDishContainsProducts();
            for (DishContainsProduct productList : dishContainsProducts) {
                for (ProductName productListFromUser : listOfNamesAndPrice.getName()) {
                    if (!(productList.getProduct().getName().contains(productListFromUser.getName()))) {
                        listProductToByu.add(productList.getProduct());
                    }
                }
            }
        }
        return listProductToByu;
    }

    /**
     * @param id
     * @return nazwe productu oraz danie po Id dania
     */

    public List<ProduktTitleDishName> findProductsByDish(Long id) {
        List<ProduktTitleDishName> dishById = dishContainsProductRepository.findProductsByDish(id);
        return dishById;
    }

    /**
     * @param id
     * @return nazwe dania i sumę cen na produkty po Id dania
     */
    public List<SumPricesAllProductsForDish> priceAllProductsForDish(Long id) {
        List<SumPricesAllProductsForDish> priceListForDish = dishContainsProductRepository.SumPriceAllProductsForDish(id);
        return priceListForDish;
    }
}



