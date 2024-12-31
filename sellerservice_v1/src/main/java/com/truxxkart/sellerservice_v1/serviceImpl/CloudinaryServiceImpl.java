package com.truxxkart.sellerservice_v1.serviceImpl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.truxxkart.sellerservice_v1.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

	@Autowired
	private Cloudinary cloudinary;
	@Override
	public String uploadImage(MultipartFile file) throws IOException {
		
	Map objectUtils =	ObjectUtils.asMap(
                "folder", "product_img",
                "resource_type", "image",
                "use_filename", true,
                "overwrite", true,
                "context", ObjectUtils.asMap("caption", "Sample Caption", "alt", "Sample Alt Text"),
                "quality", "auto"
               
        );
		
		Map result =cloudinary.uploader().upload(file.getBytes(), objectUtils);
		return result.get("url").toString() +"   ////  "+result.get("public_id").toString();
	}
	@Override
	public String deleteImage(String publicId) {
		  try {
	            // Deleting the image by its public_id
	            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("invalidate", true));
	            return result.get("result").toString(); // Should return "ok" if successful
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to delete image: " + e.getMessage(), e);
	        }
	}

}
