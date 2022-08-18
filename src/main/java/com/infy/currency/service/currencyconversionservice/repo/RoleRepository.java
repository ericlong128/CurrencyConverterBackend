package com.infy.currency.service.currencyconversionservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.currency.service.currencyconversionservice.bean.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String name);
}
