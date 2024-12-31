package com.truxxkart.sellerservice_v1.controller;

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

import com.truxxkart.sellerservice_v1.entity.Address;
import com.truxxkart.sellerservice_v1.entity.User;
import com.truxxkart.sellerservice_v1.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addService;
	
	@PostMapping("/id/{sellerId}")
	public ResponseEntity<Address> createAddress(@PathVariable Long sellerId ,  @RequestBody Address address){
		
		Address newAdd=addService.createAddress(sellerId, address);
		return ResponseEntity.status(HttpStatus.OK).body(newAdd);
	}
	
	@GetMapping()
	public ResponseEntity<List<Address>> getAllAddress(){
		List<Address> addList =addService.getAllAddress();
		return ResponseEntity.status(HttpStatus.OK).body(addList);
	}
	
	@GetMapping("/seller/id/{sellerId}")
	public ResponseEntity<List<Address>> getAddressBySellerId(@PathVariable Long sellerId){
		List<Address> addList =addService.getAddressBySellerId(sellerId);
		return ResponseEntity.status(HttpStatus.OK).body(addList);
	}
	
	@PutMapping("/update/status/{sellerId}/{addressId}")
	public ResponseEntity<Address> updateAddressStatus(@PathVariable Long sellerId , @PathVariable Long addressId , @RequestParam String field ,@RequestParam Boolean toBeUpdated){
		
		Address newAdd=addService.updateAddressStatus(sellerId, addressId, field, toBeUpdated);
		return ResponseEntity.status(HttpStatus.OK).body(newAdd);
	}
	@PutMapping("/update/field/{addressId}")
	public ResponseEntity<Address> updateAddressField(@PathVariable Long addressId , @RequestParam String field ,@RequestParam String toBeUpdated){
		
		Address newAdd=addService.updateAddressField(addressId, field, toBeUpdated);
		return ResponseEntity.status(HttpStatus.OK).body(newAdd);
	}
	
	@DeleteMapping("/delete/id/{addressId}")
public ResponseEntity<String> deleteAddressById(@PathVariable Long addressId){
		
		String res = addService.deleteAddressById(addressId);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
