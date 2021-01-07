package com.rates.utils;

import com.rates.model.AverageRate;
import com.rates.model.Monobank;
import com.rates.model.Privatbank;

public class AverageRateUtils {

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
}
