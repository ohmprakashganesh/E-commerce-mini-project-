package com.ecommerce.controller;

import com.ecommerce.DTOs.ProductDTO;
import com.ecommerce.entities.Product;
import com.ecommerce.service.ProductServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServices productServices;

    public  ProductController(ProductServices productServices){this.productServices=productServices;};

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product){
         return  new ResponseEntity<>(productServices.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        return  new ResponseEntity<>(productServices.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return  new ResponseEntity<>(productServices.getAllProducts(),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO){
        return  new ResponseEntity<>(productServices.updateProduct(id,productDTO),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteProductById(@PathVariable Long id){
        productServices.deleteProduct(id);
        return  new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }

    // filter APIs
//    @GetMapping("/filter/category")
//    public List<ProductDTO> filterByCategory(@RequestParam String category) {
//        return productServices.filterByCategory(category);
//    }
//
//    // Filter by price range
//    @GetMapping("/filter/price")
//    public List<ProductDTO> filterByPrice(@RequestParam int min, @RequestParam int max) {
//        return productServices.filterByPriceRange(min, max);
//    }

    // Filter by category & price
    @GetMapping("/filter/category-price")
    public List<ProductDTO> filterByCategoryAndPrice(@RequestParam(required = false) String category,
                                                  @RequestParam(required = false) Integer min,
                                                  @RequestParam (required = false)Integer max,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "20") int size,
                                                     @RequestParam(defaultValue = "name") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return productServices.filterByCategoryAndPrice(category, min, max,page,size,sortBy,sortDir);
    }





}
