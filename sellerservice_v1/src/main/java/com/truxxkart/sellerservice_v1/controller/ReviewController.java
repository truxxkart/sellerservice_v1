package com.truxxkart.sellerservice_v1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.truxxkart.sellerservice_v1.entity.Review;
import com.truxxkart.sellerservice_v1.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/id/{productId}")
	public ResponseEntity<Review> createReview(@PathVariable Long productId, @RequestBody Review review){
		Review newReview =reviewService.createReview(productId, review);
	
		return ResponseEntity.status(HttpStatus.OK).body(newReview);
	}
	
	@GetMapping()
	public ResponseEntity<List<Review>> getAllReview(){
		List<Review> reviewList =reviewService.getAllReview();
		return ResponseEntity.status(HttpStatus.OK).body(reviewList);
	}
	@GetMapping("/product-id/{productId}")
	public ResponseEntity<List<Review>> getReviewByProductId(@PathVariable Long productId){
		List<Review> reviewList =reviewService.getReviewByProductId(productId);
		return ResponseEntity.status(HttpStatus.OK).body(reviewList);
	}
	
	@GetMapping("/id/{userId}")
	public ResponseEntity<List<Review>> getReviewByUserId(@PathVariable Long userId){
		List<Review> reviewList =reviewService.getReviewByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(reviewList);
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<Review>> getReviewByStatus(@RequestParam String field,@RequestParam Boolean findBy){
		List<Review> reviewList =reviewService.getReviewByStatus(field, findBy);
		return ResponseEntity.status(HttpStatus.OK).body(reviewList);
	}
	
	@GetMapping("/date")
	public ResponseEntity<List<Review>> getReviewByCreationDate(@RequestParam LocalDate date){
		List<Review> reviewList =reviewService.getReviewByCreationDate(date);
		return ResponseEntity.status(HttpStatus.OK).body(reviewList);
	}
	
	@PutMapping("/update/id/{reviewId}")
	public ResponseEntity<Review> updateReviewRating(@PathVariable Long reviewId, @RequestParam Integer rating){
		Review newReview =reviewService.updateReviewRating(reviewId, rating);
	
		return ResponseEntity.status(HttpStatus.OK).body(newReview);
	}
	
	@PutMapping("/update/comment/id/{reviewId}")
	public ResponseEntity<Review> updateReviewText(@PathVariable Long reviewId, @RequestParam String reviewText){
		Review newReview =reviewService.updateReviewText(reviewId, reviewText);
	
		return ResponseEntity.status(HttpStatus.OK).body(newReview);
	}
	
	@PutMapping("/update/status/id/{reviewId}")
	public ResponseEntity<Review> updateReviewVerificationOrActive(@PathVariable Long reviewId, @RequestParam String field, @RequestParam Boolean toBeUpdated){
		Review newReview =reviewService.updateReviewVerificationOrActive(reviewId, field, toBeUpdated);
	
		return ResponseEntity.status(HttpStatus.OK).body(newReview);
	}
	@DeleteMapping("/delete/id/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
		String delMsg =reviewService.deleteReview(reviewId);
	
		return ResponseEntity.status(HttpStatus.OK).body(delMsg);
	}
}
