package com.truxxkart.sellerservice_v1.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.ProductSize;
import com.truxxkart.sellerservice_v1.entity.ProductVariant;
import com.truxxkart.sellerservice_v1.repository.ProductSizeRepository;
import com.truxxkart.sellerservice_v1.repository.ProductVariantRepository;
import com.truxxkart.sellerservice_v1.service.ProductSizeService;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

	@Autowired
	private ProductSizeRepository psRepo;
	@Autowired
	private ProductVariantRepository pvRepo;

	@Override
	public ProductSize addProductSizes(ProductSize prodSize, Long pvId) {
		Optional<ProductVariant> optPV = pvRepo.findById(pvId);
		if (optPV.isPresent()) {

			prodSize.setProductVariant(optPV.get());
			return psRepo.save(prodSize);
		}
		return null;
	}

	@Override
	public List<ProductSize> getProductSizeByProductVariantId(Long pvId) {
		Optional<ProductVariant> optProdVariant = pvRepo.findById(pvId);
		if (optProdVariant.isPresent()) {
			ProductVariant pv = optProdVariant.get();
			return pv.getSizes();

		}
		return null;
	}

	@Override
	public ProductSize getProductSizeBySizeId(Long prodSizeId) {
		ProductSize prodSize = psRepo.findById(prodSizeId).get();
		return prodSize;
	}

	@Override
	public List<ProductSize> getAllProductSizes() {
		List<ProductSize> psList = psRepo.findAll();
		return psList;
	}

//	@Override
//	public ProductSize getProductSizes(Long prodSizeId, Long pvId) {
//		
//		List<ProductSize> psList =getProductSizeByProductVariantId(pvId);
//		return psList.stream().filter(ps->ps.getId()==prodSizeId).findFirst().get();
//		
//	
//	}

	@Override
	public ProductSize getProductSizesOfProductVariant(Long prodSizeId, Long pvId) {
		Optional<ProductVariant> optProdVariant = pvRepo.findById(pvId);
		if (optProdVariant.isPresent()) {
			ProductVariant pv = optProdVariant.get();
			return pv.getSizes().stream().filter(ps -> ps.getId() == prodSizeId).findFirst().get();

		}
		return null;
	}

	@Override
	public List<ProductSize> getAllProductSizesByStatus(String field, Boolean findBy) {
		if (field.equalsIgnoreCase("ACTIVATION")) {
			return psRepo.findAll().stream().filter(ps -> ps.getIsActive() == findBy).collect(Collectors.toList());

		} else if (field.equalsIgnoreCase("AVAILABLE")) {
			return psRepo.findAll().stream().filter(ps -> ps.getIsAvailable() == findBy).collect(Collectors.toList());

		}
		return null;

	}

	@Override
	public ProductSize updateSize(Long prodSizeId, String size) {
		Optional<ProductSize> optProductSize = psRepo.findById(prodSizeId);
		if (optProductSize.isPresent()) {
			ProductSize ps = optProductSize.get();
			ps.setSize(size);
			return psRepo.save(ps);
		}
		return null;
	}

	@Override
	public ProductSize updatePrice(Long prodSizeId, Double additionalPrice, Double additionalDiscountedPrice) {
		Optional<ProductSize> optProductSize = psRepo.findById(prodSizeId);
		if (optProductSize.isPresent()) {
			ProductSize ps = optProductSize.get();
			ps.setAdditionalPrice(additionalPrice);
			ps.setAdditionalDiscountedPrice(additionalDiscountedPrice);
			return psRepo.save(ps);
		}
		return null;
	}

	@Override
	public ProductSize updateStock(Long prodSizeId, Integer stock) {
		Optional<ProductSize> optProductSize = psRepo.findById(prodSizeId);
		if (optProductSize.isPresent()) {
			ProductSize ps = optProductSize.get();
			ps.setStock(stock);
			return psRepo.save(ps);
		}
		return null;
	}

	@Override
	public ProductSize updateStatus(Long prodSizeId, String field, Boolean toBeUpdated) {
		Optional<ProductSize> optProductSize = psRepo.findById(prodSizeId);
		if (optProductSize.isPresent()) {
			ProductSize ps = optProductSize.get();
			if (field.equalsIgnoreCase("ACTIVATION")) {
				ps.setIsActive(toBeUpdated);
			} else if (field.equalsIgnoreCase("AVAILABLE")) {
				ps.setIsAvailable(toBeUpdated);
			}

			return psRepo.save(ps);
		}
		return null;
	}

	@Override
	public String deleteProductSize(Long prodSizeId) {
		psRepo.deleteById(prodSizeId);
		return "Product's size is deleted" ;
	}

}
