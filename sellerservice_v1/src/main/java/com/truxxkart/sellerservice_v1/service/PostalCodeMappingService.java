package com.truxxkart.sellerservice_v1.service;

import java.util.List;

import com.truxxkart.sellerservice_v1.entity.PostalCodeMapping;

public interface PostalCodeMappingService {

	public PostalCodeMapping createPostalCodeMapping(PostalCodeMapping postalCodeMapping);

	public List<PostalCodeMapping> getAllPostalCodeMapping();

	public PostalCodeMapping getPostalCodeMappingBySourcePostalCode(String sourcePostalCodeMapping);

	public PostalCodeMapping updatePostalCodeMappingIsActive(String sourcePostalCodeMapping, Boolean toBeUpdated);

	public PostalCodeMapping updatePostalCodeMappingDestinationPotalCode(String sourcePostalCodeMapping, String field,
			String toBeUpdated);   //field =add,remove destnation code from set of destination codes
    
}
