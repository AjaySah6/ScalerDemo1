package com.dev_ajay.scalerdemo1.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
@Entity
public class Category extends BaseModel implements Serializable {
    private String title;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JsonIgnore  //helps -> Jackson library to ignore this attribute/ to avoid recursion when do the GET api
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> products;

}
