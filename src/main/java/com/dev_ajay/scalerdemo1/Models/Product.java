package com.dev_ajay.scalerdemo1.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


// Front end format
public class Product {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;
}
