package com.truxxkart.sellerservice_v1.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@Entity
public class ProductTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(unique = true)
    private String name;

 
    private String type; // Attribute, Seasonal, Promotion, etc.
    private String slug; // new arrival ,eco-friendly ,max-discount
 
    private String description;

    @ManyToMany
    @JsonIgnore
    private List<Product> product =new ArrayList<Product>();
    
    private Boolean visibility = true;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
