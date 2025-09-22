package com.ecommerce.serviceImpl;

import com.ecommerce.DTOs.ProductDTO;
import com.ecommerce.Mapping.ProductMapping;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.exception.ObjectNotFound;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductServices;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductServices {

    private  ProductRepository productRepository;
    private   ProductMapping productMapping;
     private CategoryRepository categoryRepository;

   public  ProductServiceImpl(ProductRepository productRepository,ProductMapping productMapping,CategoryRepository categoryRepository){
       this.productMapping=productMapping;
       this.productRepository=productRepository;
       this.categoryRepository=categoryRepository;
   }
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        try{
            Product product= productMapping.DtoToProduct(productDTO);
            Category category= categoryRepository.findByName(productDTO.getCategoryName());
            product.setCategory(category);
            Product product1 = productRepository.save(product);
            return  productMapping.ProductToDto(product1);
        }catch (DataIntegrityViolationException ex) {
           //failed to meet the constraints
            throw new RuntimeException("duplication or invalid data", ex);
        } catch (Exception ex) {
            // any other exceptions
            throw new RuntimeException("An error occurred while creating the product.", ex);
        }
    }

    @Override
    public ProductDTO getProductById(Long id) {
        try{
            Optional<Product> product=productRepository.findById(id);
            if(product.isPresent()){
                return  productMapping.ProductToDto(product.get());
            }else{
                throw  new ObjectNotFound("not found object with id:"+id);
            }
        }catch (Exception ex){
            throw new RuntimeException("An error occurred while creating the product.", ex);
        }
    }

    @Override
    public ProductDTO getByName(String name) {
     Product product =  productRepository.findByNameIgnoreCase(name);
     return productMapping.ProductToDto(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        try{
            Product product= productRepository.findById(id).orElseThrow(()->new RuntimeException("not found product with id:"+id));
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            product.setCategory(categoryRepository.findByName(productDTO.getCategoryName()));

            return  productMapping.ProductToDto(productRepository.save(product));
        }catch (Exception ex){
            throw  new RuntimeException(ex.getMessage());
        }
    };

    @Override
    public void deleteProduct(Long id) {
        try{
           productRepository.deleteById(id);
        }catch(Exception ex){
            throw new RuntimeException("product not found with id:"+id + ex.getMessage());
        }
    }

    @Override
    public Page<ProductDTO> getAllProducts(int page, int size) {
        try{
            Page<Product> productPage;
            Pageable pageable=PageRequest.of(page,size);
        productPage = productRepository.findAll(pageable);

         return  productPage.map(productMapping::ProductToDto);
        }catch (Exception ex){
            throw new RuntimeException("unable to fetch "+ ex.getMessage());
        }
    }
    // filter implementation
//    @Override
//    public List<ProductDTO> filterByCategory(String name) {
//       List<Product> productList =  productRepository.findByCategory_NameIgnoreCase(name);
//      return   productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ProductDTO> filterByPriceRange(int min, int max) {
//       List<Product> productList= productRepository.findByPriceBetween(min,max);
//        return productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//
//    }
    @Override
    public Page<ProductDTO> filterByCategoryAndPrice(String category, Integer min, Integer max,
                                                     int page, int size, String sortBy, String sortDir) {

        Pageable pageable = PageRequest.of(page, size,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        Page<Product> productPage;

        if(category != null && max != null && min != null){
            productPage = productRepository.findByCategory_NameIgnoreCaseAndPriceBetween(category, min, max, pageable);
        } else if(category == null && max != null && min != null){
            productPage = productRepository.findByPriceBetween(min, max, pageable);
        } else if(category != null && max != null && min == null){
            productPage = productRepository.findByCategory_NameIgnoreCaseAndPriceLessThanEqual(category, max, pageable);
        } else if(category != null && min != null && max == null){
            productPage = productRepository.findByCategory_NameIgnoreCaseAndPriceGreaterThanEqual(category, min, pageable);
        } else if(category == null && max == null && min != null){
            productPage = productRepository.findByPriceGreaterThanEqual(min, pageable);
        } else if(category == null && min == null && max != null){
            productPage = productRepository.findByPriceLessThanEqual(max, pageable);
        } else if(category != null && min == null && max == null){
            productPage = productRepository.findByCategory_NameIgnoreCase(category, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }

        // mapping the page <Product> into page<ProductDTO>
        return productPage.map(productMapping::ProductToDto);
    }

//    public Page<ProductDTO> filterByCategoryAndPrice(String category, Integer min, Integer max, int page, int size, String sortBy, String  sortDir) {
//        Pageable pageable= PageRequest.of(
//                page,
//                size,
//                sortDir.equalsIgnoreCase("asc")
//                        ? Sort.by(sortBy).ascending()
//                        :Sort.by(sortBy).descending()
//                );
//           Page<Product> productPage;
//
//        if(category !=null && max!=null && min!=null){
//            productPage= productRepository.findByCategory_NameIgnoreCaseAndPriceBetween(category,min,max,pageable);
////            return productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//        }else if( category == null && max !=null && min!=null){
//            List<Product> productList= productRepository.findByPriceBetween(min,max);
//            return productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//        }else if(category !=null  && max!=null && min==null){
//            List<Product> productList= productRepository.findByCategory_NameIgnoreCaseAndPriceLessThanEqual(category,max);
//            return productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//        }else if(category !=null &&  min!=null && max==null ){
//            List<Product> productList= productRepository.findByCategory_NameIgnoreCaseAndPriceGreaterThanEqual(category,max);
//            return productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//        }else if(category ==null && max==null && min!=null){
//            List<Product> productList= productRepository.findByPriceGreaterThanEqual(min);
//            return productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//        }else if(category ==null && min==null && max!=null){
//            List<Product> productList= productRepository.findByPriceLessThanEqual(max);
//            return productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//        } else if (category !=null && min==null && max==null) {
//            List<Product> productList= productRepository.findByCategory_NameIgnoreCase(category);
//            return productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//
//        }else{
//            List<Product> productList= productRepository.findAll();
//            return productList.stream().map(productMapping::ProductToDto).collect(Collectors.toList());
//        }
//        return productPage.map(productMapping::ProductToDto);
//
//    }
}
