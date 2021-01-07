package com.rates.model;

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
@Table(name = "average_rate")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class AverageRate extends AbstractEntity {

	@JsonProperty("sourceCurrency")
	@Column(name = "sourceCurrency")
	private String sourceCurrency;
	
	@JsonProperty("targetCurrency")
	@Column(name = "targetCurrency")
	private String targetCurrency;
	
	@JsonProperty("date")
	@Column(name = "date")
	private LocalDateTime date;
	
	@JsonProperty("rateBuy")
	@Column(name = "rateBuy")
	private float rateBuy;
	
	@JsonProperty("rateSell")
	@Column(name = "rateSell")
	private float rateSell;

	public AverageRate(String sourceCurrency, String targetCurrency, LocalDateTime date, float rateBuy, float rateSell) {
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.date = date;
		this.rateBuy = rateBuy;
		this.rateSell = rateSell;
	}
}
