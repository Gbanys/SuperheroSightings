package com.superherosightings.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		return "create_location";
	}
	
	@GetMapping("/view_locations")
	public String viewLocations() {
		return "view_locations";
	}
	
	@GetMapping("/viewAllLocations")
	public String displayAllLocations(Model model) {
		List<Location> locations = locationDao.getAllLocations();
		model.addAttribute("locations", locations);
		return "view_locations";
	}
	
	@GetMapping("/viewLocationById")
	public String displayLocationById(Model model, HttpServletRequest request) {
		int locationId;
		
		try {
		locationId = Integer.parseInt(request.getParameter("locationId"));
		}
		catch(NumberFormatException e) {
			model.addAttribute("NumberFormatError", "Please enter a number into the ID field, no letters or symbols are allowed!");
			return "view_locations";
		}
		
		try {
		Location location = locationDao.getLocationById(locationId);
		model.addAttribute("locationById", location.toString());
		return "view_locations";
		}
		catch(NoSuchElementException e) {
			model.addAttribute("NotFoundError", "Sorry but no location exists with this ID number."
					+ "Please click on view all locations to see all ID numbers and their corresponding locations");
		return "view_locations";
		}
	}
	
	@GetMapping("/edit_locations")
	public String viewEditLocation() {
		return "edit_locations";
	}
	
	@PostMapping("/editLocation")
	public String editLocation(Model model, HttpServletRequest request) {
		
		int locationId;
		Location location;
		
		try {
			locationId = Integer.parseInt(request.getParameter("locationId"));
		}
		catch(NumberFormatException e) {
			model.addAttribute("NumberFormatError", "Please enter a number into the ID field, no letters or symbols are allowed!");
			return "edit_locations";
		}
		String locationName = request.getParameter("name");
		String locationAddress = request.getParameter("address");
		String locationDesc = request.getParameter("description");
		
		try {
			location = locationDao.getLocationById(locationId);
			location.setName(locationName);
			location.setAddress(locationAddress);
			location.setDescription(locationDesc);
			model.addAttribute("locationById", location.toString());
			model.addAttribute("successMessage", "The location has been successfully updated");
			return "edit_locations";
		}
		catch(NoSuchElementException e) {
			model.addAttribute("NotFoundError", "Sorry but no location exists with this ID number."
						+ "Please click on view all locations to see all ID numbers and their corresponding locations");
			return "edit_locations";
			}
		
	}
	
	@GetMapping("/delete_locations")
	public String viewDeleteLocation() {
		return "delete_locations";
	}
	
	@PostMapping("/deleteLocation")
	public String deleteLocation(Model model, HttpServletRequest request) {
		
		int locationId;
		
		try {
			locationId = Integer.parseInt(request.getParameter("locationId"));
		}
		catch(NumberFormatException e) {
			model.addAttribute("NumberFormatError", "Please enter a number into the ID field, no letters or symbols are allowed!");
			return "delete_locations";
		}
		
		try {
		locationDao.deleteLocationById(locationId);
		}
		catch(EmptyResultDataAccessException e) {
			model.addAttribute("NotFoundError", "Sorry but a location with this ID does not exist");
			return "delete_locations";
		}
		model.addAttribute("successMessage", "The location has been successfully deleted");
		return "delete_locations";
	}
}
