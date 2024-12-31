package com.truxxkart.sellerservice_v1.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.truxxkart.sellerservice_v1enums.OcassionEnumType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Product {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String description;
	    
	    @ManyToOne
	    @JoinColumn(name = "category_id" )
	    private Category category;

	    @ManyToOne
	    @JoinColumn(name = "brand_id")
	    private Brand brand;
	    @JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "seller_id")
	    private User seller;
	    @JsonIgnore
	    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	    private List<Review> reviews;
	    @NotNull
	    private Double price;
        private Long saleCount;
        private Long monthSaleCount;
        private Long weekSaleCount;
	    private Double rating;
	    
	    @ManyToMany( mappedBy = "product", cascade =CascadeType.ALL)
//	    @JoinTable(
//	        name = "product_tag_mapping",
//	        joinColumns = @JoinColumn(name = "product_id"),
//	        inverseJoinColumns = @JoinColumn(name = "tag_id")
//	    )
	    private List<ProductTag> tags =new ArrayList<ProductTag>();
	    
	    @Enumerated(EnumType.STRING)
	    private OcassionEnumType ocassions;
	    
	    
	    private Boolean isActive = true;

	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;

	    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	    private List<ProductVariant> variants;
	    
	    @PrePersist
	    private void onCreate() {
	        this.createdAt = LocalDateTime.now();
	        this.updatedAt = LocalDateTime.now();
	    }

	    @PreUpdate
	    private void onUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }
	   
}