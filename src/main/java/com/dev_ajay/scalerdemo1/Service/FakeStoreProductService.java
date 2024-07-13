package com.dev_ajay.scalerdemo1.Service;

import com.dev_ajay.scalerdemo1.Models.Category;
import com.dev_ajay.scalerdemo1.DTO.FakeStoreProductDTO;
import com.dev_ajay.scalerdemo1.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {

    @Override
    public Product getSingleProduct(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products/" + id;
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(url, FakeStoreProductDTO.class);

        if (fakeStoreProductDTO == null) {
            return null;
        }

        // Front end
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImageUrl(fakeStoreProductDTO.getImage());

        // Map the category
        Category category = new Category();
        category.setTitle(fakeStoreProductDTO.getCategory());
        product.setCategory(category);

        return product;
    }
}
