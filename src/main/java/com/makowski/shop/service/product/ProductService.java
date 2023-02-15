package com.makowski.shop.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.Product;
import com.makowski.shop.entity.product.ProductCategory;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.product.ProductRepository;
import com.makowski.shop.repository.product.ProductCategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService {
    
    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;

    public Product createProduct(Product product, Long productCategoryId){
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
            .orElseThrow(() -> new EntityNotFoundException(productCategoryId, ProductCategory.class));
        product.setProductCategory(productCategory);
        return productRepository.save(product);
    }

    public Product getProduct(Long id){
        return productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Product.class));
    }

    public List<Product> findByProductCategoryId(Long productCategoryId){
        return productRepository.findByProductCategoryId(productCategoryId);
    }

    public List<Product> findByProductCategoryIdAndProductRatesId(Long productCategoryId, Long productRateId){
        return productRepository.findByProductCategoryIdAndProductRatesId(productCategoryId, productRateId);
    }

    public List<Product> findByProductName(String productName){
        return productRepository.findByNameContainingIgnoreCase(productName);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);          
    }

    public Product updateProduct(Long id, Long productCategoryId, Product product){
        Product updateProduct =  productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Product.class));
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
            .orElseThrow(() -> new EntityNotFoundException(productCategoryId, ProductCategory.class));
        updateProduct.setProductCategory(productCategory);        
        updateProduct.setName(product.getName());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setQuantity(product.getQuantity());
        return productRepository.save(updateProduct);
    }
}
