package com.superherosightings.main.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SuperheroOrganization {

	@EmbeddedId
	SuperheroOrganizationKey id = new SuperheroOrganizationKey();
	
	@ManyToOne
	@MapsId("superheroId")
	@JoinColumn(name = "superheroId")
	Superhero superhero;
	
	@ManyToOne
	@MapsId("organizationId")
	@JoinColumn(name = "organizationId")
	Organization organization;
}
