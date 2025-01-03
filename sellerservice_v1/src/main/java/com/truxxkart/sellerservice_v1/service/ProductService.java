package com.truxxkart.sellerservice_v1.service;

import java.util.List;


import com.truxxkart.sellerservice_v1.entity.Product;
import com.truxxkart.sellerservice_v1.entity.ProductVariant;
import com.truxxkart.sellerservice_v1enums.OcassionEnumType;

public interface ProductService {
	 public Product createProduct(Long userId, Product product,String category, String brand);
	   public List<Product> getAllProducts();
	   public List<Product> getProductByPincode(String pincode);
	   public List<Product> getProductByCategory(String category);
	   public Product updateProductPrice(Long productId,Double price);
	   public Product updateProductBrand(Long productId,Long brandId);
	   public Product updateProductcategory(Long productId,Long categoryId);
	   public Product updateProductOcassion(Long productId ,OcassionEnumType ocassion);
	   public List<Product> getProductsSortedByPrice();
	   public List<Product> sortedProductsByPriceDescending();
	   public List<Product> sortedProductsByPriceAscending();
	   public List<Product> sortedProductsByRatingDescending();
	   public List<Product> sortedProductsByRatingAscending();
	   public List<Product> sortedProductsByCategoryThenPriceAscending(String categoryName);
	   public List<Product> sortedProductsByCategoryThenPriceDescending(String categoryName);
	   public List<Product> sortedProductsByCategoryThenRatingAscending(String categoryName);
	   public List<Product> sortedProductsByCategoryThenRatingDescending(String categoryName);
	   public List<Product> sortedProductsByBrandThenPriceAscending(String brandName);
	   public List<Product> sortedProductsByBrandThenPriceDescending(String brandName);
	   public List<Product> sortedProductsByBrandThenRatingAscending(String brandName);
	   public List<Product> sortedProductsByBrandThenRatingDescending(String brandName);
	   
	   public List<Product> filterProductByPriceRanges(Double initialPrice,Double finalPrice);
	   public List<Product> filterProductByBrands(List<String> brandName);
	   public List<Product> filterProductByCategoriess(List<String> categoryName);
	   public List<Product> filterProductByOcassions(List<String> ocassion);
	   public List<Product> filterProductByColors(List<String> color);
	   public List<Product> filterProductBySizes(List<String> size);
	   public List<Product> filterProductByTags(List<String> tag);
	   public List<Product> filterProductByRatingsMoreThan( Double rating);

	   
}
