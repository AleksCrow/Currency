package com.rates.providers.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "privatbank")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Privatbank extends AbstractEntity {
	
	@JsonProperty("ccy")
	@Column(name = "ccy")
	private String sourceCurrency;
	
	@JsonProperty("base_ccy")
	@Column(name = "base_ccy")
	private String targetCurrency;
	
	@JsonProperty("date")
	@Column(name = "date")
	private LocalDateTime date;
	
	@JsonProperty("buy")
	@Column(name = "buy")
	private String rateBuy;
	
	@JsonProperty("sale")
	@Column(name = "sale")
	private String rateSell;

	public Privatbank(Long id, String sourceCurrency, String targetCurrency, String rateBuy, String rateSell) {
		super(id);
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.rateBuy = rateBuy;
		this.rateSell = rateSell;
	}

	public Privatbank(Long id, String sourceCurrency, String targetCurrency, LocalDateTime date, String rateBuy,
			String rateSell) {
		super(id);
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.date = date;
		this.rateBuy = rateBuy;
		this.rateSell = rateSell;
	}

	public LocalDateTime getDate() {
		if (date == null) {
			return LocalDateTime.now();
		}
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
