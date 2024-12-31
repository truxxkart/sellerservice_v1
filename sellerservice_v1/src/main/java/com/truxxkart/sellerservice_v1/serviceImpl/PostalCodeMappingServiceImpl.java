package com.truxxkart.sellerservice_v1.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.PostalCodeMapping;
import com.truxxkart.sellerservice_v1.repository.PostalCodeMappingRepository;
import com.truxxkart.sellerservice_v1.service.PostalCodeMappingService;
@Service
public class PostalCodeMappingServiceImpl implements PostalCodeMappingService {
     
	@Autowired
	private PostalCodeMappingRepository pcmRepo;
	
	@Override
	public PostalCodeMapping createPostalCodeMapping(PostalCodeMapping postalCodeMapping) {
		
		return pcmRepo.save(postalCodeMapping);
	}

	@Override
	public List<PostalCodeMapping> getAllPostalCodeMapping() {
		// TODO Auto-generated method stub
		return pcmRepo.findAll();
	}

	@Override
	public PostalCodeMapping getPostalCodeMappingBySourcePostalCode(String sourcePostalCodeMapping) {
	Optional<PostalCodeMapping> optPostalCode =	pcmRepo.findBySourcePincode(sourcePostalCodeMapping);
	if(optPostalCode.isPresent()) {
		return optPostalCode.get();
	}
		return null;
	}

	@Override
	public PostalCodeMapping updatePostalCodeMappingIsActive(String sourcePostalCodeMapping, Boolean toBeUpdated) {
		PostalCodeMapping postalCodeMapping =   getPostalCodeMappingBySourcePostalCode(sourcePostalCodeMapping);
		if(postalCodeMapping!=null) {
			postalCodeMapping.setIsActive(toBeUpdated);
			return pcmRepo.save(postalCodeMapping);
		}
		return null;
	}

	@Override
	public PostalCodeMapping updatePostalCodeMappingDestinationPotalCode(String sourcePostalCodeMapping, String field,
			String toBeUpdated) {
		PostalCodeMapping postalCodeMapping =   getPostalCodeMappingBySourcePostalCode(sourcePostalCodeMapping);
		if(postalCodeMapping!=null) {
			if(field.equalsIgnoreCase("ADD")) {
				Set<String> destinationPostalCodes =postalCodeMapping.getDestinationPincodes();
				destinationPostalCodes.add(toBeUpdated);
			}
			else if(field.equalsIgnoreCase("REMOVE")) {
				Set<String> destinationPostalCodes =postalCodeMapping.getDestinationPincodes();
				destinationPostalCodes.remove(toBeUpdated);
			}
			
			return pcmRepo.save(postalCodeMapping);
		}
		return null;
	}

}
