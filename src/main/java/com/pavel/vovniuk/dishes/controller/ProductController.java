package com.pavel.vovniuk.dishes.controller;


import com.pavel.vovniuk.dishes.entity.Product;
import com.pavel.vovniuk.dishes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    List<Product> productList;

    /**
     *
     * @return wszystkie produkty
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        productList = productService.allProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     *
     * @param name
     * @return produkty po nazwie
     */
    @GetMapping("/productby/{name}")
    public ResponseEntity<List<Product>> findProductsByName(@PathVariable("name") String name) {
        productList = productService.findProductByName(name);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
