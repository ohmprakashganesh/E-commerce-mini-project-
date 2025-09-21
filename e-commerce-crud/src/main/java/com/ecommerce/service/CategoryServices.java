package com.ecommerce.service;

import com.ecommerce.DTOs.CategoryDTO;

import java.util.List;

public interface CategoryServices {

    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO findCategoryByName(String name);
    CategoryDTO findCategoryById(Long id);
    CategoryDTO updateCategory(Long id,  CategoryDTO categoryDTO);
    void deleteCategoryById(Long id);
    List<CategoryDTO> fetAllCategories();






}
