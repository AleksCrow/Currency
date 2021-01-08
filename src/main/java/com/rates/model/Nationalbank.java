package com.rates.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "nationalbank")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Nationalbank extends AbstractEntity {

	@JsonProperty("currency")
	@Column(name = "currency")
	private String sourceCurrency;
	
	@JsonProperty("targetCurrency")
	@Column(name = "targetCurrency")
	private String targetCurrency = "uah";
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonProperty("date")
	@Column(name = "date")
	private LocalDateTime date;
	
	@JsonProperty("bid")
	@Column(name = "bid")
	private String rateBuy;
	
	@JsonProperty("ask")
	@Column(name = "ask")
	private String rateSell;

	public Nationalbank(Long id, String sourceCurrency, LocalDateTime date, String rateBuy, String rateSell) {
		super(id);
		this.sourceCurrency = sourceCurrency;
		this.date = date;
		this.rateBuy = rateBuy;
		this.rateSell = rateSell;
	}
}
