package com.dev_ajay.scalerdemo1.Controller;
import com.dev_ajay.scalerdemo1.Models.Product;

import com.dev_ajay.scalerdemo1.Service.FakeStoreProductService;
import com.dev_ajay.scalerdemo1.Service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    //Constructors so that we do not create its object manually all the time
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //ProductController will user ProductService via interface
    //ProductService productService = new FakeStoreProductService();


    @GetMapping("/products/{Id}")
    public Product getProductById(@PathVariable("Id") Long Id) {
        return productService.getSingleProduct(Id);
    }
}