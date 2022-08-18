package com.infy.currency.service.currencyconversionservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.currency.service.currencyconversionservice.bean.User;
import com.infy.currency.service.currencyconversionservice.repo.ICustomerRepo;


@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepo customerRepo;
	
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
	public void delete(Long id) {
		if (get(id).isPresent()) {
			customerRepo.delete((User) get(id).get());
		}
	}

	@Override
	public User update(User customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
