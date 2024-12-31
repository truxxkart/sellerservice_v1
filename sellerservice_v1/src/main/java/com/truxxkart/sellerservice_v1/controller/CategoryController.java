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

import com.truxxkart.sellerservice_v1.entity.Category;
import com.truxxkart.sellerservice_v1.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private CategoryService catService;;
	
	@PostMapping()
	public ResponseEntity<Category> createCategory( @RequestBody Category category){
		Category newCategory =catService.createCategory(category);
	
		return ResponseEntity.status(HttpStatus.OK).body(newCategory);
	}
	
	@GetMapping()
	public ResponseEntity<List<Category>> getCategory(){
		List<Category> catList =catService.getAllCategory();
		return ResponseEntity.status(HttpStatus.OK).body(catList);
	}
	
	@PutMapping("/update/id/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId ,@RequestParam  String name){
		Category updatedCategory =catService.updateCategory(categoryId, name);
	
		return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
	}
	@DeleteMapping("/delete/id/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
		String deletedCategory =catService.deleteCategory(categoryId);
	
		return ResponseEntity.status(HttpStatus.OK).body(deletedCategory);
	}
}
