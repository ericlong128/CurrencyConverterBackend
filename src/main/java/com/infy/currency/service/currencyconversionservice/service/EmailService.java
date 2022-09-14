package com.infy.currency.service.currencyconversionservice.service;

import com.infy.currency.service.currencyconversionservice.bean.EmailDetails;

public interface EmailService {

	public String sendSimpleMail(EmailDetails details);
}
