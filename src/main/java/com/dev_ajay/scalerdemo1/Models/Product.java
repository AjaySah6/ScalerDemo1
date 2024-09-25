package com.dev_ajay.scalerdemo1.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Product extends BaseModel {
    //private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
}
