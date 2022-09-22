package com.superherosightings.main.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(locationId, superheroId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuperheroLocationKey other = (SuperheroLocationKey) obj;
		return locationId == other.locationId && superheroId == other.superheroId;
	}
}
