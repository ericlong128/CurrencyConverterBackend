package com.infy.currency.service.currencyconversionservice.service;

import java.util.List;
import java.util.Optional;

import com.infy.currency.service.currencyconversionservice.bean.Customer;


public interface ICustomerService {
		
		Customer add(Customer customer);
		
		List get();
		
		Customer update(Customer customer);
		
		Optional get(Long id);
		
		void delete(Long id);
}
