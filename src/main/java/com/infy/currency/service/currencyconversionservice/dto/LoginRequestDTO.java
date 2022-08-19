package com.infy.currency.service.currencyconversionservice.dto;

public class LoginRequestDTO {

    private String emailId;
    private String password;
    private String usernameOrEmail;
    private String message;

    public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getMessage() {
    	return message;
    }

	public void setMessage(String message) {
		this.message = message;
	}
}
