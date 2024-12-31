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

import com.truxxkart.sellerservice_v1.entity.Brand;
import com.truxxkart.sellerservice_v1.entity.Category;
import com.truxxkart.sellerservice_v1.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {
	@Autowired
	private BrandService brandService;;
	
	@PostMapping()
	public ResponseEntity<Brand> createBrand( @RequestBody Brand brand){
		Brand newBrand =brandService.createBrand(brand);
	
		return ResponseEntity.status(HttpStatus.OK).body(newBrand);
	}
	
	@GetMapping()
	public ResponseEntity<List<Brand>> getBrand(){
		List<Brand> brandList =brandService.getAllBrand();
		return ResponseEntity.status(HttpStatus.OK).body(brandList);
	}
	
	@PutMapping("/update/id/{brandId}")
	public ResponseEntity<Brand> updateBrand(@PathVariable Long brandId ,@RequestParam  String name){
		Brand updatedBrand =brandService.updateBrand(brandId, name);
	
		return ResponseEntity.status(HttpStatus.OK).body(updatedBrand);
	}
	@DeleteMapping("/delete/id/{brandId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long brandId){
		String deletedBrand =brandService.deleteBrand(brandId);
	
		return ResponseEntity.status(HttpStatus.OK).body(deletedBrand);
	}
}
