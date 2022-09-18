package com.superherosightings.main.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SuperheroOrganizationKey implements Serializable {

	private int superheroId;
	private int organizationId;
	
}
