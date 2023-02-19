package com.makowski.shop.web.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makowski.shop.entity.product.Product;
import com.makowski.shop.entity.product.ProductRate;
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.product.ProductRateService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/productRate")
public class ProductRateController {
    
    private ProductRateService productRateService;

    @PostMapping("/")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<ProductRate> createProductRate( @Valid @RequestBody ProductRate productRate){
        return new ResponseEntity<>(productRateService.createProductRate(productRate), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/product/{productId}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<ProductRate> addRateToProduct(@PathVariable Long id, @PathVariable Long productId){
        return new ResponseEntity<>(productRateService.addRateToProduct(id, productId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRate> getProductRate(@PathVariable Long id){
        return new ResponseEntity<>(productRateService.getProductRate(id), HttpStatus.OK);
    }

    @GetMapping("/productByRateId/{id}")
    public ResponseEntity<List<Product>> getProductByRateId(@PathVariable Long id){
        return new ResponseEntity<>(productRateService.getProductByRateId(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductRate>> getAllProductRates(){
        return new ResponseEntity<>(productRateService.getAllProductRate(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<ProductRate> deleteProductRate(@PathVariable Long id){
        productRateService.deleteProductRate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<ProductRate> updateProductRate (@PathVariable Long id, @RequestBody ProductRate productRate){
        return new ResponseEntity<>(productRateService.updateProductRate(id, productRate) , HttpStatus.OK);
    }
}


