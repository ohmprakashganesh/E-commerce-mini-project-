package com.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long id;
    private  String name;

    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL, orphanRemoval = true )
    private List<Product> products;



}
