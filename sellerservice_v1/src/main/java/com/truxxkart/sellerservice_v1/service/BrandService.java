package com.truxxkart.sellerservice_v1.service;

import java.util.List;

import com.truxxkart.sellerservice_v1.entity.Brand;
import com.truxxkart.sellerservice_v1.entity.Category;


public interface BrandService {
	public Brand createBrand(Brand brand);
	public List<Brand> getAllBrand();
	public Brand updateBrand(Long brandId,String name);
	public String deleteBrand(Long brandId); 
	
}
