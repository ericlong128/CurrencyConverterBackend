package com.infy.currency.service.currencyconversionservice.service;

import java.util.List;
import java.util.Optional;

import com.infy.currency.service.currencyconversionservice.bean.User;


public interface ICustomerService {
		
		User add(User customer);
		
		List get();
		
		User update(User customer);
		
		Optional get(Long id);
		
		void delete(Long id);
}
