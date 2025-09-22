package com.ecommerce.DTOs;

import com.ecommerce.entities.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
public class ProductDTO {
    private  Long id;
    private String name;
    private int Price;
    private  int stock;
    private  String categoryName;


}
