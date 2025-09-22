package com.ecommerce.controller;

import com.ecommerce.DTOs.CategoryDTO;
import com.ecommerce.DTOs.ProductDTO;
import com.ecommerce.service.CategoryServices;
import com.ecommerce.service.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryServices categoryServices;
    public  CategoryController( CategoryServices categoryServices){
        this.categoryServices=categoryServices;

    }

        @PostMapping("/create")
        public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
            return  new ResponseEntity<>(categoryServices.createCategory(categoryDTO), HttpStatus.CREATED);
        }

        @GetMapping("/fetchByName/{name}")
        public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
            return  new ResponseEntity<>(categoryServices.findCategoryByName(name), HttpStatus.OK);
        }
    @GetMapping("/fetchById/{id}")
    public ResponseEntity<CategoryDTO>  getCategoryById (@PathVariable Long id){
        return  new ResponseEntity<>(categoryServices.findCategoryById(id), HttpStatus.OK);
    }


    @GetMapping("/fetchAll")
        public ResponseEntity<List<CategoryDTO>> getAllProducts(){
            return  new ResponseEntity<>(categoryServices.fetAllCategories(),HttpStatus.OK);
        }
        @PutMapping("/update/{id}")
        public  ResponseEntity<CategoryDTO> updateProduct(@PathVariable Long id,@RequestBody CategoryDTO categoryDTO){
            return  new ResponseEntity<>(categoryServices.updateCategory(id,categoryDTO),HttpStatus.ACCEPTED);
        }

        @DeleteMapping("/delete/{id}")
        public  ResponseEntity<String> deleteProductById(@PathVariable Long id){
            categoryServices.deleteCategoryById(id);
            return  new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }







}
