package com.ecommerce.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "Category")
public class Category {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long id;
    private  String name;

    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL, orphanRemoval = true )
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
