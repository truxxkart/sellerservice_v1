package com.truxxkart.sellerservice_v1.controller;

import java.util.List;

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
import com.truxxkart.sellerservice_v1.service.BrandService;
import com.truxxkart.sellerservice_v1.service.CategoryService;
import com.truxxkart.sellerservice_v1.service.ProductService;
import com.truxxkart.sellerservice_v1enums.OcassionEnumType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService prodService;
	
	
	@PostMapping("/id/{sellerId}")
	public ResponseEntity<Product> createProduct(@RequestParam String category,@RequestParam String brand ,  @PathVariable Long sellerId ,  @RequestBody Product product){
		        
		Product newProduct=prodService.createProduct(sellerId, product,category, brand);
		return ResponseEntity.status(HttpStatus.OK).body(newProduct);
	}
	
	@GetMapping()
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> prodList =prodService.getAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	
	
	@GetMapping("/sorted")
	public ResponseEntity<List<Product>> getProductsSortedByPrice(){
		List<Product> prodList =prodService.getProductsSortedByPrice();
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/price/desc")
	public ResponseEntity<List<Product>> getAllSortedProductsByDesc(){
		List<Product> prodList =prodService.sortedProductsByPriceDescending();
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/price/asc")
	public ResponseEntity<List<Product>> getAllSortedProductsByAsc(){
		List<Product> prodList =prodService.sortedProductsByPriceAscending();
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/rating/desc")
	public ResponseEntity<List<Product>> getAllSortedProductsByRatingDesc(){
		List<Product> prodList =prodService.sortedProductsByRatingDescending();
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/rating/asc")
	public ResponseEntity<List<Product>> getAllSortedProductsByRatingAsc(){
		List<Product> prodList =prodService.sortedProductsByRatingAscending();
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/category/price/desc/{category}")
	public ResponseEntity<List<Product>> getAllSortedProductsByCategoryAndPriceDesc(@PathVariable String category){
		List<Product> prodList =prodService.sortedProductsByCategoryThenPriceDescending(category);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/category/price/asc/{category}")
	public ResponseEntity<List<Product>> getAllSortedProductsByCategoryAndPriceAsc(@PathVariable String category){
		List<Product> prodList =prodService.sortedProductsByCategoryThenPriceAscending(category);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/category/rating/asc/{category}")
	public ResponseEntity<List<Product>> getAllSortedProductsByCategoryAndRatingAsc(@PathVariable String category){
		List<Product> prodList =prodService.sortedProductsByCategoryThenRatingAscending(category);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/category/rating/desc/{category}")
	public ResponseEntity<List<Product>> getAllSortedProductsByCategoryAndRatingDesc(@PathVariable String category){
		List<Product> prodList =prodService.sortedProductsByCategoryThenRatingDescending(category);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	
	@GetMapping("/sorted/brand/price/desc/{brand}")
	public ResponseEntity<List<Product>> getAllSortedProductsByBrandAndPriceDesc(@PathVariable String brand){
		List<Product> prodList =prodService.sortedProductsByBrandThenPriceDescending(brand);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/brand/price/asc/{brand}")
	public ResponseEntity<List<Product>> getAllSortedProductsByBrandAndPriceAsc(@PathVariable String brand){
		List<Product> prodList =prodService.sortedProductsByBrandThenPriceAscending(brand);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/brand/rating/asc/{brand}")
	public ResponseEntity<List<Product>> getAllSortedProductsByBrandAndRatingAsc(@PathVariable String brand){
		List<Product> prodList =prodService.sortedProductsByBrandThenRatingAscending(brand);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@GetMapping("/sorted/brand/rating/desc/{brand}")
	public ResponseEntity<List<Product>> getAllSortedProductsByBrandAndRatingDesc(@PathVariable String brand){
		List<Product> prodList =prodService.sortedProductsByBrandThenRatingDescending(brand);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	
	
	
	@GetMapping("/pincode")
	public ResponseEntity<List<Product>> getAllProductsByPincode(@RequestParam String pincode){
		log.info("PINCODEEEEEEEEEEEEEEEE {}",pincode);
		List<Product> prodList =prodService.getProductByPincode(pincode);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam String category){
		
		List<Product> prodList =prodService.getProductByCategory(category);
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
	@PutMapping("/update/{productId}/{price}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@PathVariable Double price){
        
		Product newProduct=prodService.updateProductPrice(productId, price);
		return ResponseEntity.status(HttpStatus.OK).body(newProduct);
	}
	
	@PutMapping("/update/brand/{productId}/{brandId}")
	public ResponseEntity<Product> updateProductsBrand(@PathVariable Long productId,@PathVariable Long brandId){
        
		Product newProduct=prodService.updateProductBrand(productId, brandId);
		return ResponseEntity.status(HttpStatus.OK).body(newProduct);
	}
	
	@PutMapping("/update/category/{productId}/{categoryId}")
	public ResponseEntity<Product> updateProductscategory(@PathVariable Long productId,@PathVariable Long categoryId){
        
		Product updatedProduct=prodService.updateProductcategory(productId, categoryId);
		return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
	}
	
	@PutMapping("/update/ocassion/{productId}")
	public ResponseEntity<Product> updateProductsOcassion(@PathVariable Long productId,@RequestParam OcassionEnumType ocassion){
    
		Product updatedProduct=prodService.updateProductOcassion(productId, ocassion);
		return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
	}
	
//	FILTERS
	  @PostMapping("/filter-by-brands")
	    public ResponseEntity<List<Product>> filterProductsByBrands(@RequestBody List<String> brandNames) {
	        List<Product> filteredProducts = prodService.filterProductByBrands(brandNames);
	        return ResponseEntity.ok(filteredProducts);
	    }
	  @PostMapping("/filter-by-categories")
	    public ResponseEntity<List<Product>> filterProductsByCategories(@RequestBody List<String> categoryNames) {
	        List<Product> filteredProducts = prodService.filterProductByCategoriess(categoryNames);
	        return ResponseEntity.ok(filteredProducts);
	    }
	  
	  //test or check it below 4 apis********************************
	  @PostMapping("/filter-by-colors")
	    public ResponseEntity<List<Product>> filterProductsByColors(@RequestBody List<String> colors) {
	        List<Product> filteredProducts = prodService.filterProductByColors(colors);
	        return ResponseEntity.ok(filteredProducts);
	    }
	  
	  @PostMapping("/filter-by-sizes")
	    public ResponseEntity<List<Product>> filterProductsBySizes(@RequestBody List<String> sizes) {
	        List<Product> filteredProducts = prodService.filterProductBySizes(sizes);
	        return ResponseEntity.ok(filteredProducts);
	    }
	  
	  @PostMapping("/filter-by-ocassions")
	    public ResponseEntity<List<Product>> filterProductsByOcassions(@RequestBody List<String> ocassions) {
	        List<Product> filteredProducts = prodService.filterProductByOcassions(ocassions);
	        return ResponseEntity.ok(filteredProducts);
	    }

	  //On the basis of Type
	  @PostMapping("/filter-by-tags")
	    public ResponseEntity<List<Product>> filterProductsByTags(@RequestBody List<String> tags) {
	        List<Product> filteredProducts = prodService.filterProductByTags(tags);
	        return ResponseEntity.ok(filteredProducts);
	    }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  @PostMapping("/filter-by-pricerange")
	    public ResponseEntity<List<Product>> filterProductsByPricess(@RequestParam Double initialPrice,@RequestParam Double finalPrice) {
	        List<Product> filteredProducts = prodService.filterProductByPriceRanges(initialPrice, finalPrice);
	        return ResponseEntity.ok(filteredProducts);
	    }
	  
	  @PostMapping("/filter-by-ratingmorethan")
	    public ResponseEntity<List<Product>> filterProductsByRating(@RequestParam Double rating) {
	        List<Product> filteredProducts = prodService.filterProductByRatingsMoreThan(rating);
	        return ResponseEntity.ok(filteredProducts);
	    }
	
}
