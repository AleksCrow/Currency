package com.rates.providers.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected long id;
}
