package com.infy.currency.service.currencyconversionservice.service;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infy.currency.service.currencyconversionservice.bean.Role;
import com.infy.currency.service.currencyconversionservice.bean.User;
import com.infy.currency.service.currencyconversionservice.dto.SignUpRequestDTO;
import com.infy.currency.service.currencyconversionservice.repo.ICustomerRepo;


@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepo customerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User add(User customer) {
		return customerRepo.save(customer);
	}

	@Override
	public List get() {
		return customerRepo.findAll();
	}

	@Override
	public Optional get(Long id) {
		return customerRepo.findById(id);
	}
	
	@Override
	public Long get(String username) {
		return customerRepo.findByUsername(username).get().getId();
	}

	@Override
	public void delete(Long id) {
		if (get(id).isPresent()) {
			customerRepo.delete((User) get(id).get());
		}
	}

	@Override
	public void update(SignUpRequestDTO dto) throws Exception{
		Optional<User> customer = customerRepo.findByEmail(dto.getEmail());
		User user = customer.orElseThrow(()->new Exception("Customer not found!")); 
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAge(dto.getAge());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        customerRepo.save(user);
	}
	
	@Override
	public boolean checkOTPToken(String username, Long otpToken) {
		Optional<User> checkUser = customerRepo.findByUsername(username);
		long token = checkUser.get().getOtpToken();
		if (otpToken != token) {
			return false;
		}
		return true;
	}
	
	@Override
	public Integer generateToken() {
		SecureRandom random = new SecureRandom();
		Integer newToken = random.nextInt(1000000-100000) + 100000;
		return newToken;
	}
	
	@Override
	public void updateToken(SignUpRequestDTO signUpRequestDTO) throws Exception {
		Optional<User> optUser= customerRepo.findByEmail(signUpRequestDTO.getUsername());
		User user = optUser.orElseThrow(()->new Exception("Customer not found!"));
//	        user.setName(signUpRequestDTO.getName());
//	        user.setUsername(signUpRequestDTO.getUsername());
//	        user.setEmail(signUpRequestDTO.getEmail());
//	        user.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
//	        user.setAge(signUpRequestDTO.getAge());
//	        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
	        user.setOtpToken(generateToken());
	        customerRepo.save(user);
	}

}
