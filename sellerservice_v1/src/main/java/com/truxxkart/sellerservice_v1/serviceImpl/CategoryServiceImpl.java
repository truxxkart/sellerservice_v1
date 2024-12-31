package com.truxxkart.sellerservice_v1.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.Category;
import com.truxxkart.sellerservice_v1.repository.CategoryRepository;
import com.truxxkart.sellerservice_v1.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;
	@Override
	public Category createCategory(Category category) {
		    Category newCategory = categoryRepo.save(category);
		return newCategory;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> catList =categoryRepo.findAll();
		return catList;
	}

	@Override
	public Category updateCategory(Long categoryId, String name) {
		Optional<Category> optCategory =categoryRepo.findById(categoryId);
		if(optCategory.isPresent()) {
			Category category =optCategory.get();
			category.setName(name);
			return categoryRepo.save(category);
			
		}
		
		return null;
	}

	@Override
	public String deleteCategory(Long categoryId) {
		categoryRepo.deleteById(categoryId);
		return "CAtegory deleted successfully";
	}

}
