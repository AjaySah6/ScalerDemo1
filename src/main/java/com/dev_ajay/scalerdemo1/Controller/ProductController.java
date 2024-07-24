package com.dev_ajay.scalerdemo1.Controller;

import com.dev_ajay.scalerdemo1.DTO.CreateProductRequestDTO;
import com.dev_ajay.scalerdemo1.Models.Product;
import com.dev_ajay.scalerdemo1.Service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO productRequestDto) {
        return productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getPrice(),   // Correct order of parameters
                productRequestDto.getImage(),
                productRequestDto.getCategory()
        );
    }
}
