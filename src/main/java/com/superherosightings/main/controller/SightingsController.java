package com.superherosightings.main.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.superherosightings.main.dao.LocationDao;
import com.superherosightings.main.dao.SuperheroDao;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;

@Controller
public class SightingsController {
	
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	SuperheroDao superheroDao;
	
	@GetMapping("/create_sighting")
	public String viewCreateSighting() {
		return "create_sighting";
	}
	
	@PostMapping("/createNewSighting")
	public String createNewSighting(Model model, HttpServletRequest request) {
		
		int superheroId = Integer.parseInt(request.getParameter("superheroId"));
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate timestamp = LocalDate.parse(request.getParameter("date"), formatter);
		
		Superhero superhero = superheroDao.getSuperheroById(superheroId);
		Location location = locationDao.getLocationById(locationId);
		
		superheroDao.recordSighting(superhero, location, timestamp);
		model.addAttribute("successMessage", "The sighting has been successfully recorded");
		return "create_sighting";
	}
	
	@GetMapping("/view_sighting")
	public String viewSighting() {
		return "view_sighting";
	}
	
	@GetMapping("/getAllSightings")
	public String getAllSightings(Model model, HttpServletRequest request) {
		List<SuperheroLocation> sightings = locationDao.getAllSightings();
		model.addAttribute("sightings", sightings);
		return "view_sighting";
	}
	
	@GetMapping("/sightingsBySuperhero")
	public String displaySightingBySuperhero(Model model, HttpServletRequest request) {
		int superheroId = Integer.parseInt(request.getParameter("superheroId"));
		Superhero superhero = superheroDao.getSuperheroById(superheroId);
		List<SuperheroLocation> sightings = locationDao.getSightingsBySuperhero(superhero);
		model.addAttribute("sightingsBySuperhero", sightings.toString());
		return "view_sighting";
	}
	
	@GetMapping("/delete_sighting")
	public String deleteSighting() {
		return "delete_sighting";
	}
	
	@GetMapping("/sightingsByLocation")
	public String displaySightingByLocation(Model model, HttpServletRequest request) {
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		Location location = locationDao.getLocationById(locationId);
		List<SuperheroLocation> sightings = locationDao.getSightingsByLocation(location);
		model.addAttribute("sightingsByLocation", sightings.toString());
		return "view_sighting";
	}
	
	@PostMapping("/deleteSightingByLocation")
	@Transactional
	public String deleteSightingByLocation(Model model, HttpServletRequest request) {
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		Location location = locationDao.getLocationById(locationId);
		locationDao.deleteSightingByLocation(location);
		model.addAttribute("successMessage", "The sightings have been successfully deleted");
		return "delete_sighting";
	}
	
	@PostMapping("/deleteSightingBySuperhero")
	@Transactional
	public String deleteSightingBySuperhero(Model model, HttpServletRequest request) {
		int superheroId = Integer.parseInt(request.getParameter("superheroId"));
		Superhero superhero = superheroDao.getSuperheroById(superheroId);
		locationDao.deleteSightingBySuperhero(superhero);
		model.addAttribute("successMessage", "The sightings have been successfully deleted");
		return "delete_sighting";
	}
	
	@GetMapping("/edit_sighting")
	public String viewEditSighting() {
		return "edit_sighting";
	}
	
	@PostMapping("/editSighting")
	public String editSighting(Model model, HttpServletRequest request) {
		int superheroId = Integer.parseInt(request.getParameter("superheroId"));
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		String date = request.getParameter("date");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate formattedDate = LocalDate.parse(date, formatter);
		
		Superhero superhero = superheroDao.getSuperheroById(superheroId);
		Location location = locationDao.getLocationById(locationId);
		SuperheroLocation sighting = locationDao.getSighting(superhero, location);
		sighting.setSightingDate(formattedDate);
		locationDao.updateSighting(sighting);
		
		model.addAttribute("successMessage", "The sighting has been successfully updated");
		return "edit_sighting";
	}

}