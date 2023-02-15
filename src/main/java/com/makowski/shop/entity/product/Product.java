package com.makowski.shop.entity.product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.makowski.shop.validation.ValidationConstans;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @NotBlank(message = ValidationConstans.PRODUCT_NAME_NOT_BLANK)
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @NonNull
    @NotBlank(message = ValidationConstans.PRODUCT_DESCRIPTION_NOT_BLANK)
    @Column(name = "description", nullable = false)
    private String description;
    
    @NonNull
    @Min(value = 0, message = ValidationConstans.PRODUCT_PRICE_NOT_MIN)
    @NotNull(message = ValidationConstans.PRODUCT_PRICE_NOT_NULL)
    @Column(name = "price", nullable = false)
    private double price;
    
    @NonNull
    @Min(value = 0, message = ValidationConstans.PRODUCT_QUANTITY_NOT_MIN)
    @NotNull(message = ValidationConstans.PRODUCT_QUANTITY_NOT_NULL)
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    private ProductCategory productCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductReview> productReviews;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<ProductRate> productRates;
}
