package com.truxxkart.sellerservice_v1.service;

import java.util.List;

import com.truxxkart.sellerservice_v1.entity.ProductVariant;

public interface ProductVariantService {
	public ProductVariant addProductVariants(ProductVariant pv,Long productId);
	public List<ProductVariant> getProductVariantByProductId(Long productId);
	public List<ProductVariant> getAllProductVariants();
	public ProductVariant updateProductVariantSizes(Long variantId,Long sizeId);
	// work on image add and update
	public List<ProductVariant> getProductVariantsByStatus(String field,Boolean findBy);
	public ProductVariant updateProductVariantColor(Long variantId,String color);
	public ProductVariant updateProductVariantPrice(Long variantId,Double price,Double discountedPrice);
	public ProductVariant updateProductVariantStatus(Long variantId,String field,Boolean toBeUpdated);
	public String deleteProductVariant(Long variantId);
}
