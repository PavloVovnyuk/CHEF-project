package com.pavel.vovniuk.dishes.controller;

import com.pavel.vovniuk.dishes.entity.Dish;
import com.pavel.vovniuk.dishes.entity.Product;
import com.pavel.vovniuk.dishes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    List<Product> productList;

    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        productList = productService.allProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/getproductbytitle/{name}")
    public ResponseEntity<List<Product>> findProductsByName(@PathVariable("name") String name) {
        productList = productService.findProductByName(name);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
