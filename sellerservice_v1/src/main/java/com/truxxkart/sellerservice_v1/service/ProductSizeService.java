package com.truxxkart.sellerservice_v1.service;

import java.util.List;

import com.truxxkart.sellerservice_v1.entity.ProductSize;
import com.truxxkart.sellerservice_v1.entity.ProductVariant;

public interface ProductSizeService {
	public ProductSize addProductSizes(ProductSize prodSize,Long pvId);
	public ProductSize getProductSizesOfProductVariant(Long prodSizeId, Long pvId);
	public List<ProductSize> getProductSizeByProductVariantId(Long pvId) ;
	public ProductSize getProductSizeBySizeId(Long prodSizeId);
	public List<ProductSize> getAllProductSizes();
	
	public List<ProductSize> getAllProductSizesByStatus(String field,Boolean findBy);
	public ProductSize updateSize(Long prodSizeId,String size);
	public ProductSize updatePrice(Long prodSizeId,Double additionalPrice,Double additionalDiscountedPrice);
	public ProductSize updateStock(Long prodSizeId,Integer stock);
	public ProductSize updateStatus(Long prodSizeId,String field,Boolean toBeUpdated);
	public String deleteProductSize(Long prodSizeId);

}
