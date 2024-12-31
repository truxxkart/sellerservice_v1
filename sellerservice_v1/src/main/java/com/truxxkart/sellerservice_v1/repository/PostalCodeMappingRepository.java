package com.truxxkart.sellerservice_v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxxkart.sellerservice_v1.entity.PostalCodeMapping;
@Repository
public interface PostalCodeMappingRepository extends JpaRepository<PostalCodeMapping, Long> {
       Optional<PostalCodeMapping> findBySourcePincode(String sourcePostalCode);  
}
