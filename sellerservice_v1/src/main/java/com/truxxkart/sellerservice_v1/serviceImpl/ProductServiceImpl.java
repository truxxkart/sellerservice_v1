package com.truxxkart.sellerservice_v1.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.Brand;
import com.truxxkart.sellerservice_v1.entity.Category;
import com.truxxkart.sellerservice_v1.entity.PostalCodeMapping;
import com.truxxkart.sellerservice_v1.entity.Product;
import com.truxxkart.sellerservice_v1.entity.ProductSize;
import com.truxxkart.sellerservice_v1.entity.User;
import com.truxxkart.sellerservice_v1.repository.BrandRepository;
import com.truxxkart.sellerservice_v1.repository.CategoryRepository;
import com.truxxkart.sellerservice_v1.repository.ProductRepository;
import com.truxxkart.sellerservice_v1.repository.SellerRepository;
import com.truxxkart.sellerservice_v1.service.PostalCodeMappingService;
import com.truxxkart.sellerservice_v1.service.ProductService;
import com.truxxkart.sellerservice_v1enums.OcassionEnumType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
     @Autowired
     private ProductRepository productRepo;
     @Autowired
     private SellerRepository sellerRepo;
     @Autowired
     private CategoryRepository catRepo;
     @Autowired
     private BrandRepository brandRepo;
     @Autowired
     private PostalCodeMappingService pcmService;
     
	@Override
	public Product createProduct(Long userId, Product product ,String category ,String brand) {
		Optional<User> user  =sellerRepo.findById(userId);
		Optional<Category> cat=catRepo.findByName(category);
		Optional<Brand> optBrand =brandRepo.findByName(brand);
		if(user.isPresent() && cat.isPresent() && optBrand.isPresent()) {
			product.setSeller(user.get());
			product.setBrand(optBrand.get());
			product.setCategory(cat.get());
			Product productCreated = productRepo.save(product);
			return productCreated;
		}
		
	  return null;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productList=productRepo.findAll();
		return productList;
	}

	@Override
	public List<Product> getProductByPincode(String pincode) {
		log.info("PIiiiiiiiiiiiNCODEEEEEEEEEEEEEEEE {}",pincode);
		List<PostalCodeMapping> postalCodes =pcmService.getAllPostalCodeMapping();
		Set<String> pinCodes=new HashSet<String>();
		postalCodes.stream().forEach(pc->{
			if(pc.getDestinationPincodes().contains(pincode) || pc.getSourcePincode().equalsIgnoreCase(pincode)) {
				pinCodes.add(pc.getSourcePincode());
			}
		});
		
		
		List<Product> productList=	productRepo.findAll().stream().filter(prod-> pinCodes.contains(prod.getSeller().getPinCode()) ).collect(Collectors.toList());
	return productList;
	
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		 return productRepo.findAll().stream().filter(product->product.getCategory()!=null && product.getCategory().getName().equalsIgnoreCase(category)).collect(Collectors.toList());
		
	}

	@Override
	public Product updateProductPrice(Long productId,Double price) {
		Optional<Product> optProduct = productRepo.findById(productId);
		if(optProduct.isPresent()) {
			Product product=optProduct.get();
			product.setPrice(price);
		return	productRepo.save(product);
			
		}
		return null;
	}

	@Override
	public List<Product> sortedProductsByPriceDescending() {
		List<Product> products =productRepo.findAll();
		Comparator<Product> productSortByPrice =Comparator.comparing(Product :: getPrice);
		Collections.sort(products, productSortByPrice.reversed());
		return products;
	}
	
	
	@Override
	  public List<Product> getProductsSortedByPrice() {
	        return productRepo.findAll()
	                .stream()
	                .sorted(Comparator.comparingDouble(
	                        product -> product.getVariants().stream()
	                                .flatMap(variant -> variant.getSizes().stream())
	                                .mapToDouble(ProductSize :: getAdditionalDiscountedPrice)
	                                .min()
	                                .orElse(Double.MAX_VALUE)
	                ))
	                .collect(Collectors.toList());
	    }
	
	
	

	@Override
	public List<Product> sortedProductsByPriceAscending() {
		List<Product> products =productRepo.findAll();
		Comparator<Product> productSortByPrice =Comparator.comparing(Product :: getPrice);
		Collections.sort(products, productSortByPrice);
		return products;
	}

	@Override
	public List<Product> sortedProductsByRatingDescending() {
		List<Product> products =productRepo.findAll();
		Comparator<Product> productSortByRating =Comparator.comparing(Product :: getRating);
		Collections.sort(products, productSortByRating.reversed());
		return products;
	}

	@Override
	public List<Product> sortedProductsByRatingAscending() {
		List<Product> products =productRepo.findAll();
		Comparator<Product> productSortByRating =Comparator.comparing(Product :: getRating);
		Collections.sort(products, productSortByRating);
		return products;
	}

	@Override
	public List<Product> sortedProductsByCategoryThenPriceAscending(String categoryName) {
		List<Product> productByCategory = productRepo.findAll().stream().filter(prod->prod.getCategory()!=null &&  prod.getCategory().getName().equalsIgnoreCase(categoryName)).collect(Collectors.toList());
		Comparator<Product> productSortByPrice =Comparator.comparing(Product :: getPrice);
		Collections.sort(productByCategory, productSortByPrice);
		return productByCategory;
	}

	@Override
	public List<Product> sortedProductsByCategoryThenPriceDescending(String categoryName) {
		List<Product> productByCategory = productRepo.findAll().stream().filter(prod-> prod.getCategory()!=null && prod.getCategory().getName().equalsIgnoreCase(categoryName)).collect(Collectors.toList());
		Comparator<Product> productSortByPrice =Comparator.comparing(Product :: getPrice);
		Collections.sort(productByCategory, productSortByPrice.reversed());
		return productByCategory;
	}

	@Override
	public List<Product> sortedProductsByCategoryThenRatingAscending(String categoryName) {
		List<Product> productByCategory = productRepo.findAll().stream().filter(prod->prod.getCategory()!=null &&  prod.getCategory().getName().equalsIgnoreCase(categoryName)).collect(Collectors.toList());
		Comparator<Product> productSortByRating =Comparator.comparing(Product :: getRating);
		Collections.sort(productByCategory, productSortByRating);
		return productByCategory;
	}

	@Override
	public List<Product> sortedProductsByCategoryThenRatingDescending(String categoryName) {
		List<Product> productByCategory = productRepo.findAll().stream().filter(prod->prod.getCategory()!=null &&  prod.getCategory().getName().equalsIgnoreCase(categoryName)).collect(Collectors.toList());
		Comparator<Product> productSortByRating =Comparator.comparing(Product :: getRating);
		Collections.sort(productByCategory, productSortByRating.reversed());
		return productByCategory;
	}
	@Override
	public List<Product> sortedProductsByBrandThenPriceAscending(String brandName) {
		List<Product> productByBrand = productRepo.findAll().stream().filter(prod->prod.getBrand()!=null &&  prod.getBrand().getName().equalsIgnoreCase(brandName)).collect(Collectors.toList());
		Comparator<Product> productSortByPrice =Comparator.comparing(Product :: getPrice);
		Collections.sort(productByBrand, productSortByPrice);
		return productByBrand;
	}

	@Override
	public List<Product> sortedProductsByBrandThenPriceDescending(String brandName) {
		List<Product> productByBrand = productRepo.findAll().stream().filter(prod->prod.getBrand()!=null &&  prod.getBrand().getName().equalsIgnoreCase(brandName)).collect(Collectors.toList());
		Comparator<Product> productSortByPrice =Comparator.comparing(Product :: getPrice);
		Collections.sort(productByBrand, productSortByPrice.reversed());
		return productByBrand;
	}

	@Override
	public List<Product> sortedProductsByBrandThenRatingAscending(String brandName) {
		List<Product> productByBrand = productRepo.findAll().stream().filter(prod->prod.getBrand()!=null &&  prod.getBrand().getName().equalsIgnoreCase(brandName)).collect(Collectors.toList());
		Comparator<Product> productSortByRating =Comparator.comparing(Product :: getRating);
		Collections.sort(productByBrand, productSortByRating);
		return productByBrand;
	}

	@Override
	public List<Product> sortedProductsByBrandThenRatingDescending(String brandName) {
		List<Product> productByBrand = productRepo.findAll().stream().filter(prod->prod.getBrand()!=null &&  prod.getBrand().getName().equalsIgnoreCase(brandName)).collect(Collectors.toList());
		Comparator<Product> productSortByRating =Comparator.comparing(Product :: getRating);
		Collections.sort(productByBrand, productSortByRating.reversed());
		return productByBrand;
	}

	@Override
	public List<Product> filterProductByPriceRanges(Double initialPrice, Double finalPrice) {
	List<Product> productList =	productRepo.findAll().stream().filter(prod->prod.getPrice()>=initialPrice && prod.getPrice()<=finalPrice).collect(Collectors.toList());
		return productList;
	}

	@Override
	public List<Product> filterProductByBrands(List<String> brandNames) {
		List<Product> productList =	productRepo.findAll().stream().filter(prod->brandNames.contains(prod.getBrand().getName())).collect(Collectors.toList());
		return productList;
	}

	@Override
	public List<Product> filterProductByOcassions(List<String> ocassions) {
		List<Product> productList =	productRepo.findAll().stream().filter(prod->prod.getOcassions()!=null &&  ocassions.contains(prod.getOcassions().name())).collect(Collectors.toList());
		return productList;
	}

	@Override
	public List<Product> filterProductByColors(List<String> color) {
		List<Product> productList =	productRepo.findAll().stream().filter(prod->prod.getVariants().stream().anyMatch(var->color.contains(var.getColor()) )).collect(Collectors.toList());
		return productList;
	}

	@Override
	public List<Product> filterProductBySizes(List<String> size) {
		List<Product> productList =	productRepo.findAll().stream().filter(prod->prod.getVariants().stream().anyMatch(var->var.getSizes().stream().anyMatch(sz->size.contains(sz.getSize()) )) ).collect(Collectors.toList());
		return productList;
	}

	@Override
	public List<Product> filterProductByTags(List<String> tags) {
		List<Product> productList =	productRepo.findAll().stream().filter(prod->prod.getTags().stream().anyMatch(tag->tags.contains(tag.getType()))).collect(Collectors.toList());
		return productList;
	}

	@Override
	public List<Product> filterProductByRatingsMoreThan(Double rating) {
		List<Product> productList =	productRepo.findAll().stream().filter(prod->prod.getRating()>=rating).collect(Collectors.toList());
		return productList;
	}

	@Override
	public Product updateProductBrand(Long productId, Long brandId) {
		Optional<Product> optProduct =productRepo.findById(productId);
		if(optProduct.isPresent()){
			Optional<Brand> optBrand =brandRepo.findById(brandId);
			 if(optBrand.isPresent()) {
				 Product product =optProduct.get();
				 Brand brand=optBrand.get();
				 product.setBrand(brand);
				 return productRepo.save(product);
			 }
		}
		return null;
	}

	@Override
	public Product updateProductcategory(Long productId, Long categoryId) {
		Optional<Product> optProduct =productRepo.findById(productId);
		if(optProduct.isPresent()){
			Optional<Category> optCategory =catRepo.findById(categoryId);
			 if(optCategory.isPresent()) {
				 Product product =optProduct.get();
				 Category category=optCategory.get();
				 product.setCategory(category);
				 return productRepo.save(product);
			 }
		}
		return null;
	}

	@Override
	public List<Product> filterProductByCategoriess(List<String> categoryName) {
		List<Product> productList =	productRepo.findAll().stream().filter(prod->categoryName.contains(prod.getCategory().getName() )).collect(Collectors.toList());
		return productList;
	}

	@Override
	public Product updateProductOcassion(Long productId,OcassionEnumType ocassion) {
	   
		Optional<Product> optProduct =productRepo.findById(productId);
		if(optProduct.isPresent()){
			
				 Product product =optProduct.get();
				
				 product.setOcassions(ocassion);
				    System.out.println(product.getOcassions());
				 return productRepo.save(product);
			
			 }
		
		return null;
	}

	

}
