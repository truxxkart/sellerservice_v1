package com.truxxkart.sellerservice_v1.serviceImpl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.Product;
import com.truxxkart.sellerservice_v1.entity.ProductSize;
import com.truxxkart.sellerservice_v1.entity.ProductVariant;
import com.truxxkart.sellerservice_v1.repository.ProductRepository;
import com.truxxkart.sellerservice_v1.repository.ProductSizeRepository;
import com.truxxkart.sellerservice_v1.repository.ProductVariantRepository;
import com.truxxkart.sellerservice_v1.service.ProductSizeService;
import com.truxxkart.sellerservice_v1.service.ProductVariantService;
import com.truxxkart.sellerservice_v1enums.ColorType;

@Service
public class ProductVariantServiceImpl implements ProductVariantService{

	@Autowired
	private ProductVariantRepository pvRepo;
	@Autowired
	private ProductRepository prodRepo;
	@Autowired
	private ProductSizeRepository psRepo;
	@Autowired
	private ProductSizeService psService;
	
	@Override
	public List<ProductVariant> getProductVariantByProductId(Long productId) {
		    List<ProductVariant> pvList =pvRepo.findAll().stream().filter(p->p.getProduct().getId()==productId).collect(Collectors.toList());
		      
		return pvList;
	}
	@Override
	public List<ProductVariant> getAllProductVariants() {
		 List<ProductVariant> pvList =pvRepo.findAll();
		return pvList;
	}
	@Override
	public ProductVariant addProductVariants(ProductVariant pv, Long productId) {
		Optional<Product> optProd =prodRepo.findById(productId);
		if(optProd.isPresent()) {
			pv.setProduct(optProd.get());
			return pvRepo.save(pv);
		}
		
		return null;
	}


	@Override
	public ProductVariant updateProductVariantSizes(Long variantId, Long sizeId) {
//			  Optional<ProductVariant> optPV =pvRepo.findById(variantId);
//			    if(optPV.isPresent()) {
//			    	ProductVariant prodVariant=optPV.get();
//			    	   Optional<ProductSize> optPS=psRepo.findById(sizeId);
//			    	     if(optPS.isPresent()) {
//			    	    	 ProductSize ps=optPS.get();
//			    	    	 ProductVariant pv =ps.getProductVariant();
//			    	    	 pvList.add(prodVariant);
//			    	    	 pvRepo.save(prodVariant);
//			    	         ps.setProductVariant(pvList);
//			    	         psRepo.save(ps);
//			    	     }
//			    	     return prodVariant;
//			    }
			   
		  
		return null;
	
	
}
	@Override
	public List<ProductVariant> getProductVariantsByStatus(String field, Boolean findBy) {
		if(field.equalsIgnoreCase("AVAILABLE")) {
		return	pvRepo.findAll().stream().filter(pv->pv.getIsAvailable()==findBy).collect(Collectors.toList());
		}
		else if(field.equalsIgnoreCase("ACTIVATION")) {
		return	pvRepo.findAll().stream().filter(pv->pv.getIsActive()==findBy).collect(Collectors.toList());
		}
		return null;
	}
	
	@Override
	public ProductVariant getProductVariantsByColor(ColorType color, Long productId) {
		Optional<Product> optProduct =prodRepo.findById(productId);
	    if(optProduct.isPresent()) {
	    	ProductVariant prodVariant=optProduct.get().getVariants().stream().filter(pv->pv.getColor().equals(color)).findAny().get();
	    	prodVariant.setColor(color);
	    	return pvRepo.save(prodVariant);
	    	
	    }
		return null;
	}
	
	
	@Override
	public ProductVariant updateProductVariantColor(Long variantId, ColorType color) {
		Optional<ProductVariant> optPV =pvRepo.findById(variantId);
	    if(optPV.isPresent()) {
	    	ProductVariant prodVariant=optPV.get();
	    	prodVariant.setColor(color);
	    	return pvRepo.save(prodVariant);
	    	
	    }
		return null;
	}
	@Override
	public ProductVariant updateProductVariantPrice(Long variantId, Double price, Double discountedProce) {
		Optional<ProductVariant> optPV =pvRepo.findById(variantId);
	    if(optPV.isPresent()) {
	    	ProductVariant prodVariant=optPV.get();
	    	prodVariant.setPrice(price);
	    	prodVariant.setDiscountedPrice(discountedProce);
	    	return pvRepo.save(prodVariant);
	    	
	    }
		return null;
	}
	@Override
	public ProductVariant updateProductVariantStatus(Long variantId, String field, Boolean toBeUpdated) {
		Optional<ProductVariant> optPV =pvRepo.findById(variantId);
	    if(optPV.isPresent()) {
	    	ProductVariant prodVariant=optPV.get();
			if(field.equalsIgnoreCase("AVAILABLE")) {
			  prodVariant.setIsAvailable(toBeUpdated);
			} else if(field.equalsIgnoreCase("ACTIVATION")) {
				  prodVariant.setIsActive(toBeUpdated);
				}
	    	return pvRepo.save(prodVariant);
	    	
	    }
	    return null;
	}
	@Override
	public String deleteProductVariant(Long variantId) {
		Optional<ProductVariant> optPV =pvRepo.findById(variantId);
	    if(optPV.isPresent()) {
	    	pvRepo.deleteById(variantId);
	    	return "VAriant is deleted Successfully";
	    	}
		return null;
	}
	@Override
	public Set<ProductVariant> getProductVariantBaseOnSortedProductSizeByPriceInAsc() {
		List<ProductSize> sortedProductSizeList =psService.sortProductSizesByPrice();
		Set<ProductVariant> response =new LinkedHashSet<ProductVariant>();
		            sortedProductSizeList.stream().forEach(ps->{
		            	response.add(ps.getProductVariant());
		            });  
		return response;
	}
	
}