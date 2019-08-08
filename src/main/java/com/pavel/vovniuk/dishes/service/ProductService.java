package com.pavel.vovniuk.dishes.service;

import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.entity.Product;
import com.pavel.vovniuk.dishes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findProductByName(String name) {
        List<Product> product = productRepository.findProductByName(name);
        return product;
    }

    public List<Product> allProducts() {
        List<Product> allProd = productRepository.findAll();
        return allProd;
    }

    public Optional<Product> findProductsById(Long id) {
        Optional<Product> findbyId = productRepository.findById(id);
        return findbyId;
    }

}
