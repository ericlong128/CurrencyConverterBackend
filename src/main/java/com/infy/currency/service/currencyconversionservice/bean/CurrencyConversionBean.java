package com.infy.currency.service.currencyconversionservice.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="table_currencyConversion")
public class CurrencyConversionBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String rate_from;
	
	@Column
	private String rate_to;
	
	@Column
	private String quantity;
	
	@Column
	private String exchangeRate;
	
	@Column
	private String totalCalcAmount;
	
	@Column
	private int port;
	
	public CurrencyConversionBean() {
		
	}

	public CurrencyConversionBean(Long id, String rate_from, String rate_to, String quantity, String exchangeRate,
			String totalCalcAmount, int port) {
		super();
		this.id = id;
		this.rate_from = rate_from;
		this.rate_to = rate_to;
		this.quantity = quantity;
		this.exchangeRate = exchangeRate;
		this.totalCalcAmount = totalCalcAmount;
		this.port = port;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRate_from() {
		return rate_from;
	}

	public void setRate_from(String rate_from) {
		this.rate_from = rate_from;
	}

	public String getTo() {
		return rate_to;
	}

	public void setTo(String rate_to) {
		this.rate_to = rate_to;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getTotalCalcAmount() {
		return totalCalcAmount;
	}

	public void setTotalCalcAmount(String totalCalcAmount) {
		this.totalCalcAmount = totalCalcAmount;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
