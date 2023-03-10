package com.makowski.shop.service.product.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.Product;
import com.makowski.shop.entity.product.ProductCategory;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.product.ProductRepository;
import com.makowski.shop.service.product.ProductCategoryService;
import com.makowski.shop.service.product.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    
    private ProductRepository productRepository;
    private ProductCategoryService productCategoryService;

    @Override
    public Product createProduct(Product product, Long productCategoryId){
        ProductCategory productCategory = productCategoryService.getProductCategory(productCategoryId);
        product.setProductCategory(productCategory);
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id){
        return productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Product.class));
    }

    @Override
    public List<Product> findByProductCategoryId(Long productCategoryId){
        return productRepository.findByProductCategoryId(productCategoryId);
    }

    @Override
    public List<Product> findByProductCategoryIdAndProductRatesId(Long productCategoryId, Long productRateId){
        return productRepository.findByProductCategoryIdAndProductRatesId(productCategoryId, productRateId);
    }

    @Override
    public List<Product> findByProductName(String productName){
        return productRepository.findByNameContainingIgnoreCase(productName);
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);          
    }

    @Override
    public Product updateProduct(Long id, Long productCategoryId, Product product){
        Product updateProduct =  getProduct(id);
        ProductCategory productCategory = productCategoryService.getProductCategory(productCategoryId);
        updateProduct.setProductCategory(productCategory);        
        updateProduct.setName(product.getName());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setQuantity(product.getQuantity());
        return productRepository.save(updateProduct);
    }
}
