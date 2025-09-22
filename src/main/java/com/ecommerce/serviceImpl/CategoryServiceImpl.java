package com.ecommerce.serviceImpl;

import com.ecommerce.DTOs.CategoryDTO;
import com.ecommerce.Mapping.CategoryMapping;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryServices {

    private CategoryRepository categoryRepository;
    private CategoryMapping categoryMapping;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapping categoryMapping) {
        this.categoryRepository = categoryRepository;
        this.categoryMapping = categoryMapping;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        try {
            Category category = categoryMapping.CategoryDTOToCategory(categoryDTO);
            Category savedCategory = categoryRepository.save(category);
            return categoryMapping.CategoryToCategoryDTO(savedCategory);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create category: " + ex.getMessage(), ex);
        }
    }

    @Override
    public CategoryDTO findCategoryByName(String name) {
        try {
            Category category = categoryRepository.findByName(name);
            return categoryMapping.CategoryToCategoryDTO(category);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to fetch category by name: " + ex.getMessage());
        }
    }

    @Override
    public CategoryDTO findCategoryById(Long id) {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
            return categoryMapping.CategoryToCategoryDTO(category);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to find category by id: " + ex.getMessage(), ex);
        }
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

            category.setName(categoryDTO.getName());
            // update only name
            Category updatedCategory = categoryRepository.save(category);

            return categoryMapping.CategoryToCategoryDTO(updatedCategory);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to update category: " + ex.getMessage(), ex);
        }
    }

    public void deleteCategoryById(Long id) {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

            categoryRepository.deleteById(id);
            categoryMapping.CategoryToCategoryDTO(category); // return deleted one
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete category: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<CategoryDTO> fetAllCategories() {
        try{
         List<Category> categoryList= categoryRepository.findAll();
         List<CategoryDTO> dtoList=categoryList.stream().map(categoryMapping::CategoryToCategoryDTO).collect(Collectors.toList());
            return dtoList;
        }catch (Exception ex){
            throw new RuntimeException("unable to fetch categories :"+ ex.getMessage());
        }
    };


}
