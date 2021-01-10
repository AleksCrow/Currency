package com.rates.utils;

import com.rates.average.model.AverageRate;
import com.rates.providers.model.Monobank;
import com.rates.providers.model.Nationalbank;
import com.rates.providers.model.Privatbank;

public class ProviderToAverageRate {

	public static AverageRate converter(Monobank rate) {
		return new AverageRate(rate.getSourceCurrency().toString(), 
				rate.getTargetCurrency().toString(), 
				rate.getDate(), 
				rate.getRateBuy(), 
				rate.getRateSell());
	}
	
	public static AverageRate converter(Privatbank rate) {
		return new AverageRate(rate.getSourceCurrency().equals("RUR") ? "RUB" : rate.getSourceCurrency(), 
				rate.getTargetCurrency(), 
				rate.getDate(), 
				Float.parseFloat(rate.getRateBuy()), 
				Float.parseFloat(rate.getRateSell()));
	}
	
	public static AverageRate converter(Nationalbank rate) {
		return new AverageRate(rate.getSourceCurrency().toUpperCase(), 
				rate.getTargetCurrency().toUpperCase(), 
				rate.getDate(), 
				Float.parseFloat(rate.getRateBuy()), 
				Float.parseFloat(rate.getRateSell()));
	}
}
