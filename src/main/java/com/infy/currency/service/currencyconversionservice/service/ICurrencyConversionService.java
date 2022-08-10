package com.infy.currency.service.currencyconversionservice.service;

import java.util.List;
import java.util.Optional;

import com.infy.currency.service.currencyconversionservice.bean.CurrencyConversionBean;

public interface ICurrencyConversionService {

	CurrencyConversionBean add(CurrencyConversionBean currencyBean);
	
	List get();
	
	CurrencyConversionBean update(CurrencyConversionBean currencyBean);
	
	Optional get(Long id);
	
	void delete(Long id);
}
