package com.truxxkart.sellerservice_v1.controller;

import java.util.List;
import java.util.Set;

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

import com.truxxkart.sellerservice_v1.entity.Product;
import com.truxxkart.sellerservice_v1.entity.ProductTag;
import com.truxxkart.sellerservice_v1.service.ProductTagService;

@RestController
@RequestMapping("/product-tags")
public class ProductTagController {
	@Autowired
	private ProductTagService ptService;

	@PostMapping("/id/{productId}")
	public ResponseEntity<ProductTag> createTags(@PathVariable Long productId, @RequestBody ProductTag tag) {
		ProductTag craetedTag =ptService.createProductTag(productId, tag);

		return ResponseEntity.status(HttpStatus.OK).body(craetedTag);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductTag>> getAllTags() {
		List<ProductTag> tagList =ptService.getAllTags();

		return ResponseEntity.status(HttpStatus.OK).body(tagList);
	}
	
	@GetMapping("/id/{productId}")
	public ResponseEntity<List<ProductTag>> getAllTagsByProductId(@PathVariable Long productId) {
		List<ProductTag> tagList =ptService.getTagByProductId(productId);

		return ResponseEntity.status(HttpStatus.OK).body(tagList);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<ProductTag> getTagByName(@PathVariable String name) {
		ProductTag tag =ptService.getTagByName(name);

		return ResponseEntity.status(HttpStatus.OK).body(tag);
	}
	@GetMapping("/type/{type}")
	public ResponseEntity<List<ProductTag>> getTagByType(@PathVariable String type) {
		List<ProductTag> tag =ptService.getTagByType(type);

		return ResponseEntity.status(HttpStatus.OK).body(tag);
	}
	
	@GetMapping("/product/type/{type}")
	public ResponseEntity<Set<Product>> getProductByType(@PathVariable String type) {
		Set<Product> products =ptService.getProductByTagType(type);

		return ResponseEntity.status(HttpStatus.OK).body(products);
	}
	
	@GetMapping("/slug/{slug}")
	public ResponseEntity<List<ProductTag>> getTagBySlug(@PathVariable String slug) {
		List<ProductTag> tag =ptService.getTagBySlug(slug);

		return ResponseEntity.status(HttpStatus.OK).body(tag);
	}
    
	@GetMapping("/visible")
	public ResponseEntity<List<ProductTag>> getTagByVisibility(@RequestParam boolean visible) {
		List<ProductTag> tag =ptService.getTagByVisibility(visible);

		return ResponseEntity.status(HttpStatus.OK).body(tag);
	}
	@PutMapping("/update/id/{productTagId}")
	public ResponseEntity<ProductTag> updateTagVisibility(@PathVariable Long productTagId, @RequestParam boolean isVisible) {
		ProductTag updatedTag =ptService.updateTagVisibility(productTagId, isVisible);

		return ResponseEntity.status(HttpStatus.OK).body(updatedTag);
	}
}
