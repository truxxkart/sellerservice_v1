package com.truxxkart.sellerservice_v1.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class ProductVariant {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String color;
	    private Double price;
	    private Double discountedPrice;
	    private List<String> imageUrl;
	    private Boolean isAvailable = true;
	    private Boolean isActive;

	    @ManyToOne
	    @JsonIgnore
//	    @JoinColumn(name = "product_id")
	    private Product product;
	    
	    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL)
	    private List<ProductSize> sizes;

}