package com.truxxkart.sellerservice_v1.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.Product;
import com.truxxkart.sellerservice_v1.entity.Review;
import com.truxxkart.sellerservice_v1.repository.ProductRepository;
import com.truxxkart.sellerservice_v1.repository.ReviewRepository;
import com.truxxkart.sellerservice_v1.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
     @Autowired
     private ProductRepository prodRepo;
     @Autowired
     private ReviewRepository reviewRepo;
	@Override
	public Review createReview(Long productId ,Review review) {
		Optional<Product> optProduct =prodRepo.findById(productId);
		if(optProduct.isPresent()) {
			Product prod =optProduct.get();
			double rating =prod.getReviews().stream().mapToDouble(Review::getRating).sum()+review.getRating();
			prod.setRating(rating/(prod.getReviews().size()+1));
			prodRepo.save(prod);
			review.setProduct(optProduct.get());
			return reviewRepo.save(review);
		}
		
		return null;
	}

	@Override
	public List<Review> getAllReview() {
		List<Review> reviewList =reviewRepo.findAll();
		return reviewList;
	}

	@Override
	public List<Review> getReviewByProductId(Long productId) {
		Optional<Product> optProduct =prodRepo.findById(productId);
		if(optProduct.isPresent()) {
			List<Review> revList =optProduct.get().getReviews();
			return revList;
		}
		
		return null;
	}

	@Override
	public Review updateReviewRating(Long reviewId, int rating) {
		Optional<Review> optReview =reviewRepo.findById(reviewId);
		if(optReview.isPresent()) {
			Review review = optReview.get();
			review.setRating(rating);
			return reviewRepo.save(review);
		}
		
		return null;
	}

	@Override
	public Review updateReviewText(Long reviewId, String reviewText) {
		Optional<Review> optReview =reviewRepo.findById(reviewId);
		if(optReview.isPresent()) {
			Review review = optReview.get();
			review.setReviewText(reviewText);
			return reviewRepo.save(review);
		}
		
		return null;
	}

	@Override
	public Review updateReviewVerificationOrActive(Long reviewId, String field, Boolean toBeUpdated) {
		Optional<Review> optReview =reviewRepo.findById(reviewId);
		if(optReview.isPresent()) {
			Review review = optReview.get();
			if(field.equalsIgnoreCase("VERIFICATION")) {
				review.setIsVerified(toBeUpdated);
			} else if(field.equalsIgnoreCase("ACTIVATION")) {
				review.setIsActive(toBeUpdated);
			}
			
			return reviewRepo.save(review);
		}
		
		return null;
	}

	@Override
	public String deleteReview(Long reviewId) {
		reviewRepo.deleteById(reviewId);
		return "Review is deleted successfully";
	}

	@Override
	public List<Review> getReviewByUserId(Long userId) {
		
		Optional<List<Review>> optReviewList = reviewRepo.findByUserId(userId) ;
		if(optReviewList.isPresent()) {
			return optReviewList.get();
		}
		return null;
	}

	@Override
	public List<Review> getReviewByStatus(String field, Boolean findBy) {
	if(field.equalsIgnoreCase("VERIFICATION")) {
		 return reviewRepo.findAll().stream().filter(review->review.getIsVerified()==findBy).collect(Collectors.toList());
	} else if(field.equalsIgnoreCase("ACTIVATION")) {
		 return reviewRepo.findAll().stream().filter(review->review.getIsActive()==findBy).collect(Collectors.toList());
	} 
		return null;
	}

	@Override
	public List<Review> getReviewByCreationDate(LocalDate date) {
		
		 return reviewRepo.findAll().stream().filter(review->review.getCreatedAt().toLocalDate().equals(date)).collect(Collectors.toList());
	}

}
