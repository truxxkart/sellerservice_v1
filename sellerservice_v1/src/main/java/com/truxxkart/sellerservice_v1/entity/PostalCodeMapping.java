package com.truxxkart.sellerservice_v1.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class PostalCodeMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String sourcePincode; // Shared source postal code

   
    @Column(nullable = false)
    private Set<String> destinationPincodes = new HashSet<String>(); // Multiple destinations associated with the source
    
    private Boolean isActive = true;

	private LocalDateTime createdAt;
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


    // Constructors, Getters, Setters, and toString() omitted for brevity
}
