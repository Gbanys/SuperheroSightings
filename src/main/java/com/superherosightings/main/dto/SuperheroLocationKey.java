package com.superherosightings.main.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class SuperheroLocationKey implements Serializable{
	
	private int superheroId;
	private int locationId;
}
