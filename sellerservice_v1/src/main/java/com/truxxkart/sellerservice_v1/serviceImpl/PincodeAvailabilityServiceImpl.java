package com.truxxkart.sellerservice_v1.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.PincodeAvailability;
import com.truxxkart.sellerservice_v1.entity.User;
import com.truxxkart.sellerservice_v1.repository.PincodeAvailabilityRepository;
import com.truxxkart.sellerservice_v1.repository.SellerRepository;
import com.truxxkart.sellerservice_v1.service.PincodeAvailabilityService;

@Service
public class PincodeAvailabilityServiceImpl implements PincodeAvailabilityService {
	@Autowired
	private SellerRepository sellerRepo;

	@Autowired
	private PincodeAvailabilityRepository pcaRepo;

	@Override
	public PincodeAvailability createPincodeAvailability(Long userId, PincodeAvailability pca) {
		Optional<User> user = sellerRepo.findById(userId);
		if (user.isPresent()) {
			pca.setSeller(user.get());
			PincodeAvailability pcaCreated = pcaRepo.save(pca);
			return pcaCreated;
		}
	
		return null;
	}

	@Override
	public List<PincodeAvailability> getAllPincodeAvailability() {
		 List<PincodeAvailability> pcaList = pcaRepo.findAll();
		return pcaList;
	}

}
