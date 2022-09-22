package com.superherosightings.main.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.superherosightings.main.dao.LocationDao;
import com.superherosightings.main.dao.SuperheroDao;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;
import com.superherosightings.main.dto.SuperheroLocationKey;

@Controller
public class SuperheroSightingsController {
	
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	SuperheroDao superheroDao;
	

	@GetMapping("/home")
	public String displayHomePage(Model model) {
		
		List<SuperheroLocation> sightings = locationDao.getAllSightings();
		
		model.addAttribute("sightings", sightings);
		return "home";
	}
	
	@GetMapping("/details")
	public String displayDetails(Model model, HttpServletRequest request) {
		
		List<SuperheroLocation> sightings = locationDao.getAllSightings();
		List<Integer> counters = new ArrayList<Integer>();
		
		SuperheroLocation existingSighting = null;
		
		for(SuperheroLocation sighting : sightings) {
			if(request.getParameter("" + sighting.getId()) != null) {
				existingSighting = sighting;
				break;
			}
		}
		for(int i = 0; i < 10; i++) {
			counters.add(i);
		}
		Location location = existingSighting.getLocation();
		Superhero superhero = existingSighting.getSuperhero();
		
		model.addAttribute("sighting", existingSighting);
		model.addAttribute("location", location);
		model.addAttribute("superhero", superhero);
		model.addAttribute("counters", counters);
		
		return "details";
	}
	
}
