package com.pavel.vovniuk.dishes.service;

import com.pavel.vovniuk.dishes.entity.Product;
import com.pavel.vovniuk.dishes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    /**
     * @param name
     * @return produkt po nazwie
     */
    public List<Product> findProductByName(String name) {
        List<Product> product = productRepository.findProductByName(name);
        return product;
    }

    /**
     * @return wszystkie producty
     */
    public List<Product> allProducts() {
        List<Product> allProd = productRepository.findAll();
        return allProd;
    }

}
