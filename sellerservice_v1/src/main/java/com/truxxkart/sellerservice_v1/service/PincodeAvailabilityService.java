package com.truxxkart.sellerservice_v1.service;

import java.util.List;


import com.truxxkart.sellerservice_v1.entity.PincodeAvailability;

public interface PincodeAvailabilityService {
	 public PincodeAvailability createPincodeAvailability(Long userId, PincodeAvailability pca);
	   public List<PincodeAvailability> getAllPincodeAvailability();
}
