package com.makowski.shop.entity.product;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.makowski.shop.validation.ValidationConstans;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@Data
@NoArgsConstructor
@Table(name = "product_category")
public class ProductCategory {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Size(min = 2, max = 50, message = ValidationConstans.NOT_SIZE)
    @Pattern(regexp = ValidationConstans.PATTERN_AZ09, message = ValidationConstans.NO_MATCH_PATTERN_AZ09)
    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

    
    @JsonIgnore
    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private List<Product> products;
}
