package com.makowski.shop.entity.product;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.makowski.shop.validation.ValidationConstans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product_rate")
public class ProductRate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @DecimalMin(value = "0.0", message = ValidationConstans.NOT_MIN)
    @Column(name = "rate", nullable = false, unique = true)
    private double rate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "product_product_rate",
        joinColumns = @JoinColumn(name = "product_rate_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> products;
   
}
