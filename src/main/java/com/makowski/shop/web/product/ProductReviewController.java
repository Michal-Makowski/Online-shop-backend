package com.makowski.shop.web.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makowski.shop.entity.product.ProductReview;
import com.makowski.shop.service.product.ProductReviewService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/productReview")
public class ProductReviewController {
    
    private ProductReviewService productReviewService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<ProductReview> createProductReview(@PathVariable Long productId, @Valid @RequestBody ProductReview productReview){
        return new ResponseEntity<>(productReviewService.createProductReview(productId ,productReview), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReview> getProductReview(@PathVariable Long id){
        return new ResponseEntity<>(productReviewService.getProductReview(id), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductReview>> findByProductId(@PathVariable Long productId){
        return new ResponseEntity<>(productReviewService.findByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductReview>> getAllProductReviews(){
        return new ResponseEntity<>(productReviewService.getAllProductReview(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductReview> deleteProductReview(@PathVariable Long id){
        productReviewService.deleteProductReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductReview> updateProductReview (@PathVariable Long id, @RequestBody ProductReview productReview){
        return new ResponseEntity<>(productReviewService.updateProductReview(id, productReview) , HttpStatus.OK);
    }
}


