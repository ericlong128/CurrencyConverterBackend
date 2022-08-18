package com.infy.currency.service.currencyconversionservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.currency.service.currencyconversionservice.bean.CurrencyConversionBean;
import com.infy.currency.service.currencyconversionservice.repo.ICurrencyConversionRepo;

@Service
public class CurrencyConversionServiceImpl implements ICurrencyConversionService {

	@Autowired
	private ICurrencyConversionRepo iCurrencyConversionRepo;
	
	@Override
	public CurrencyConversionBean add(CurrencyConversionBean currencyBean) {
		return iCurrencyConversionRepo.save(currencyBean);
	}

	@Override
	public List get() {
		return null;
	}

	@Override
	public CurrencyConversionBean update(CurrencyConversionBean currencyBean) {
		return null;
	}

	@Override
	public Optional get(Long id) {
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
