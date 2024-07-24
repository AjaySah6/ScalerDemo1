package com.dev_ajay.scalerdemo1.Service;

import com.dev_ajay.scalerdemo1.Models.Product;

public interface ProductService {
    public Product getSingleProduct(Long Id);
    public Product createProduct(String title, String description, double price, String image, String category);
}
