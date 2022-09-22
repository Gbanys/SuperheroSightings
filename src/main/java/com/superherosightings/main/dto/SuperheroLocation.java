package com.superherosightings.main.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.beans.factory.annotation.Autowired;

import com.superherosightings.main.dao.LocationDao;
import com.superherosightings.main.dao.SuperheroDao;
import com.superherosightings.main.dao.SuperheroDaoImpl;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SuperheroLocation {

	@EmbeddedId
	SuperheroLocationKey id = new SuperheroLocationKey();
	
	@ManyToOne
	@MapsId("superheroId")
	@JoinColumn(name = "superheroId")
	Superhero superhero;
	
	@ManyToOne
	@MapsId("locationId")
	@JoinColumn(name = "locationId")
	Location location;
	
	LocalDate sightingDate;

	@Override
	public String toString() {
		int superheroId = id.getSuperheroId();
		int locationId = id.getLocationId();
		return "Sighting [SuperheroID=" + superheroId + " LocationID=" + locationId +
				" sightingDate=" + sightingDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, location, sightingDate, superhero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuperheroLocation other = (SuperheroLocation) obj;
		return Objects.equals(id, other.id) && Objects.equals(location, other.location)
				&& Objects.equals(sightingDate, other.sightingDate) && Objects.equals(superhero, other.superhero);
	}
	
}
