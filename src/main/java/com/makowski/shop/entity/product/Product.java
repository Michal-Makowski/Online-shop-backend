package com.makowski.shop.entity.product;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.makowski.shop.entity.user.UserCart;
import com.makowski.shop.entity.user.UserFavoriteProducts;
import com.makowski.shop.entity.user.UserLastProducts;
import com.makowski.shop.validation.ValidationConstans;

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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Size(min = 2, max = 50, message = ValidationConstans.NOT_SIZE)
    @Pattern(regexp = ValidationConstans.PATTERN_AZ09, message = ValidationConstans.NO_MATCH_PATTERN_AZ09)
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @NonNull
    @Size(min = 1, max = 254, message = ValidationConstans.NOT_SIZE)
    @Column(name = "description", nullable = false)
    private String description;
    
    @DecimalMin(value = "0.0", message = ValidationConstans.NOT_MIN)
    @Column(name = "price", nullable = false)
    private double price; 
    
    @NonNull
    @Min(value = 0, message = ValidationConstans.NOT_MIN)   
    @NotNull(message = ValidationConstans.NOT_NULL)
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

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<UserCart> userCarts;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<UserFavoriteProducts> userFavoriteProducts;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<UserLastProducts> userLastProducts;
}
