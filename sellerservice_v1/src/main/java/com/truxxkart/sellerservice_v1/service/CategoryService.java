package com.truxxkart.sellerservice_v1.service;

import java.util.List;



import com.truxxkart.sellerservice_v1.entity.Category;

public interface CategoryService {
	public Category createCategory(Category category);
	public List<Category> getAllCategory();
	public Category updateCategory(Long categoryId,String name);
	public String deleteCategory(Long categoryId); 

}
