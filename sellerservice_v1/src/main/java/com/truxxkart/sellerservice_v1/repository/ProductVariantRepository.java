package com.truxxkart.sellerservice_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truxxkart.sellerservice_v1.entity.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

}
