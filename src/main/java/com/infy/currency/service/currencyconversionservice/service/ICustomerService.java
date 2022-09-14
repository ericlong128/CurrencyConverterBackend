package com.infy.currency.service.currencyconversionservice.service;

import java.util.List;
import java.util.Optional;

import com.infy.currency.service.currencyconversionservice.bean.User;
import com.infy.currency.service.currencyconversionservice.dto.SignUpRequestDTO;


public interface ICustomerService {
		
		User add(User customer);
		
		List get();
		
		void update(SignUpRequestDTO dto) throws Exception;
		
		Optional get(Long id);
		
		Long get(String username);
		
		void delete(Long id);
		
		public boolean checkOTPToken(String username, Long otpToken);
		
		public Integer generateToken();
		
		void updateToken(SignUpRequestDTO signUpRequestDTO) throws Exception;
}
