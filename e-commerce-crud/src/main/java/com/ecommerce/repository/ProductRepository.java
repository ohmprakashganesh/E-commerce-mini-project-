package com.ecommerce.repository;

import com.ecommerce.DTOs.ProductDTO;
import com.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //flter by name
    List<Product> findByCategory_NameIgnoreCase(String categoryName);

    // Filter by price range between max and min
    List<Product> findByPriceBetween(int minPrice, int maxPrice);

    //  filter by category and price
    List<Product> findByCategory_NameIgnoreCaseAndPriceBetween(String categoryName, int minPrice, int maxPrice);

    List<Product> findByCategory_NameIgnoreCaseAndPriceLessThanEqual(String category, Integer max);

    List<Product> findByCategory_NameIgnoreCaseAndPriceGreaterThanEqual(String category, Integer max);

    List<Product> findByPriceGreaterThanEqual(Integer min);

    List<Product> findByPriceLessThanEqual(Integer max);
}
