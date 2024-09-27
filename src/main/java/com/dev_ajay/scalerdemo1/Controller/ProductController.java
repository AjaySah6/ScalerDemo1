package com.dev_ajay.scalerdemo1.Controller;

import com.dev_ajay.scalerdemo1.DTO.CreateProductRequestDTO;
import com.dev_ajay.scalerdemo1.Models.Product;
import com.dev_ajay.scalerdemo1.Service.ProductService;
import com.dev_ajay.scalerdemo1.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);

        // getSingleProduct method in the ProductService interface returns an Optional<Product> to indicate that the product might not be found.
        //This code unwraps the Optional using orElseThrow, which throws a ProductNotFoundException if the product is not found.
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

    // ResponseEntity contains everything that a response requires:
    // Data, Status code and headers
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() throws ProductNotFoundException {
        List<Product> responseData = productService.getAllProduct();

        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(
                responseData, HttpStatusCode.valueOf(202));

        return responseEntity;
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

    @PutMapping("/products/{id}")
    public Product updateProductById(@PathVariable("id") Long id,
                                     @RequestBody CreateProductRequestDTO productRequest) {
        Product updatedProduct = productService.updateProduct(
                id,
                productRequest.getTitle(),
                productRequest.getPrice(),
                productRequest.getDescription(),
                productRequest.getImage(),
                productRequest.getCategory()
        );
        System.out.println("controller");
        return updatedProduct;
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.ok("Product deleted successfully");
    }
}