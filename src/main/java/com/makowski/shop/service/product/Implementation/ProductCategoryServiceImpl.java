package com.makowski.shop.service.product.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.ProductCategory;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.product.ProductCategoryRepository;
import com.makowski.shop.service.product.ProductCategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
    
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory createProductCategory(ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

    public ProductCategory getProductCategory(Long id){
        return productCategoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ProductCategory.class));
    }

    public List<ProductCategory> getAllProductCategory(){
        return productCategoryRepository.findAll();
    }

    public void deleteProductCategory(Long id){
        productCategoryRepository.deleteById(id);
    }

    public ProductCategory updateProductCategory(Long id, ProductCategory productCategory){
        ProductCategory updateProductCategory = getProductCategory(id);
        updateProductCategory.setCategoryName(productCategory.getCategoryName());
        return productCategoryRepository.save(updateProductCategory);
    }
}
