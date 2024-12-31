package com.truxxkart.sellerservice_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxxkart.sellerservice_v1.entity.PincodeAvailability;

@Repository
public interface PincodeAvailabilityRepository extends JpaRepository<PincodeAvailability, Long> {

}
