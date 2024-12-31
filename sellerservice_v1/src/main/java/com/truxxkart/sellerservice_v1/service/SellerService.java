package com.truxxkart.sellerservice_v1.service;

import java.time.LocalDate;
import java.util.List;

import com.truxxkart.sellerservice_v1.entity.User;

public interface SellerService {
	  public User createSeller(User seller);
	   public List<User> getAllSeller();
	   public User getSellerById(Long id);
//	   public User updateSeller(User seller);
//	   public User findByEmail(String email);
	   
	   public User findByfield(String field, String findBy);

		public List<User> findUserByVerificationOrActivation(String field, Boolean findBy);

		public List<User> findUserByCreationDate(LocalDate data);
		public List<User> findUserByRoleOrType(String field, String role);

		public User updateUserProfile(Long userId, String field, String toBeUpdated);

		public User updateUserStatus(Long userId, String field, Boolean toBeUpdated);
}
