package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;
    @NotNull
    private String name;
    @Min(0)
    private int price;
    @Min(0)
    private  int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
