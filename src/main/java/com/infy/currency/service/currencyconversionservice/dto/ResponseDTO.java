package com.infy.currency.service.currencyconversionservice.dto;

import java.util.Set;

import com.infy.currency.service.currencyconversionservice.bean.Role;

public class ResponseDTO {

	private long id;
	private String name;
    private String username;
    private String email;
    private String password;
    private Long age;
    private String phoneNumber;
    private Set<Role> role;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> set) {
		this.role = set;
	}	
	
	

}
