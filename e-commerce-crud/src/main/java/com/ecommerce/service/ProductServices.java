package com.ecommerce.service;

import com.ecommerce.DTOs.ProductDTO;
import com.ecommerce.entities.Product;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductServices {


    //create product
     ProductDTO createProduct(ProductDTO product);

     //fetch single product
     ProductDTO getProductById(Long id);

     //update the product
     ProductDTO updateProduct(Long id, ProductDTO product);

     //delete  the product
     void deleteProduct(Long id);

     //fetch all Products
     List<ProductDTO> getAllProducts();



 Page<ProductDTO> filterByCategoryAndPrice(String category, Integer min, Integer max, int page, int size, String sortBy, String sortDir);

 // List<ProductDTO> filterByCategory(String category);
//
// List<ProductDTO> filterByPriceRange(int min, int max);

 // Fetching based on Sort and
//        Page<ProductDTO> getProductsByCategory(String categoryName, Pageable pageable);
//        Page<ProductDTO> getProductsByPriceRange(Double minPrice, Double maxPrice, Pageable pageable);
//        Page<ProductDTO> getAllProductsPaginated(Pageable pageable);

}
