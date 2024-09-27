package com.dev_ajay.scalerdemo1.Service;

import com.dev_ajay.scalerdemo1.DTO.FakeStoreProductDTO;
import com.dev_ajay.scalerdemo1.Models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate,
                                   RedisTemplate<String,Object> redisTemplate) { // it takes string and return object
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {

        // Before making call to API, we can check Redis first, if it contains the required data
        Product productFromCache = (Product) redisTemplate.opsForValue().get(String.valueOf(id));
        if (productFromCache != null) {
            return productFromCache; // If Redis has the data, simply return it
        }

        // If Redis doesn't have the data, use restTemplate to get the data from the API
        String url = "https://fakestoreapi.com/products/" + id;
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(url, FakeStoreProductDTO.class);

        if (fakeStoreProductDTO == null) {
            return null; // If the API doesn't return a product, return null
        }

        // Convert DTO to Product
        Product product = fakeStoreProductDTO.toProduct();

        // Store the product in Redis for future use
        redisTemplate.opsForValue().set(String.valueOf(id), product); // but can not send the java object over the network to Redis eventually -> need to serialize it (convert to byte)
        //implement Serializable in model > Product and Category
        return product;
    }



    // The parameters in productService.createProduct should match the DTO.
    @Override
    public Product createProduct(String title, String description, double price, String image, String category) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setImage(image);
        fakeStoreProductDTO.setCategory(category);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FakeStoreProductDTO> request = new HttpEntity<>(fakeStoreProductDTO, headers);

        //Starts a try block to catch any exceptions that may occur during the HTTP request.
        try {
            //This sends a POST request to the specified URL with the request (which includes the body and headers).
            // The response is expected to be of type FakeStoreProductDTO, and it is wrapped in a ResponseEntity,
            // which contains both the response body and status code.
            ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
                    "https://fakestoreapi.com/products", request, FakeStoreProductDTO.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) { //Checks if the response status code indicates success (a status code in the range 200-299) and if the response body is not null.
                return response.getBody().toProduct();  // If the response is successful and contains a body, it converts the FakeStoreProductDTO to a Product using the toProduct method and returns it.
            } else {
                return null;
            }
        } catch (Exception e) {  // Catches any exceptions that occur during the HTTP request. It prints the stack trace of the exception for debugging purposes and returns null.
            e.printStackTrace();
            return null;
        }
        //HttpHeaders and HttpEntity: Setup the request headers and entity (body + headers).
        //try block: Attempts to send the POST request.
        //Response Handling: Checks if the response was successful and returns the converted Product object if so, otherwise returns null.
        //Exception Handling: Catches and prints exceptions, returning null if any error occurs.
    }

    @Override
    public List<Product> getAllProduct(){  // will return ResponseEntity wrapped around the data

        FakeStoreProductDTO[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDTO fakeStoreProductDTO : response) {
            products.add(fakeStoreProductDTO.toProduct());
        }

        return products;
    }

    @Override
    public List<String> getAllCategories(){
        String[] categories = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories", String[].class);

        List<String> categoryList = new ArrayList<>();
        for(String category : categories) {
            categoryList.add(category);
        }
        return categoryList;
        //String category: Declares a variable category of type String, which will hold each element of the categories array one at a time during each iteration.
        //categories: Means "for each element in the categories array."
    }

//    @Override
//    public Product updateProduct(Long id, String title, double price, String description, String image, String category) {
//        // Create the DTO to send to the API
//        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
//        fakeStoreProductDTO.setId(id);
//        fakeStoreProductDTO.setTitle(title);
//        fakeStoreProductDTO.setPrice(price);
//        fakeStoreProductDTO.setDescription(description);
//        fakeStoreProductDTO.setImage(image);
//        fakeStoreProductDTO.setCategory(category);
//
//        // Log the payload
//        System.out.println("Sending request to update product: " + fakeStoreProductDTO);
//
//        // Make the PUT request to update the product
//        restTemplate.put("https://fakestoreapi.com/products/{id}", fakeStoreProductDTO, id);
//
//        // After the update, fetch the updated product details
//        return getSingleProduct(id);
//
//    }

    @Override
    public Product updateProduct(Long id, String title, double price, String description, String image, String category) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(id);
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setImage(image);
        fakeStoreProductDTO.setCategory(category);

        System.out.println("Sending request to update product: " + fakeStoreProductDTO);

        try {
            restTemplate.put("https://fakestoreapi.com/products/{id}", fakeStoreProductDTO, id);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception (optional: log error message)
        }
        return getSingleProduct(id);
    }
    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/{id}", id);
    }

}

