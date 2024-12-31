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

import com.truxxkart.sellerservice_v1.entity.ProductSize;
import com.truxxkart.sellerservice_v1.entity.ProductVariant;
import com.truxxkart.sellerservice_v1.service.ProductVariantService;

@RestController
@RequestMapping("/product-variants")
public class ProductVariantController {
	
	@Autowired
	private ProductVariantService pvService;
	
	@PostMapping("/id/{productId}")
	public ResponseEntity<ProductVariant> createProductVariants(@RequestBody ProductVariant pv,@PathVariable Long productId){
	ProductVariant newPV =pvService.addProductVariants(pv, productId);
		return ResponseEntity.status(HttpStatus.OK).body(newPV);
	}
	
	@GetMapping("/id/{productId}")
	public ResponseEntity<List<ProductVariant>> getAllProductVariantOfAProduct(@PathVariable Long productId){
		List<ProductVariant> pvList =pvService.getProductVariantByProductId(productId);
		return ResponseEntity.status(HttpStatus.OK).body(pvList);
	}
	@GetMapping()
	public ResponseEntity<List<ProductVariant>> getAllProductVariants(){
		List<ProductVariant> pvList =pvService.getAllProductVariants() ;
		return ResponseEntity.status(HttpStatus.OK).body(pvList);
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<ProductVariant>> getProductVariantsByStatus(@RequestParam String field,@RequestParam Boolean  findBy){
		List<ProductVariant> pvList =pvService.getProductVariantsByStatus(field, findBy);
		return ResponseEntity.status(HttpStatus.OK).body(pvList);
	}
	
	@PutMapping("/update/color/id/{variantId}")
	public ResponseEntity<ProductVariant> updateProductVariantColor(@PathVariable Long variantId,@RequestParam String color){
		ProductVariant pv =pvService.updateProductVariantColor(variantId, color);
		return ResponseEntity.status(HttpStatus.OK).body(pv);
	}
	
	@PutMapping("/update/price/id/{variantId}")
	public ResponseEntity<ProductVariant> updateProductVariantPrices(@PathVariable Long variantId,@RequestParam Double price ,@RequestParam Double discountedPrice){
		ProductVariant pv =pvService.updateProductVariantPrice(variantId, price, discountedPrice);
		return ResponseEntity.status(HttpStatus.OK).body(pv);
	}
	
	@PutMapping("/update/status/id/{variantId}")
	public ResponseEntity<ProductVariant> updateProductVariantstatus(@PathVariable Long variantId,@RequestParam String field ,@RequestParam Boolean toBeUpdated){
		ProductVariant pv =pvService.updateProductVariantStatus(variantId, field, toBeUpdated);
		return ResponseEntity.status(HttpStatus.OK).body(pv);
	}
	
	@DeleteMapping("/delete/id/{variantId}")
	public ResponseEntity<String> deleteProductVariant(@PathVariable Long variantId){
	String delMsg =pvService.deleteProductVariant(variantId);
		return ResponseEntity.status(HttpStatus.OK).body(delMsg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@PutMapping("/update")
	public ResponseEntity<ProductVariant> updateProductVariantSizes(@RequestParam Long pvId,@RequestParam Long sizeId){
		ProductVariant pv =pvService.updateProductVariantSizes(pvId, sizeId);
		return ResponseEntity.status(HttpStatus.OK).body(pv);
	}
	

}
