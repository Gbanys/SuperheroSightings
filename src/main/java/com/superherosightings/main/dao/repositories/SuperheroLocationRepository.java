package com.superherosightings.main.dao.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;
import com.superherosightings.main.dto.SuperheroLocationKey;

public interface SuperheroLocationRepository extends CrudRepository<SuperheroLocation, Integer>{

	
	List<SuperheroLocation> findAllByOrderBySightingDateDesc();
	
	void deleteAll();
	
	void deleteBySuperhero(Superhero superhero);
	void deleteByLocation(Location location);
	void deleteById(SuperheroLocationKey id);
	
	List<SuperheroLocation> findBySuperhero(Superhero superhero);
	
	List<SuperheroLocation> findByLocation(Location location);
	
	List<SuperheroLocation> findBySightingDate(LocalDate sightingDate);
	
	SuperheroLocation findBySuperheroAndLocation(Superhero superhero, Location location);
	
}
