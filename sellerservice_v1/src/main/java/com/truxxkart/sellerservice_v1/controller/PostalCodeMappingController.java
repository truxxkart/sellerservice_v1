package com.truxxkart.sellerservice_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.truxxkart.sellerservice_v1.entity.PostalCodeMapping;
import com.truxxkart.sellerservice_v1.service.PostalCodeMappingService;

@RestController
@RequestMapping("/postal-codes")
public class PostalCodeMappingController {
   @Autowired
	private PostalCodeMappingService pcmService;
	
	@PostMapping("/creation")
	public ResponseEntity<PostalCodeMapping> createPostalCodeMapping(@RequestBody PostalCodeMapping pcm){
		
		PostalCodeMapping newPostalCodeMapping=pcmService.createPostalCodeMapping(pcm);
		return ResponseEntity.status(HttpStatus.OK).body(newPostalCodeMapping);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PostalCodeMapping>> getAllPostalCodeMapping(){
		List<PostalCodeMapping> pcmList =pcmService.getAllPostalCodeMapping();
		return ResponseEntity.status(HttpStatus.OK).body(pcmList);
	}
	
	@GetMapping("/source/{sourcePostalCode}")
	public ResponseEntity<PostalCodeMapping> getPostalCodeMappingBySourcePostalCode(@PathVariable String sourcePostalCode){
		PostalCodeMapping pcm =pcmService.getPostalCodeMappingBySourcePostalCode(sourcePostalCode);
		return ResponseEntity.status(HttpStatus.OK).body(pcm);
	}
	
	@PutMapping("/update/active")
	public ResponseEntity<PostalCodeMapping> updatePostalCodeMappingIsActive(@RequestParam String sourcePostalCode,@RequestParam Boolean toBeUpdated){
		PostalCodeMapping pcm =pcmService.updatePostalCodeMappingIsActive(sourcePostalCode, toBeUpdated);
		return ResponseEntity.status(HttpStatus.OK).body(pcm);
	}
	
	@PutMapping("/update/destination")
	public ResponseEntity<PostalCodeMapping> updatePostalCodeMappingDestinationPotalCode(@RequestParam String sourcePostalCode,@RequestParam String field,@RequestParam String toBeUpdated){
		PostalCodeMapping pcm =pcmService.updatePostalCodeMappingDestinationPotalCode(sourcePostalCode, field, toBeUpdated);
		return ResponseEntity.status(HttpStatus.OK).body(pcm);
	}
	
}
