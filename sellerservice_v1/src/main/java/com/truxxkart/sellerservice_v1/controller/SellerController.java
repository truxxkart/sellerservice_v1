package com.truxxkart.sellerservice_v1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truxxkart.sellerservice_v1.entity.User;
import com.truxxkart.sellerservice_v1.repository.SellerRepository;
import com.truxxkart.sellerservice_v1.serviceImpl.SellerServiceImpl;

@RestController
@RequestMapping("/sellers")
public class SellerController {
	@Autowired
	SellerServiceImpl sellerService;
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	@Autowired
	SellerRepository sellerRepo;
	
//	@Autowired
//	Environment env;
//	
//	@Autowired
//	AuthenticationManager authenticationManager;
	
//	 @PostMapping("/register")
//	    public ResponseEntity<String> registerUser(@RequestBody User seller) {
//	        try {
//	                String hashPwd = passwordEncoder.encode(seller.getPassword());
//	                seller.setPassword(hashPwd);
//	                User newSeller = sellerRepo.save(seller);
//
//	                if(newSeller.getId() >0) {
//	                    return ResponseEntity.status(HttpStatus.CREATED).
//	                            body("Given seller details are successfully registered");
//	                } else {
//	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).
//	                            body("Seller registration failed");
//	                }
//	        } catch (Exception ex) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
//	                    body("An exception occurred: " + ex.getMessage());
//	        }
//
//	    }
	
	
	
	
	
	
	
	@PostMapping()
	public ResponseEntity<User> createSeller(@RequestBody User seller){
		User createdSeller =sellerService.createSeller(seller);
		return new ResponseEntity<User>(createdSeller,HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> getAllSeller(){
		List<User> allSellers =sellerService.getAllSeller();
		return new ResponseEntity<List<User>>(allSellers,HttpStatus.OK);
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getSellerById(@PathVariable Long id){
		User sellerById =sellerService.getSellerById(id);
		return new ResponseEntity<User>(sellerById,HttpStatus.OK);
	}
	
	
	@GetMapping("/field")
	public ResponseEntity<User> getSellerByField(@RequestParam String field,@RequestParam String findBy){
		User user =sellerService.findByfield(field, findBy);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<User>> getSellerByVerificationOrActivation(@RequestParam String field,@RequestParam Boolean findBy){
		List<User> userList =sellerService.findUserByVerificationOrActivation(field, findBy);
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@GetMapping("/role-or-type")
	public ResponseEntity<List<User>> findSellerByRoleOrType(@RequestParam String field,@RequestParam String findBy){
		List<User> userList =sellerService.findUserByRoleOrType(field, findBy);
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@GetMapping("/creation-date")
	public ResponseEntity<List<User>> findSellerByCreationDate(@RequestParam LocalDate date){
		List<User> userList =sellerService.findUserByCreationDate(date);
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	@PutMapping("/update/id/{userId}")
	public ResponseEntity<User> updateSellerProfile( @PathVariable Long userId ,@RequestParam String field,@RequestParam String toBeUpdated){
		User user =sellerService.updateUserProfile(userId, field, toBeUpdated);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@PutMapping("/update/status/id/{userId}")
	public ResponseEntity<User> updateSellerStatus( @PathVariable Long userId ,@RequestParam String field,@RequestParam Boolean toBeUpdated){
		User user =sellerService.updateUserStatus(userId, field, toBeUpdated);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
//   @PutMapping()
//   public ResponseEntity<User> updateSeller(@RequestBody User seller){
//	   User mySeller =sellerService.updateSeller(seller);
//	   return new ResponseEntity<User>(mySeller,HttpStatus.CREATED);
//   }
//   
//   @PostMapping("/apiLogin")
//   public ResponseEntity<LoginResponseDTO> apiLogin (@RequestBody LoginRequestDTO loginRequest) {
//       String jwt = "";
//       Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),
//               loginRequest.password());
//       Authentication authenticationResponse = authenticationManager.authenticate(authentication);
//       if(null != authenticationResponse && authenticationResponse.isAuthenticated()) {
//           if (null != env) {
//               String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
//                       ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
//               SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//                jwt = Jwts.builder().issuer("Truxx kart").subject("JWT Token")
//                       .claim("username", authenticationResponse.getName())
//                       .claim("authorities", authenticationResponse.getAuthorities().stream().map(
//                               GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
//                       .issuedAt(new java.util.Date())
//                       .expiration(new java.util.Date((new java.util.Date()).getTime() + 300000))
//                       .signWith(secretKey).compact();
//           }
//       }
//       return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwt)
//               .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
//   }
//
//   @RequestMapping("/user")
//   public User getUserDetailsAfterLogin(Authentication authentication) {
//       Optional<User> optionalCustomer = sellerRepo.findByEmail(authentication.getName());
//       return optionalCustomer.orElse(null);
//   }
//   
}
