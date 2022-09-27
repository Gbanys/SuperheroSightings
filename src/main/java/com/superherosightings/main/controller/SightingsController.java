package com.superherosightings.main.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.superherosightings.main.dao.LocationDao;
import com.superherosightings.main.dao.SuperheroDao;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;
import com.superherosightings.main.dto.SuperheroLocationKey;

@Controller
public class SightingsController {
	
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	SuperheroDao superheroDao;
	
	
	@GetMapping("/create_sighting")
	public String viewCreateSighting(Model model) {
		List<Superhero> superheroes = superheroDao.getAllSuperheroes();
		List<Location> locations = locationDao.getAllLocations();
		model.addAttribute("superheroes", superheroes);
		model.addAttribute("locations", locations);
		return "create_sighting";
	}
	
	@PostMapping("/createNewSighting")
	public String createNewSighting(Model model, HttpServletRequest request) {
		
		String superheroIdAsString = request.getParameter("superheroID");
		String locationIdAsString = request.getParameter("locationID");
		
		if(superheroIdAsString.isEmpty() || superheroIdAsString.isBlank()
				|| locationIdAsString.isEmpty() || locationIdAsString.isBlank()) {
			model.addAttribute("EmptyStringError", "Sorry but you haven't selected a superhero or a location, please add them");
			return viewCreateSighting(model);
		}
		int superheroId = Integer.parseInt(superheroIdAsString);
		int locationId = Integer.parseInt(locationIdAsString);
		
		Superhero superhero = superheroDao.getSuperheroById(superheroId);
		Location location = locationDao.getLocationById(locationId);
		LocalDate timestamp;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			timestamp = LocalDate.parse(request.getParameter("date"), formatter);
		}
		catch(DateTimeParseException e) {
			model.addAttribute("DateTimeFormatError", "Sorry, but your date is in the incorrect format, please try again");
			return viewCreateSighting(model);
		}
		
		superheroDao.recordSighting(superhero, location, timestamp);
		model.addAttribute("successMessage", "The sighting has been successfully recorded");
		return viewSighting(model);
	}
	
	
	@GetMapping("/view_sighting")
	public String viewSighting(Model model) {
		List<SuperheroLocation> sightings = locationDao.getAllSightings();
		model.addAttribute("sightings", sightings);
		return "view_sighting";
	}
	
	
	@GetMapping("/edit_sighting")
	public String viewEditSighting(Model model, HttpServletRequest request) {
		
		List<SuperheroLocation> sightings = locationDao.getAllSightings();
		
		SuperheroLocation existingSighting = null;
		
		for(SuperheroLocation sighting : sightings) {
			if(request.getParameter("" + sighting.getId()) != null) {
				existingSighting = sighting;
				break;
			}
		}
		List<Superhero> superheroes = superheroDao.getAllSuperheroes();
		List<Location> locations = locationDao.getAllLocations();
		model.addAttribute("superheroes", superheroes);
		model.addAttribute("locations", locations);
		model.addAttribute("sighting", existingSighting);
		return "edit_sighting";
	}
	
	@PostMapping("/editSighting")
	public String editSighting(Model model, HttpServletRequest request) {
		
		String superheroIdAsString = request.getParameter("superheroID");
		String locationIdAsString = request.getParameter("locationID");
		
		if(superheroIdAsString.isEmpty() || superheroIdAsString.isBlank()
				|| locationIdAsString.isEmpty() || locationIdAsString.isBlank()) {
			model.addAttribute("EmptyStringError", "Sorry but you haven't selected a superhero or a location, please add them");
			return viewEditSighting(model,request);
		}
		int superheroId = Integer.parseInt(superheroIdAsString);
		int locationId = Integer.parseInt(locationIdAsString);
	
		Superhero superhero = superheroDao.getSuperheroById(superheroId);
		Location location = locationDao.getLocationById(locationId);
		LocalDate formattedDate;
		
		String date = request.getParameter("date");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			formattedDate = LocalDate.parse(date, formatter);
		}
		catch(DateTimeParseException e) {
			model.addAttribute("DateTimeFormatError", "Sorry, but your date is in the incorrect format, please try again");
			return "edit_sighting";
		}
		
		SuperheroLocation sighting = locationDao.getSighting(superhero, location);
		sighting.setSightingDate(formattedDate);
		locationDao.updateSighting(sighting);
		
		model.addAttribute("successMessage", "The sighting has been successfully updated");
		return viewSighting(model);
	}
	
	@GetMapping("/delete_sighting")
	@Transactional
	public String deleteSighting(Model model, HttpServletRequest request) {
		
		List<SuperheroLocation> sightings = locationDao.getAllSightings();
		SuperheroLocation existingSighting = null;
		
		for(SuperheroLocation sighting : sightings) {
			if(request.getParameter("" + sighting.getId()) != null) {
				existingSighting = sighting;
				break;
			}
		}
		locationDao.deleteSightingById(existingSighting.getId());
		model.addAttribute("successMessage", "The sighting has been successfully deleted");
		return viewSighting(model);
	}

}
