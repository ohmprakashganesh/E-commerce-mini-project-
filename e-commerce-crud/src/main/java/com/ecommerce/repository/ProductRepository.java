package com.ecommerce.repository;

import com.ecommerce.DTOs.ProductDTO;
import com.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAll(Pageable pageable);

    //flter by name
    Page<Product> findByCategory_NameIgnoreCase(String categoryName,Pageable pageable);

    // Filter by price range between max and min
    Page<Product> findByPriceBetween(int minPrice, int maxPrice,Pageable pageable);

    //  filter by category and price
    Page<Product> findByCategory_NameIgnoreCaseAndPriceBetween(String categoryName, int minPrice, int maxPrice, Pageable pageable);

    Page<Product> findByCategory_NameIgnoreCaseAndPriceLessThanEqual(String category, Integer max,Pageable pageable);

    Page<Product> findByCategory_NameIgnoreCaseAndPriceGreaterThanEqual(String category, Integer max,Pageable pageable);

    Page<Product> findByPriceGreaterThanEqual(Integer min,Pageable pageable);

    Page<Product> findByPriceLessThanEqual(Integer max,Pageable pageable);

    Product findByNameIgnoreCase(String name);
}
