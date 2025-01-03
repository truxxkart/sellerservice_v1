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
import com.truxxkart.sellerservice_v1.service.ProductSizeService;

@RestController
@RequestMapping("product-sizes")
public class ProductSizeController {
	@Autowired
	private ProductSizeService psService;
	
	@PostMapping("/id/{productVariantId}")
	public ResponseEntity<ProductSize> createProductSizes(@RequestBody ProductSize pv,@PathVariable Long productVariantId){
	ProductSize newPS =psService.addProductSizes(pv, productVariantId);
		return ResponseEntity.status(HttpStatus.OK).body(newPS);
	}
	
	@GetMapping("/id/{pvId}")
	public ResponseEntity<List<ProductSize>> getAllProductSizeOfAProductVariant(@PathVariable Long pvId){
		List<ProductSize> psList =psService.getProductSizeByProductVariantId(pvId);
		return ResponseEntity.status(HttpStatus.OK).body(psList);
	}
	
	@GetMapping("/sorted/asc")
	public ResponseEntity<List<ProductSize>>  sortProductSizesByPrice(){
		List<ProductSize> psList =psService.sortProductSizesByPrice();
		return ResponseEntity.status(HttpStatus.OK).body(psList);
	}
	
	
	
	
	@GetMapping("/product-sizeId/{psId}")
	public ResponseEntity<ProductSize> getProductSizeBySizeId(@PathVariable Long psId){
	ProductSize ps =psService.getProductSizeBySizeId(psId);
		return ResponseEntity.status(HttpStatus.OK).body(ps);
	}
	@GetMapping("/product-sizeId/{psId}/product-variantId/{pvId}")
	public ResponseEntity<ProductSize> getProductSizeOfAVariant(@PathVariable Long psId,@PathVariable Long pvId){
	ProductSize ps =psService.getProductSizesOfProductVariant(psId, pvId);
		return ResponseEntity.status(HttpStatus.OK).body(ps);
	}
	
	@GetMapping()
	public ResponseEntity<List<ProductSize>> getAllProductSizes(){
		List<ProductSize> pvList =psService.getAllProductSizes() ;
		return ResponseEntity.status(HttpStatus.OK).body(pvList);
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<ProductSize>> getAllProductSizesByStatus(@RequestParam String field,@RequestParam Boolean findBy){
		List<ProductSize> pvList =psService.getAllProductSizesByStatus(field, findBy);
		return ResponseEntity.status(HttpStatus.OK).body(pvList);
	}
	@PutMapping("/update/size/product-sizeId/{psId}")
	public ResponseEntity<ProductSize> updateSize(@PathVariable Long psId,@RequestParam String size){
	ProductSize ps =psService.updateSize(psId, size);
		return ResponseEntity.status(HttpStatus.OK).body(ps);
	}
	@PutMapping("/update/price/product-sizeId/{psId}")
	public ResponseEntity<ProductSize> updatePrice(@PathVariable Long psId,@RequestParam Double additionalPrice,@RequestParam Double additionalDiscountedPrice){
	ProductSize ps =psService.updatePrice(psId, additionalPrice, additionalDiscountedPrice);
		return ResponseEntity.status(HttpStatus.OK).body(ps);
	}
	@PutMapping("/update/stock/product-sizeId/{psId}")
	public ResponseEntity<ProductSize> updateStock(@PathVariable Long psId,@RequestParam Integer stock){
	ProductSize ps =psService.updateStock(psId, stock);
		return ResponseEntity.status(HttpStatus.OK).body(ps);
	}
	
	@PutMapping("/update/status/product-sizeId/{psId}")
	public ResponseEntity<ProductSize> updateStatus(@PathVariable Long psId,@RequestParam String field,@RequestParam Boolean toBeUpdated){
	ProductSize ps =psService.updateStatus(psId, field, toBeUpdated);
		return ResponseEntity.status(HttpStatus.OK).body(ps);
	}
	@DeleteMapping("/delete/product-sizeId/{psId}")
	public ResponseEntity<String> deleteProductSize(@PathVariable Long psId){
	    String delMsg =psService.deleteProductSize(psId);
		return ResponseEntity.status(HttpStatus.OK).body(delMsg);
	}

}
