package com.truxxkart.sellerservice_v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxxkart.sellerservice_v1.entity.Brand;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
       Optional<Brand> findByName(String name);
}
