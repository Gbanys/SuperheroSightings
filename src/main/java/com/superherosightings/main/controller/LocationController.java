package com.superherosightings.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.superherosightings.main.dao.LocationDao;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.Superpower;

@Controller
public class LocationController {
	
	@Autowired
	LocationDao locationDao;

	@GetMapping("/create_location")
	public String viewCreateLocation() {
		return "create_location";
	}
	
	@PostMapping("/createNewLocation")
	public String createNewLocation(Model model, HttpServletRequest request) {
		String locationName = request.getParameter("LocationName");
		String locationAddress = request.getParameter("LocationAddress");
		String locationDescription = request.getParameter("LocationDescription");
		Location location = new Location();
		location.setName(locationName);
		location.setAddress(locationAddress);
		location.setDescription(locationDescription);
		locationDao.createLocation(location);
		model.addAttribute("successMessage", "The location has been successfully created");
		return viewLocations(model);
	}
	
	@GetMapping("/view_locations")
	public String viewLocations(Model model) {
		List<Location> locations = locationDao.getAllLocations();
		model.addAttribute("locations",locations);
		return "view_locations";
	}
	
	
	@GetMapping("/edit_locations/{locationId}")
	public String viewEditLocation(@PathVariable int locationId, Model model) {
		model.addAttribute("locationId", locationId);
		return "edit_locations";
	}
	
	@PostMapping("/editLocation/{locationId}")
	public String editLocation(@PathVariable int locationId, Model model, HttpServletRequest request) {
		
		Location location;

		String locationName = request.getParameter("name");
		String locationAddress = request.getParameter("address");
		String locationDesc = request.getParameter("description");
		
		location = locationDao.getLocationById(locationId);
		location.setName(locationName);
		location.setAddress(locationAddress);
		location.setDescription(locationDesc);
		model.addAttribute("locationById", location.toString());
		model.addAttribute("successMessage", "The location has been successfully updated");
		return viewLocations(model);
		
	}
	
	@PostMapping("/deleteLocation/{locationId}")
	public String deleteLocation(@PathVariable int locationId, Model model, HttpServletRequest request) {
		locationDao.deleteLocationById(locationId);
		model.addAttribute("successMessage", "The location has been successfully deleted");
		return viewLocations(model);
	}
}
