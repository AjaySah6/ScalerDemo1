package com.dev_ajay.scalerdemo1.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDTO {
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
