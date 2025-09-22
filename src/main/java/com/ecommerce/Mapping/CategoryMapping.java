package com.ecommerce.Mapping;

import com.ecommerce.DTOs.CategoryDTO;
import com.ecommerce.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapping {

    public CategoryDTO CategoryToCategoryDTO(Category category){
        CategoryDTO categoryDTO= new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return  categoryDTO;

    }
    public Category CategoryDTOToCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId()); // optional if you want to allow updating existing category
        category.setName(categoryDTO.getName()); // map DTO field to entity field
        return category;
    }




}
