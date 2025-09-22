package com.ecommerce.controller;

import com.ecommerce.DTOs.PageResponse;
import com.ecommerce.DTOs.ProductDTO;
import com.ecommerce.entities.Product;
import com.ecommerce.service.ProductServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    @GetMapping("/fetchByName/{name}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String name){
        return  new ResponseEntity<>(productServices.getByName(name), HttpStatus.OK);
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
    @GetMapping("/fetchAll")
    public ResponseEntity<PageResponse<ProductDTO>> getAllProducts( @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size){
        Page<ProductDTO> productPage  =  productServices.getAllProducts(page,size);
        PageResponse response= new PageResponse(
                productPage.getContent(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()

        );
        if(productPage.isEmpty()){
        return     ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }else{
            return  ResponseEntity.ok(response);
        }


    }
    @GetMapping("/filter/category-price")
    public ResponseEntity< PageResponse<ProductDTO>> filterByCategoryAndPrice(@RequestParam(required = false) String category,
                                                             @RequestParam(required = false) Integer min,
                                                             @RequestParam(required = false) Integer max,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(defaultValue = "name") String sortBy,
                                                             @RequestParam(defaultValue = "asc") String sortDir) {
       Page<ProductDTO> productPage= productServices.filterByCategoryAndPrice(category, min, max, page, size, sortBy, sortDir);
             //return by mapping to pageDto class
        PageResponse<ProductDTO> response= new PageResponse<>(
               productPage.getContent(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
           if(productPage.isEmpty()){
               return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
           }else{
               return  ResponseEntity.ok(response);
           }
    }






}
