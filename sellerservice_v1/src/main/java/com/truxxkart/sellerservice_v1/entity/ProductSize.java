package com.truxxkart.sellerservice_v1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JsonIgnore
    private ProductVariant productVariant;

    @Column(nullable = false, length = 20)
    private String size;

   
    @Column(nullable = false)
    private Double additionalPrice;

    @Column(nullable = false)
    private Double additionalDiscountedPrice;

    
    @Column(nullable = false)
    private Integer stock;

    private Boolean isAvailable = true;
    private Boolean isActive=false;
    // Getters and Setters
}
