package com.truxxkart.sellerservice_v1.serviceImpl;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.Product;
import com.truxxkart.sellerservice_v1.entity.ProductTag;
import com.truxxkart.sellerservice_v1.repository.ProductRepository;
import com.truxxkart.sellerservice_v1.repository.ProductTagsRepository;
import com.truxxkart.sellerservice_v1.service.ProductTagService;

@Service
public class ProductTagServiceImpl implements ProductTagService {

	@Autowired
	private ProductTagsRepository ptRepo;
	@Autowired
	private ProductRepository prodRepo;
	
	
	@Override
	public ProductTag createProductTag(Long productId, ProductTag tag) {
		Optional<Product> optProd = prodRepo.findById(productId);
		ProductTag newPT=null;
		if(optProd.isPresent()) {
			Product product =optProd.get();
		List<Product> ps=tag.getProduct();
		ps.add(product);
		tag.setProduct(ps);
		
		  newPT = ptRepo.save(tag);
		
		}
		
		return newPT;
	}

	@Override
	public List<ProductTag> getAllTags() {
	
		return ptRepo.findAll().stream().collect(Collectors.toList()) ;
	}

	@Override
	public List<ProductTag> getTagByProductId(Long productId) {
		    return  ptRepo.findAll().stream().filter(pt->pt.getProduct().stream().anyMatch(prod->prod.getId()==productId) ).collect(Collectors.toList());
		
	}

	@Override
	public ProductTag getTagByName(String name) {
		
		return ptRepo.findByName(name);
	}

	@Override
	public List<ProductTag> getTagByType(String type) {
		return ptRepo.findByType(type);
	}

	@Override
	public List<ProductTag> getTagBySlug(String slug) {
	return ptRepo.findBySlug(slug);
	}

	@Override
	public List<ProductTag> getTagByVisibility(boolean visible) {
		
		return ptRepo.findAll().stream().filter(pt->pt.getVisibility()==visible).collect(Collectors.toList());
	}

	@Override
	public ProductTag updateTagVisibility(Long productTagId, boolean isVisible) {
		Optional<ProductTag> optPT =ptRepo.findById(productTagId);
		if(optPT.isPresent()) {
			ProductTag pt=optPT.get();
			pt.setVisibility(isVisible);
			return ptRepo.save(pt);
		}
		return null;
	}

	@Override
	public Set<Product> getProductByTagType(String tagType) {
        Set<Product> productsForTag = ptRepo.findAll().stream()
                .filter(tag -> tag.getType().equalsIgnoreCase(tagType))
                .flatMap(tag -> tag.getProduct().stream())
                .collect(Collectors.toSet());
		return productsForTag;
	}

}
