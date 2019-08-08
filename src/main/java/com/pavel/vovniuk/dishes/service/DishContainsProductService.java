package com.pavel.vovniuk.dishes.service;

import com.pavel.vovniuk.dishes.dto.*;
//import com.pavel.vovniuk.dishes.dto.SumPriceAllProductsForDish;
//import com.pavel.vovniuk.dishes.dto.ProduktTitleDishName;
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

    public List<ProductsAndDish> findAllByDishAndProduct() {
        List<ProductsAndDish> listDishesAndProducts = dishContainsProductRepository.findAllByDishId();
        return listDishesAndProducts;
    }

    public List<Product> findAllProductsByDish(Long dishId) {
        List<ProductsAndDish> listProductsAndDishes = findAllByDishAndProduct();
        ProductsAndDish dish = listProductsAndDishes.get(Math.toIntExact(dishId));
        List<DishContainsProduct> products = dish.getDish().getDishContainsProducts();
        List<Product> productsList = new ArrayList<>();
        for (DishContainsProduct productIter : products) {
            Product product = productIter.getProduct();
            productsList.add(product);
        }
        return productsList;
    }

    public Dish getDishWithPrice(Long dishId) {
        List<ProductsAndDish> productsAndDishList = findAllByDishAndProduct();
        ProductsAndDish dishAndProduct = productsAndDishList.get(Math.toIntExact(dishId));
        Dish dish = dishAndProduct.getDish();
        Double priceDish;
        List<DishContainsProduct> dishContainsProducts = dish.getDishContainsProducts();
        double sum = 0;
        for (DishContainsProduct products : dishContainsProducts) {
            priceDish = products.getProduct().getPrice();
            sum += priceDish;
        }
        dish.setDishPrice(sum);
        return dish;
    }

    @Override
    public String toString() {
        return "DishContainsProductService{" +
                "dishContainsProductRepository=" + dishContainsProductRepository +
                ", dishRepository=" + dishRepository +
                ", productRepository=" + productRepository +
                '}';
    }

    public List<ProductsAndDish> findDishByProduct(Product product) {
        System.out.println(product);
        List<ProductsAndDish> dishesByProductList = dishContainsProductRepository.findAllByProduct(product);
        return dishesByProductList;
    }

// Wyszukiwanie po 1 produkcie

    public Dish findAllByProductName(ProductName name) {
        ProductsAndDish dishByProduct = dishContainsProductRepository.findAllByProductName(name.getName());
        Dish dish = dishByProduct.getDish();
        return dish;
    }

//    public Set<Dish> findDishContainsProductsByProductNameAndPrice(DishByNameAndPrice listOfNamesAndPrice) {
//        Set<Dish> dishList = new LinkedHashSet<>();
//        for (ProductName namesAndPrice : listOfNamesAndPrice.getName()) {
//            double sumproductPrice = 0;
//            Set<ProductsAndDish> disheByProducts = dishContainsProductRepository.findDishContainsProductByProductName(namesAndPrice.getName());
//            for (ProductsAndDish dishes : disheByProducts) {
//                Dish dish = dishes.getDish();
//                List<DishContainsProduct> productsList = dish.getDishContainsProducts();
//                for (DishContainsProduct product : productsList) {
//                    double productPrice = product.getProduct().getPrice();
//                    sumproductPrice += productPrice;
//                }
//                dish.setDishPrice(sumproductPrice);
//                if (dish.getDishPrice() <= listOfNamesAndPrice.getPrice()) {
//                    System.out.println(listOfNamesAndPrice.getPrice());
//                    dishList.add(dish);
//                }
//            }
//        }
//        return dishList;
//    }
    public Set<Dish> findDishContainsProductsByProductNameAndPrice(DishByNameAndPrice listOfNamesAndPrice) {
        Set<Dish> dishList = new LinkedHashSet<>();
        for (ProductName namesAndPrice : listOfNamesAndPrice.getName()) {
            double sumproductPrice = 0;
            Set<ProductsAndDish> disheByProducts = dishContainsProductRepository.findDishContainsProductByProductName(namesAndPrice.getName());
            for (ProductsAndDish dishes : disheByProducts) {
                Dish dish = dishes.getDish();
                List<DishContainsProduct> productsList = dish.getDishContainsProducts();
                for (DishContainsProduct product : productsList) {
                    double productPrice = product.getProduct().getPrice();
                    sumproductPrice += productPrice;
                }
                dish.setDishPrice(sumproductPrice);
                if (dish.getDishPrice() <= listOfNamesAndPrice.getPrice()) {
                    System.out.println(listOfNamesAndPrice.getPrice());
                    dishList.add(dish);
                }
            }
        }
        return dishList;
    }

    public List<Product> productsToBy(DishByNameAndPrice listOfNamesAndPrice) {
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
}


//Metody które mają logike w Query

//    public List<ProduktTitleDishName> findProductsByDish(Long id) {
//        List<ProduktTitleDishName> dishById = dishContainsProductRepository.findProductsByDish(id);
//        return dishById;
//    }
//
//    public List<SumPriceAllProductsForDish> priceAllProductsForDish(Long id) {
//        List<SumPriceAllProductsForDish> priceListForDish = dishContainsProductRepository.SumPriceAllProductsForDish(id);
//        return priceListForDish;
//    }
//    public List<FindDishByPrice> findAllDishByPrice (double dishPrice){
//        List<FindDishByPrice> allDishByPrice = dishContainsProductRepository.findAllDishByPrice(dishPrice);
//        return allDishByPrice;
//    }



