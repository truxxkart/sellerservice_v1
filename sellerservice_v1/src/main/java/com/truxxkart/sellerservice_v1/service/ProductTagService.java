package com.truxxkart.sellerservice_v1.service;

import java.util.List;
import java.util.Set;

import com.truxxkart.sellerservice_v1.entity.Product;
import com.truxxkart.sellerservice_v1.entity.ProductTag;


public interface ProductTagService {
	public ProductTag createProductTag(Long productId ,ProductTag tag);
	public List<ProductTag> getAllTags();
	public List<ProductTag> getTagByProductId(Long productId);
	public ProductTag getTagByName(String name);
	public List<ProductTag> getTagByType(String type);
	public List<ProductTag> getTagByVisibility(boolean visible);
	public List<ProductTag> getTagBySlug(String slug);
	public ProductTag updateTagVisibility(Long productTagId , boolean visible);
	public Set<Product> getProductByTagType(String tagType);
}
