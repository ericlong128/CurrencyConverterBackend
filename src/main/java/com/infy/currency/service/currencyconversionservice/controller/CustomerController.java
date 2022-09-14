package com.infy.currency.service.currencyconversionservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.currency.service.currencyconversionservice.bean.User;
import com.infy.currency.service.currencyconversionservice.dto.SignUpRequestDTO;
import com.infy.currency.service.currencyconversionservice.repo.ICustomerRepo;
import com.infy.currency.service.currencyconversionservice.service.ICustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	@Autowired
	ICustomerRepo customerRepo;

	@GetMapping("/customers")
	public ResponseEntity<List<User>> getAllCustomers() {
		try {
			List<User> list = customerService.get();

			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<User> getCustomer(@PathVariable Long id) {
		Optional<User> customer = customerService.get(id);

		if (customer.isPresent()) {
			return new ResponseEntity<User>(customer.get(), HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/customers")
	public ResponseEntity<User> saveCustomer(@RequestBody User customer) {
		try {
			return new ResponseEntity<User>(customerService.add(customer), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customers")
	public ResponseEntity<String> updateCustomer(@RequestBody SignUpRequestDTO dto) throws Exception {
		customerService.update(dto);
		return new ResponseEntity<String>("User has been updated successfully!", HttpStatus.OK);

	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
		try {
			customerService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//-----OTP API CONTROLLERS BELOW!!!------
	
	@PostMapping("/otp/{username}/{otpToken}")
	public boolean checkOTP(@PathVariable String username, @PathVariable Long otpToken) {
		return customerService.checkOTPToken(username, otpToken);
	}

	@PutMapping("/generateOTP/{username}")
	public ResponseEntity<String> generateOTP(@PathVariable String username) throws Exception {
		Integer newToken = customerService.generateToken();
		Optional<User> optUser = customerRepo.findByUsername(username);
		User customerToUpdate = optUser.orElseThrow(()-> new Exception("Customer not found!"));
		customerToUpdate.setOtpToken(newToken);
		customerRepo.save(customerToUpdate);
		return new ResponseEntity<String>("OTP GENERATED!" + newToken, HttpStatus.OK);
	}
}
