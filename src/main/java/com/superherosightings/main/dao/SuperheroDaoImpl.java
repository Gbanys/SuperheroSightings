package com.superherosightings.main.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superherosightings.main.dao.repositories.LocationRepository;
import com.superherosightings.main.dao.repositories.SuperheroLocationRepository;
import com.superherosightings.main.dao.repositories.SuperheroOrganizationRepository;
import com.superherosightings.main.dao.repositories.SuperheroRepository;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Organization;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;
import com.superherosightings.main.dto.SuperheroLocationKey;
import com.superherosightings.main.dto.SuperheroOrganization;

@Service
public class SuperheroDaoImpl implements SuperheroDao{
	
	@Autowired
	SuperheroRepository superheroRepository;
	
	@Autowired
	SuperheroLocationRepository superheroLocationRepository;
	
	@Autowired
	SuperheroOrganizationRepository superheroOrganizationRepository;
	
	
	@Override
	public void createSuperhero(Superhero superhero) {
		superheroRepository.save(superhero);
	}

	@Override
	public Superhero getSuperheroById(Integer superheroId) {
		Optional<Superhero> superheroes = superheroRepository.findBysuperheroId(superheroId);
		Superhero superhero = superheroes.get();
		return superhero;
	}
	@Override
	public List<Superhero> getAllSuperheroes(){
		return superheroRepository.findAll();
	}
	
	@Override
	public void deleteSuperheroById(Integer superheroId) {
		superheroRepository.deleteById(superheroId);
	}
	
	@Override
	public void deleteAllSuperheroes() {
		superheroRepository.deleteAll();
	}
	
	@Override
	public void updateSuperhero(Superhero superhero) {
		superheroRepository.save(superhero);
	}
	
	@Override
	public List<Superhero> getSuperheroByLocation(Location location) {
		
		List<SuperheroLocation> superheroLocations = superheroLocationRepository.findByLocation(location);
		List<Superhero> superheroes = new ArrayList<Superhero>();
		
		for(SuperheroLocation tempSuperheroLocation : superheroLocations) {
			superheroes.add(tempSuperheroLocation.getSuperhero());
		}
		return superheroes;
	}
	

	@Override
	public void recordSighting(Superhero superhero, Location location, LocalDate date) {
		
		SuperheroLocation sighting = new SuperheroLocation();
		sighting.setSuperhero(superhero);
		sighting.setLocation(location);
		sighting.setSightingDate(date);
		superheroLocationRepository.save(sighting);
		
	}
	
	@Override
	public void assignSuperheroToOrganization(Superhero superhero, Organization organization) {
		
		SuperheroOrganization superheroOrganization = new SuperheroOrganization();
		superheroOrganization.setSuperhero(superhero);
		superheroOrganization.setOrganization(organization);
		superheroOrganizationRepository.save(superheroOrganization);
	}
	
	@Override
	public List<Superhero> getSuperheroesByOrganization(Organization organization){
		
		List<SuperheroOrganization> superheroOrganizations = superheroOrganizationRepository.findByOrganization(organization);
		List<Superhero> superheroes = new ArrayList<Superhero>();
		
		for(SuperheroOrganization tempSuperheroOrganization : superheroOrganizations) {
			superheroes.add(tempSuperheroOrganization.getSuperhero());
		}
		return superheroes;
	}
	
	
}
