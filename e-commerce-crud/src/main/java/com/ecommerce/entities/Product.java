package com.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;
    private String name;
    private int price;
    private  int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
