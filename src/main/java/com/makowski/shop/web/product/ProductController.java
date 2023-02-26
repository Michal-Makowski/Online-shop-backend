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
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.product.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "2.1 Product")
@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    
    private ProductService productService;

    @PostMapping("/productCategory/{productCategoryId}")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.EMPLOYEE +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<Product> createProduct(@PathVariable Long productCategoryId, @Valid @RequestBody Product product){
        return new ResponseEntity<>(productService.createProduct(product, productCategoryId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/productCategory/{productCategoryId}")
    public ResponseEntity<List<Product>> getByProductCategoryId(@PathVariable Long productCategoryId){
        return new ResponseEntity<>(productService.findByProductCategoryId(productCategoryId), HttpStatus.OK);
    }

    @GetMapping("/productCategory/{productCategoryId}/productRate/{productRateId}")
    public ResponseEntity<List<Product>> getByProductCategoryIdAndProductRatesId(@PathVariable Long productCategoryId, @PathVariable Long productRateId){
        return new ResponseEntity<>(productService.findByProductCategoryIdAndProductRatesId(productCategoryId, productRateId), HttpStatus.OK);
    }

    @GetMapping("/productName/{productName}")
    public ResponseEntity<List<Product>> getByName (@PathVariable String productName){
        return new ResponseEntity<>(productService.findByProductName(productName), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.EMPLOYEE +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/productCategory/{productCategoryId}")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.EMPLOYEE +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @PathVariable Long productCategoryId, @RequestBody Product product){
        return new ResponseEntity<>(productService.updateProduct(id, productCategoryId, product) , HttpStatus.OK);
    }
}
