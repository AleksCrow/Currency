package com.rates.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "monobank")
public class Monobank extends AbstractEntity {
	
	@JsonProperty("currencyCodeA")
	@Column(name = "currencyCodeA")
	private int sourceCurrency;
	
	@JsonProperty("currencyCodeB")
	@Column(name = "currencyCodeB")
	private int targetCurrency;
	
	@JsonProperty("date")
	@Column(name = "date")
	private long date;
	
	@JsonProperty("rateBuy")
	@Column(name = "rateBuy")
	private float rateBuy;
	
	@JsonProperty("rateSell")
	@Column(name = "rateSell")
	private float rateSell;
	
	@JsonProperty("rateCross")
	@Column(name = "rateCross")
	private float rateCross;
	
	public Monobank() {
	}

	public Monobank(Long id, int sourceCurrency, int targetCurrency, long date, float rateBuy, float rateSell) {
		super(id);
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.date = date;
		this.rateBuy = rateBuy;
		this.rateSell = rateSell;
	}

	public int getSourceCurrency() {
		return sourceCurrency;
	}

	public int getTargetCurrency() {
		return targetCurrency;
	}

	public long getDate() {
		return date;
	}

	public float getRateBuy() {
		return rateBuy;
	}

	public float getRateSell() {
		return rateSell;
	}

	public void setSourceCurrency(int sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public void setTargetCurrency(int targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void setRateBuy(float rateBuy) {
		this.rateBuy = rateBuy;
	}

	public void setRateSell(float rateSell) {
		this.rateSell = rateSell;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (date ^ (date >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monobank other = (Monobank) obj;
		if (date != other.date)
			return false;
		return true;
	}
}
