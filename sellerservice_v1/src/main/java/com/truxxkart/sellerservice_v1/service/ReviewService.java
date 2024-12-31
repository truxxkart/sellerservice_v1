package com.truxxkart.sellerservice_v1.service;

import java.time.LocalDate;
import java.util.List;
import com.truxxkart.sellerservice_v1.entity.Review;

public interface ReviewService {
	
	public Review createReview(Long productId ,Review review);
	public List<Review> getAllReview();
	public List<Review> getReviewByProductId(Long productId);
	public Review updateReviewRating(Long reviewId,int rating);
	public Review updateReviewText(Long reviewId,String reviewText);
	public Review updateReviewVerificationOrActive(Long reviewId,String field,Boolean toBeUpdated);
	public String deleteReview(Long reviewId);
	public List<Review> getReviewByUserId(Long userId);
	public List<Review> getReviewByStatus(String field,Boolean findBy);
	public List<Review> getReviewByCreationDate(LocalDate date);

}
