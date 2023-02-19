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

import com.makowski.shop.entity.product.ProductCategory;
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.product.ProductCategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    
    private ProductCategoryService productCategoryService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.EMPLOYEE +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<ProductCategory> createProductCategory(@Valid @RequestBody ProductCategory productCategory){
        return new ResponseEntity<>(productCategoryService.createProductCategory(productCategory), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategory(@PathVariable Long id){
        return new ResponseEntity<>(productCategoryService.getProductCategory(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductCategory>> getAllProductCategory(){
        return new ResponseEntity<>(productCategoryService.getAllProductCategory(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.EMPLOYEE +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<ProductCategory> deleteProductCategory(@PathVariable Long id){
        productCategoryService.deleteProductCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.EMPLOYEE +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<ProductCategory> updateProductCategory (@PathVariable Long id, @RequestBody ProductCategory productCategory){
        return new ResponseEntity<>(productCategoryService.updateProductCategory(id, productCategory) , HttpStatus.OK);
    }
}


