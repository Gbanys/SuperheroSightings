package com.superherosightings.main.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class SuperheroLocationKey implements Serializable{
	
	private int superheroId;
	private int locationId;
	
	public int getSuperheroId() {
		return this.superheroId;
	}
	
	public int getLocationId() {
		return this.locationId;
	}
}
