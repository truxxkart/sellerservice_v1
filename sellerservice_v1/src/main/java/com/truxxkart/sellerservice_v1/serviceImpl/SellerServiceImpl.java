package com.truxxkart.sellerservice_v1.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.User;
import com.truxxkart.sellerservice_v1.repository.SellerRepository;
import com.truxxkart.sellerservice_v1.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
    
	@Autowired
	SellerRepository sellerRepo;
	
	@Override
	public User createSeller(User seller) {
		User createdSeller =sellerRepo.save(seller);
		return createdSeller;
	}

	@Override
	public List<User> getAllSeller() {
		List<User> allSeller = sellerRepo.findAll();
		return allSeller;
	}

	@Override
	public User getSellerById(Long id) {
		User sellerById = sellerRepo.findById(id).get();
	return sellerById;
	}

//	@Override
//	public User updateSeller(User seller) {
//		User mySeller = sellerRepo.findById(seller.getId()).get();
//		if(mySeller!=null) {
//			mySeller=sellerRepo.save(seller);
//		}
//		return mySeller;
//	}
//
//	@Override
//	public User findByEmail(String email) {
//		return sellerRepo.findByEmail(email).get();
//	}
	
	@Override
	public User findByfield(String field, String findBy) {
		if (field.equalsIgnoreCase("EMAIL")) {
			Optional<User> user = sellerRepo.findByEmail(findBy);
			if (user.isPresent()) {
				return user.get();
			}
		} else if (field.equalsIgnoreCase("PHONE")) {
			Optional<User> user = sellerRepo.findByPhone(findBy);
			if (user.isPresent()) {
				return user.get();
			}
		} else if (field.equalsIgnoreCase("GSTIN")) {
			Optional<User> user = sellerRepo.findByGstin(findBy);
			if (user.isPresent()) {
				return user.get();
			}
		}
		
		return null;
	}

	@Override
	public List<User> findUserByVerificationOrActivation(String field, Boolean findBy) {
		if (field.equalsIgnoreCase("VERIFICATION")) {
			Optional<List<User>> userList = sellerRepo.findByIsVerified(findBy);
			if (userList.isPresent()) {
				return userList.get();
			}
		} else if (field.equalsIgnoreCase("ACTIVATION")) {
			Optional<List<User>> userList = sellerRepo.findByIsActive(findBy);
			if (userList.isPresent()) {
				return userList.get();
			}
		}
		return null;
	}

	@Override
	public List<User> findUserByCreationDate(LocalDate data) {
		return sellerRepo.findAll().stream().filter(user -> user.getCreatedAt().toLocalDate().isEqual(data))
				.collect(Collectors.toList());

	}

	@Override
	public User updateUserProfile(Long userId, String field, String toBeUpdated) {
		Optional<User> optUser = sellerRepo.findById(userId);
		if (optUser.isPresent()) {
			User user = optUser.get();

			if (field.equalsIgnoreCase("NAME")) {
				user.setName(toBeUpdated);
			} else if (field.equalsIgnoreCase("EMAIL")) {
				user.setEmail(toBeUpdated);
			} else if (field.equalsIgnoreCase("PHONE")) {
				user.setPhone(toBeUpdated);
			} else if (field.equalsIgnoreCase("PASSWORDHASH")) {
				user.setPasswordHash(toBeUpdated);
			} 
			else if (field.equalsIgnoreCase("ROLE")) {
				user.setRole(toBeUpdated);
			} 
			else if (field.equalsIgnoreCase("USERTYPE")) {
				user.setUserType(toBeUpdated);
			} 
			else if (field.equalsIgnoreCase("GSTIN")) {
				user.setGstin(toBeUpdated);
			} 
			else if (field.equalsIgnoreCase("PINCODE")) {
				user.setPinCode(toBeUpdated);;
			} 

			return sellerRepo.save(user);

		}
		return null;
	}

	@Override
	public User updateUserStatus(Long userId, String field, Boolean toBeUpdated) {
		Optional<User> optUser = sellerRepo.findById(userId);
		if (optUser.isPresent()) {
			User user = optUser.get();

			if (field.equalsIgnoreCase("VERIFICATION")) {
				user.setIsVerified(toBeUpdated);
			} else if (field.equalsIgnoreCase("ACTIVATION")) {
				user.setIsActive(toBeUpdated);
			}

			return sellerRepo.save(user);

		}
		return null;
	}


	@Override
	public List<User> findUserByRoleOrType(String field, String findBy) {
		if (field.equalsIgnoreCase("ROLE")) {
			return sellerRepo.findByRole(findBy).get();
		} else if (field.equalsIgnoreCase("USERTYPE")) {
			return sellerRepo.findByUserType(findBy).get();
		}

		return null;
	}


}