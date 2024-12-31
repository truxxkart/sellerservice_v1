package com.truxxkart.sellerservice_v1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.truxxkart.sellerservice_v1.service.CloudinaryService;


@RestController
@RequestMapping("/images")
public class CloudinaryController {
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
		String imgUrl;
		try {
			imgUrl = cloudinaryService.uploadImage(file);
			return ResponseEntity.ok(imgUrl);
		} catch (IOException e) {
			  return ResponseEntity.badRequest().body("Image upload failed: " + e.getMessage());
		}
		
	}
	
	 @DeleteMapping("/delete")
	    public ResponseEntity<String> deleteImage(@RequestParam String imgUrl) {
	        try {
	    		String newStr=imgUrl.substring(61);
	    		String publicId =newStr.substring(0,newStr.length()-4);
	            String result = cloudinaryService.deleteImage(publicId);
	            return ResponseEntity.ok("Image deletion result: " + result);
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Image deletion failed: " + e.getMessage());
	        }

}
}