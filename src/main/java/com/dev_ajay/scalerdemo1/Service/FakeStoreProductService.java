package com.dev_ajay.scalerdemo1.Service;

import com.dev_ajay.scalerdemo1.DTO.FakeStoreProductDTO;
import com.dev_ajay.scalerdemo1.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {
        // Removed the unnecessary creation of a new RestTemplate instance
        // RestTemplate restTemplate = new RestTemplate(); // This line is removed

        String url = "https://fakestoreapi.com/products/" + id;
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(url, FakeStoreProductDTO.class);

        if (fakeStoreProductDTO == null) {
            return null;
        }

        return fakeStoreProductDTO.toProduct();
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
}

