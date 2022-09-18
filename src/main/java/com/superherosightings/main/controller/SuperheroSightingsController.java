package com.superherosightings.main.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.superherosightings.main.dao.LocationDao;
import com.superherosightings.main.dao.SuperheroDao;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;

@Controller
public class SuperheroSightingsController {
	
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	SuperheroDao superheroDao;
	

	@GetMapping("/home")
	public String displayHomePage(Model model) {
		
		List<SuperheroLocation> sightings = locationDao.getAllSightings();
		List<LocalDate> sightingDates = new ArrayList<LocalDate>();
		List<String> heroNames = new ArrayList<String>();
		List<String> locationNames = new ArrayList<String>();
		
		for(SuperheroLocation sighting : sightings) {
			sightingDates.add(sighting.getSightingDate());
			List<Superhero> superheroes = superheroDao.getSuperheroByLocation(sighting.getLocation());
			Superhero superhero = superheroes.get(0);
			List<Location> locations = locationDao.getLocationsBySuperhero(sighting.getSuperhero());
			Location location = locations.get(0);
			
			heroNames.add(superhero.getName());
			locationNames.add(location.getName());
		}
		
		model.addAttribute("sightings", sightingDates.toString());
		return "home";
	}
	
}
