package com.infy.currency.service.currencyconversionservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.currency.service.currencyconversionservice.bean.Customer;



@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {

}