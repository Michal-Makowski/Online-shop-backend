package com.makowski.shop.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.Product;
import com.makowski.shop.entity.product.ProductRate;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.product.ProductRateRepository;
import com.makowski.shop.repository.product.ProductRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProductRateService {
    
    private ProductRateRepository productRateRepository;
    private ProductRepository productRepository;

        
    public ProductRate createProductRate(ProductRate productRate){
        return productRateRepository.save(productRate);
    }

    public ProductRate addRateToProduct(Long id, Long productId){
        ProductRate productRate = productRateRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ProductRate.class));
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException(productId, Product.class));
        productRate.getProducts().add(product);
        return productRateRepository.save(productRate);
    }

    public ProductRate getProductRate(Long id){
        return productRateRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ProductRate.class));
    }

    public List<Product> getProductByRateId(Long id){
        ProductRate productRate = productRateRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ProductRate.class));
        return productRate.getProducts();
    }


    public List<ProductRate> getAllProductRate(){
        return productRateRepository.findAll();
    }

    public void deleteProductRate(Long id){
        productRateRepository.deleteById(id);
    }

    public ProductRate updateProductRate(Long id, ProductRate productRate){
        ProductRate updateProductRate = productRateRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ProductRate.class));
        updateProductRate.setRate(productRate.getRate());
        return productRateRepository.save(updateProductRate);
    }

}
