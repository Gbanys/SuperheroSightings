package com.superherosightings.main.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;
import com.superherosightings.main.dto.SuperheroLocationKey;

@Service
public interface LocationDao {
	
	void createLocation(Location location);
	
	Location getLocationById(Integer locationId);
	
	List<Location> getAllLocations();
	
	void deleteLocationById(Integer locationId);
	
	void deleteAllLocations();
	
	void deleteSightingByLocation(Location location);
	
	void deleteSightingBySuperhero(Superhero superhero);
	
	void updateLocation(Location location);
	
	List<SuperheroLocation> getAllSightings();
	
	List<SuperheroLocation> getAllSightingsByDate(LocalDate date);
	
	List<SuperheroLocation> getSightingsBySuperhero(Superhero superhero);
	
	List<SuperheroLocation> getSightingsByLocation(Location location);
	
	SuperheroLocation getSightingById(int id);
	
	void deleteAll();
	
	void deleteSightingById(SuperheroLocationKey id);
	
	List<Location> getLocationsBySuperhero(Superhero superhero);

	SuperheroLocation getSighting(Superhero superhero, Location location);
	
	void updateSighting(SuperheroLocation sighting);
	
	
}
