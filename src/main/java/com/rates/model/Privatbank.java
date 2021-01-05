package com.rates.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "privatbank")
public class Privatbank extends AbstractEntity {
	
	@JsonProperty("ccy")
	@Column(name = "ccy")
	private String ccy;
	
	@JsonProperty("base_ccy")
	@Column(name = "base_ccy")
	private String base_ccy;
	
	@JsonIgnore
	@Column(name = "date")
	private LocalDate date = LocalDate.now();
	
	@JsonProperty("buy")
	@Column(name = "buy")
	private String buy;
	
	@JsonProperty("sale")
	@Column(name = "sale")
	private String sale;

	public Privatbank(Long id, String ccy, String base_ccy, String buy, String sale) {
		super(id);
		this.ccy = ccy;
		this.base_ccy = base_ccy;
		this.buy = buy;
		this.sale = sale;
	}

	public Privatbank() {
	}

	public LocalDate getDate() {
		return date;
	}

	public String getBuy() {
		return buy;
	}

	public String getSale() {
		return sale;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setRateBuy(String buy) {
		this.buy = buy;
	}

	public void setRateSell(String sale) {
		this.sale = sale;
	}

	public String getCcy() {
		return ccy;
	}

	public String getBase_ccy() {
		return base_ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public void setBase_ccy(String base_ccy) {
		this.base_ccy = base_ccy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((base_ccy == null) ? 0 : base_ccy.hashCode());
		result = prime * result + ((ccy == null) ? 0 : ccy.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((sale == null) ? 0 : sale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Privatbank other = (Privatbank) obj;
		if (base_ccy == null) {
			if (other.base_ccy != null)
				return false;
		} else if (!base_ccy.equals(other.base_ccy))
			return false;
		if (ccy == null) {
			if (other.ccy != null)
				return false;
		} else if (!ccy.equals(other.ccy))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (sale == null) {
			if (other.sale != null)
				return false;
		} else if (!sale.equals(other.sale))
			return false;
		return true;
	}
}
