package com.superherosightings.main.dto;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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
	
	
}
