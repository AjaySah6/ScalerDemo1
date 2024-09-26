package com.dev_ajay.scalerdemo1;

import com.dev_ajay.scalerdemo1.Models.Product;
import com.dev_ajay.scalerdemo1.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Needed dependency -> spring-boot-starter-test
public class ScalerDemo1ApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testingQuery() {
        // Assuming there's a product with a specific title and id in your database
        Product product = productRepository.getProductWithSomeTitleAndId("iPhone XR", 1L);

        // Print the price of the product if found
        if (product != null) {
            System.out.println(product.getPrice());
        } else {
            System.out.println("Product not found.");
        }
    }
}
