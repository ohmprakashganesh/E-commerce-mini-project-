package com.ecommerce.Mapping;

import com.ecommerce.DTOs.ProductDTO;
import com.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public  class ProductMapping {

    public ProductDTO ProductToDto(Product product){
        ProductDTO productDTO= new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());

        if (product.getCategory() != null) {
            productDTO.setCategoryName(product.getCategory().getName());
        } else {
            productDTO.setCategoryName("Uncategorized"); // or null if you prefer
        }
        return productDTO;

    }


    public Product  DtoToProduct(ProductDTO productDTO){
        Product product= new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        return  product;

    }

}
