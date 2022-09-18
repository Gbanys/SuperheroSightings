package com.superherosightings.main.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Organization;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;

@Service
public interface SuperheroDao {
	
	void createSuperhero(Superhero superhero);
	
	Superhero getSuperheroById(Integer superheroId);
	
	List<Superhero> getAllSuperheroes();
	
	void deleteSuperheroById(Integer superheroId);
	
	void deleteAllSuperheroes();
	
	void updateSuperhero(Superhero superhero);
	
	List<Superhero> getSuperheroByLocation(Location location);
	
	void recordSighting(Superhero superhero, Location location, LocalDate date);
	
	void assignSuperheroToOrganization(Superhero superhero, Organization organization);
	
	List<Superhero> getSuperheroesByOrganization(Organization organization);
	
}
