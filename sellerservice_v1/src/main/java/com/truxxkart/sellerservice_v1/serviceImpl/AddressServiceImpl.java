package com.truxxkart.sellerservice_v1.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.sellerservice_v1.entity.Address;
import com.truxxkart.sellerservice_v1.entity.User;
import com.truxxkart.sellerservice_v1.repository.AddressRepository;
import com.truxxkart.sellerservice_v1.repository.SellerRepository;
import com.truxxkart.sellerservice_v1.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addRepo;

	@Autowired
	SellerRepository sellerRepo;

	@Override
	public Address createAddress(Long userId, Address address) {
		Optional<User> user = sellerRepo.findById(userId);
		if (user.isPresent()) {
			address.setSeller(user.get());
			Address addressCreated = addRepo.save(address);
			return addressCreated;
		}

		return null;

	}

	@Override
	public List<Address> getAllAddress() {
		List<Address> addressList = addRepo.findAll();
		return addressList;
	}

	@Override
	public List<Address> getAddressBySellerId(Long sellerId) {
		Optional<User> seller = sellerRepo.findById(sellerId);
		if (seller.isPresent()) {
			return seller.get().getAddress();
		}
		return null;
	}

	@Override
	public Address updateAddressStatus(Long sellerId, Long addressId, String field, Boolean toBeUpdated) {

		if (field.equalsIgnoreCase("ACTIVE")) {
			Optional<Address> optAddress = addRepo.findById(addressId);
			if (optAddress.isPresent()) {
				Address address = optAddress.get();
				address.setIsActive(toBeUpdated);
				return addRepo.save(address);
			}
		} else if (field.equalsIgnoreCase("Default")) {
			Optional<User> seller = sellerRepo.findById(sellerId);
			List<Address> addressList = null;
			if (seller.isPresent()) {
				addressList = getAddressBySellerId(sellerId);
				addressList.stream().forEach(address -> {
					address.setIsDefault(false);
					addRepo.save(address);
				});

				Optional<Address> optAddress = addRepo.findById(addressId);
				if (optAddress.isPresent()) {
					Address address = optAddress.get();
					address.setIsDefault(toBeUpdated);
					return addRepo.save(address);
				}

			}

		}

		return null;
	}

	@Override
	public Address updateAddressField(Long addressId, String field, String toBeUpdated) {
		Optional<Address> optAddress = addRepo.findById(addressId);
		if (optAddress.isPresent()) {
			Address address = optAddress.get();
			if (field.equalsIgnoreCase("ADDRESSLINE")) {
				address.setAddressLine(toBeUpdated);
			} else if (field.equalsIgnoreCase("CITY")) {
				address.setCity(toBeUpdated);
			} else if (field.equalsIgnoreCase("STATE")) {
				address.setState(toBeUpdated);
			} else if (field.equalsIgnoreCase("POSTALCODE")) {
				address.setPostalCode(toBeUpdated);
			} else if (field.equalsIgnoreCase("TYPE")) {
				address.setType(toBeUpdated);
			}

			return addRepo.save(address);

		}
		return null;
	}

	@Override
	public String deleteAddressById(Long addressId) {
		Optional<Address> optAddress = addRepo.findById(addressId);
		if (optAddress.isPresent()) {
			
			addRepo.deleteById(addressId);
			return "Address Deleted Successfully";
		}
		return null;
	}

}
