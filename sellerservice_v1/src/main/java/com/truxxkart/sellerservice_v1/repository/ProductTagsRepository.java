package com.truxxkart.sellerservice_v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truxxkart.sellerservice_v1.entity.ProductTag;

public interface ProductTagsRepository extends JpaRepository<ProductTag, Long> {
     ProductTag findByName(String name);
     List<ProductTag> findByType(String type);
     List<ProductTag> findBySlug(String slug);
}
