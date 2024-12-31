package com.truxxkart.sellerservice_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.truxxkart.sellerservice_v1.entity.PincodeAvailability;
import com.truxxkart.sellerservice_v1.service.PincodeAvailabilityService;

@RestController
@RequestMapping("/pincodes")
public class PincodeAvailabilityController {
	@Autowired
	private PincodeAvailabilityService pcaService;
	
	@PostMapping("/id/{sellerId}")
	public ResponseEntity<PincodeAvailability> reateAddress(@PathVariable Long sellerId ,  @RequestBody PincodeAvailability pca){
		
		PincodeAvailability newPCA=pcaService.createPincodeAvailability(sellerId, pca);
		return ResponseEntity.status(HttpStatus.OK).body(newPCA);
	}
	
	@GetMapping()
	public ResponseEntity<List<PincodeAvailability>> getAllAddress(){
		List<PincodeAvailability> pcaList =pcaService.getAllPincodeAvailability();
		return ResponseEntity.status(HttpStatus.OK).body(pcaList);
	}
}
