package com.dev_ajay.scalerdemo1.DTO;

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
}
