package com.truxxkart.sellerservice_v1.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	public String uploadImage(MultipartFile file) throws IOException;
	public String deleteImage(String publicId);
}
