package com.truxxkart.sellerservice_v1.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.Brand;
import com.truxxkart.sellerservice_v1.repository.BrandRepository;
import com.truxxkart.sellerservice_v1.service.BrandService;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepo;
	@Override
	public Brand createBrand(Brand brand) {
		Brand newBrand=brandRepo.save(brand);
		return newBrand;
	}

	@Override
	public List<Brand> getAllBrand() {
		List<Brand> brandList = brandRepo.findAll();
		return brandList;
	}

	@Override
	public Brand updateBrand(Long brandId, String name) {
		Optional<Brand> optBrand =brandRepo.findById(brandId);
		if(optBrand.isPresent()) {
			Brand brand = optBrand.get();
			brand.setName(name);
			return brandRepo.save(brand);
			
		}
		
		return null;
	}

	@Override
	public String deleteBrand(Long brandId) {
		// TODO Auto-generated method stub
	 brandRepo.deleteById(brandId);
		return "Brand is deleted successfully";
	}

}
