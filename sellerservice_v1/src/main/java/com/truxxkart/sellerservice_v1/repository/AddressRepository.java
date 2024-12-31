package com.truxxkart.sellerservice_v1.repository;



//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxxkart.sellerservice_v1.entity.Address;
//import com.truxxkart.sellerservice_v1.entity.User;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
//	Optional<User> findByEmail(String email);
}
