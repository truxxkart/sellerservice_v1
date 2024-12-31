package com.truxxkart.sellerservice_v1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truxxkart.sellerservice_v1.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
   Optional<List<Review>> findByUserId(Long userId);
}
