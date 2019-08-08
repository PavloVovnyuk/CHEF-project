package com.pavel.vovniuk.dishes.repository;

import com.pavel.vovniuk.dishes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByName (String name);

}
