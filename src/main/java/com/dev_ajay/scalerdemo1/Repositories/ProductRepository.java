package com.dev_ajay.scalerdemo1.Repositories;

import com.dev_ajay.scalerdemo1.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product findById(long id);
    List<Product> findAll();
}
