package com.infy.currency.service.currencyconversionservice.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infy.currency.service.currencyconversionservice.bean.CurrencyConversionBean;
import com.infy.currency.service.currencyconversionservice.service.ICurrencyConversionService;

@RestController
@RequestMapping("/api")
public class CurrencyConversionCalculator {

	@Autowired
	ICurrencyConversionService currencyConversionService;
	
	@GetMapping("/currencyconversionservice/from/{from}/to/{to}/{quantity}")
	public CurrencyConversionBean getConversionDetails(@PathVariable("from") String from,@PathVariable("to")  String to,
			@PathVariable("quantity")  BigDecimal quantity) {
		Map<String, String> uriVariable = new HashMap<String, String>();
		uriVariable.put("from", from);
		uriVariable.put("to", to);
		
		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean();
		try {
			String url_str = "https://api.exchangerate.host/convert?from="+from+"&to="+to+"&amount="+quantity;

			URL url = new URL(url_str);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonObject jsonobj = root.getAsJsonObject();

			String req_result = jsonobj.get("result").toString();
			
			JsonObject req_type = jsonobj.get("info").getAsJsonObject();
			
			String rate = req_type.get("rate").toString();
			
			JsonObject res_query = jsonobj.get("query").getAsJsonObject();
			String res_from = res_query.get("from").toString();
			String res_to = res_query.get("to").toString();
			String res_quantity = res_query.get("amount").toString();
			
			CurrencyConversionBean obj = new CurrencyConversionBean();
			
			obj.setExchangeRate(rate);
			obj.setRate_from(res_from);
			obj.setTo(res_to);
			obj.setQuantity(res_quantity);
			obj.setTotalCalcAmount(req_result);
			
			currencyConversionBean  = currencyConversionService.add(obj);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return currencyConversionBean; 
		
	}
}
