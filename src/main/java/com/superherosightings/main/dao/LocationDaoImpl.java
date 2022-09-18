package com.superherosightings.main.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superherosightings.main.dao.repositories.LocationRepository;
import com.superherosightings.main.dao.repositories.OrganizationRepository;
import com.superherosightings.main.dao.repositories.SuperheroLocationRepository;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Organization;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;

@Service
public class LocationDaoImpl implements LocationDao{

	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	SuperheroLocationRepository superheroLocationRepository;

	@Override
	public void createLocation(Location location) {
		locationRepository.save(location);
	}
	
	@Override
	public Location getLocationById(Integer locationId) {
		
		Optional<Location> locations = locationRepository.findBylocationId(locationId);
		Location location = locations.get();
		return location;
	}
	@Override
	public List<Location> getAllLocations(){
		return locationRepository.findAll();
	}
	
	@Override
	public void deleteLocationById(Integer locationId) {
		locationRepository.deleteById(locationId);
	}
	
	@Override
	public void deleteAllLocations() {
		locationRepository.deleteAll();
	}
	
	@Override
	public void deleteSightingByLocation(Location location) {
		superheroLocationRepository.deleteByLocation(location);
	}
	
	@Override
	public void deleteSightingBySuperhero(Superhero superhero) {
		superheroLocationRepository.deleteBySuperhero(superhero);
	}
	
	@Override
	public void updateLocation(Location location) {
		locationRepository.save(location);
	}
	
	@Override
	public SuperheroLocation getSighting(Superhero superhero, Location location) {
		return superheroLocationRepository.findBySuperheroAndLocation(superhero, location);
	}
	
	@Override
	public void updateSighting(SuperheroLocation sighting) {
		superheroLocationRepository.save(sighting);
	}
	
	@Override
	public List<SuperheroLocation> getAllSightings(){
		return superheroLocationRepository.findAllByOrderBySightingDateAsc();
	}
	
	@Override
	public List<SuperheroLocation> getSightingsBySuperhero(Superhero superhero) {
		return superheroLocationRepository.findBySuperhero(superhero);
	}
	
	@Override
	public List<SuperheroLocation> getSightingsByLocation(Location location) {
		return superheroLocationRepository.findByLocation(location);
	}
	
	@Override
	public void deleteAll() {
		superheroLocationRepository.deleteAll();
	}
	
	@Override
	public List<Location> getLocationsBySuperhero(Superhero superhero){
		
		List<SuperheroLocation> superheroLocations = superheroLocationRepository.findBySuperhero(superhero);
		List<Location> locations = new ArrayList<Location>();
		
		for(SuperheroLocation tempSuperheroLocation : superheroLocations) {
			locations.add(tempSuperheroLocation.getLocation());
		}
		return locations;
	}
	
	@Override 
	public List<SuperheroLocation> getAllSightingsByDate(LocalDate date) {
		
		List<SuperheroLocation> sightings = superheroLocationRepository.findBySightingDate(date);
		return sightings;
	}
}
