package com.rates.providers.model;

import java.time.LocalDateTime;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rates.utils.MonobankUtils;
import com.rates.utils.serialize.LocalDateTimeDeserializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "monobank")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Monobank extends AbstractEntity {
	
	@JsonProperty("currencyCodeA")
	@Column(name = "currencyCodeA")
	private int sourceCurrency;
	
	@JsonProperty("currencyCodeB")
	@Column(name = "currencyCodeB")
	private int targetCurrency;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty(value = "date")
	@Column(name = "date")
	private LocalDateTime date;
	
	@JsonProperty("rateBuy")
	@Column(name = "rateBuy")
	private float rateBuy;
	
	@JsonProperty("rateSell")
	@Column(name = "rateSell")
	private float rateSell;

	public Monobank(Long id, int sourceCurrency, int targetCurrency, LocalDateTime date, float rateBuy, float rateSell) {
		super(id);
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.date = date;
		this.rateBuy = rateBuy;
		this.rateSell = rateSell;
	}

	public Currency getSourceCurrency() {
		return MonobankUtils.getCurrencyInstance(sourceCurrency);
	}

	public Currency getTargetCurrency() {
		return MonobankUtils.getCurrencyInstance(targetCurrency);
	}
	
	public void setSourceCurrency(int sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public void setTargetCurrency(int targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
}
