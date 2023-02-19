package com.makowski.shop.service.product.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.Product;
import com.makowski.shop.entity.product.ProductRate;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.product.ProductRateRepository;
import com.makowski.shop.service.product.ProductService;
import com.makowski.shop.service.product.ProductRateService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProductRateServiceImpl implements ProductRateService{
    
    private ProductRateRepository productRateRepository;
    private ProductService productService;

    
    @Override
    public ProductRate createProductRate(ProductRate productRate){
        return productRateRepository.save(productRate);
    }

    @Override
    public ProductRate getProductRate(Long id){
        return productRateRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ProductRate.class));
    }

    @Override
    public ProductRate addRateToProduct(Long id, Long productId){
        ProductRate productRate = getProductRate(id);
        Product product = productService.getProduct(productId);
        productRate.getProducts().add(product);
        return productRateRepository.save(productRate);
    }

    
    @Override
    public List<Product> getProductByRateId(Long id){
        ProductRate productRate = getProductRate(id);
        return productRate.getProducts();
    }


    @Override
    public List<ProductRate> getAllProductRate(){
        return productRateRepository.findAll();
    }

    @Override
    public void deleteProductRate(Long id){
        productRateRepository.deleteById(id);
    }
    
    @Override
    public ProductRate updateProductRate(Long id, ProductRate productRate){
        ProductRate updateProductRate = getProductRate(id);
        updateProductRate.setRate(productRate.getRate());
        return productRateRepository.save(updateProductRate);
    }

}
