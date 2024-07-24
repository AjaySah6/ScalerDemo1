package com.dev_ajay.scalerdemo1.DTO;

import com.dev_ajay.scalerdemo1.Models.Category;
import com.dev_ajay.scalerdemo1.Models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    // Category is a string in the FakeStore API

    public Product toProduct() {
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setPrice(getPrice());
        product.setImageUrl(getImage());

        Category category = new Category();
        category.setTitle(getCategory());

        product.setCategory(category);

        return product;

    }
}
