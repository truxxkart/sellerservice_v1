package com.truxxkart.sellerservice_v1.service;

import java.util.List;

import com.truxxkart.sellerservice_v1.entity.Address;


public interface AddressService {
	public Address createAddress(Long userId, Address address);

	public List<Address> getAllAddress();

	public List<Address> getAddressBySellerId(Long sellerId);

	public Address updateAddressStatus(Long sellerId,Long addressId, String field, Boolean toBeUpdated);

	public Address updateAddressField(Long addressId, String field, String toBeUpdated);

	public String deleteAddressById(Long addressId);
}
