package com.dev_ajay.scalerdemo1.Service;

import com.dev_ajay.scalerdemo1.Models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(Long Id);
    public Product createProduct(String title, String description, double price, String image, String category);
    public List<Product> getAllProduct();
    public List<String> getAllCategories();
    public Product updateProduct(Long id, String title, double price, String description, String image, String category);
    public void deleteProduct(Long id);
}
