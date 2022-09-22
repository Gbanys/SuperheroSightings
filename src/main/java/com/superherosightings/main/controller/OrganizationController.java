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
import java.lang.NumberFormatException;

import com.superherosightings.main.dao.OrganizationDao;
import com.superherosightings.main.dto.Organization;

@Controller
public class OrganizationController {
	
	@Autowired
	OrganizationDao organizationDao;

	@GetMapping("/create_organization")
	public String viewCreateOrganization() {
		return "create_organization";
	}
	
	@PostMapping("/createOrganization")
	public String createNewOrganization(Model model, HttpServletRequest request) {
		String organizationName = request.getParameter("organizationName");
		String organizationAddress = request.getParameter("organizationAddress");
		String organizationDescription = request.getParameter("organizationDescription");
		String organizationEmailAddress = request.getParameter("organizationEmailAddress");
		String organizationPhoneNumber = request.getParameter("organizationPhoneNumber");
		Organization organization = new Organization();
		organization.setName(organizationName);
		organization.setAddress(organizationAddress);
		organization.setDescription(organizationDescription);
		organization.setEmailAddress(organizationEmailAddress);
		organization.setPhoneNumber(organizationPhoneNumber);
		organizationDao.createOrganization(organization);
		model.addAttribute("successMessage", "The organization has been successfully created");
		return "create_organization";
	}
	
	@GetMapping("/view_organizations")
	public String viewOrganizations() {
		return "view_organizations";
	}
	
	@GetMapping("/viewAllOrganizations")
	public String displayOrganizations(Model model) {
		
		List<Organization> organizations = organizationDao.getAllOrganizations();
		model.addAttribute("organizations", organizations);
		return "view_organizations";
	}
	
	@GetMapping("/viewOrganizationById")
	public String displayOrganizationById(Model model, HttpServletRequest request){
		
		int organizationId;
		
		try {
			organizationId = Integer.parseInt(request.getParameter("organizationId"));
		}
		catch(NumberFormatException e) {
			model.addAttribute("NumberFormatError", "Please enter a number into the ID field, no letters or symbols are allowed!");
			return "view_organizations";
		}
		
		try {
		Organization organization = organizationDao.getOrganizationById(organizationId);
		model.addAttribute("organizationById", organization.toString());
		return "view_organizations";
		}
		catch(NoSuchElementException e) {
			model.addAttribute("NotFoundError", "Sorry but no organization exists with this ID number."
					+ "Please click on view all organizations to see all ID numbers and their corresponding organizations");
			return "view_organizations";
		}
	}
	
	@GetMapping("/edit_organizations")
	public String viewEditLocation() {
		return "edit_organizations";
	}
	
	@PostMapping("/editOrganization")
	public String editOrganization(Model model, HttpServletRequest request) {
		
		
		int organizationId;
		Organization organization;
		
		try {
			organizationId = Integer.parseInt(request.getParameter("organizationId"));
		}
		catch(NumberFormatException e) {
			model.addAttribute("NumberFormatError", "Please enter a number into the ID field, no letters or symbols are allowed!");
			return "edit_organizations";
		}
		String organizationName = request.getParameter("name");
		String organizationAddress = request.getParameter("address");
		String organizationDesc = request.getParameter("description");
		String organizationEmailAddress = request.getParameter("emailAddress");
		String organizationPhoneNumber = request.getParameter("phoneNumber");
		
		try {
			organization = organizationDao.getOrganizationById(organizationId);
			model.addAttribute("organizationById", organization.toString());
			organization.setName(organizationName);
			organization.setAddress(organizationAddress);
			organization.setDescription(organizationDesc);
			organization.setEmailAddress(organizationEmailAddress);
			organization.setPhoneNumber(organizationPhoneNumber);
			organizationDao.updateOrganization(organization);
			model.addAttribute("successMessage", "The location has been successfully updated");
			
			return "edit_organizations";
			}
			catch(NoSuchElementException e) {
				model.addAttribute("NotFoundError", "Sorry but no organization exists with this ID number."
						+ "Please click on view all organizations to see all ID numbers and their corresponding organizations");
			return "edit_organizations";
		}
	}
	
	@GetMapping("/delete_organizations")
	public String viewDeleteOrganization() {
		return "delete_organizations";
	}
	
	@GetMapping("/deleteOrganization")
	public String deleteOrganization(Model model, HttpServletRequest request) {
		
		int organizationId;
		
		try {
		organizationId = Integer.parseInt(request.getParameter("organizationId"));
		}
		catch(NumberFormatException e) {
			model.addAttribute("NumberFormatError", "Please enter a number into the ID field, no letters or symbols are allowed!");
			return "delete_organizations";
		}
		
		try {
		organizationDao.deleteOrganizationById(organizationId);
		return "delete_organizations";
		}
		catch(NoSuchElementException e) {
			model.addAttribute("NotFoundError", "Sorry but no organization exists with this ID number."
					+ "Please click on view all organizations to see all ID numbers and their corresponding organizations");
		return "delete_organizations";
		}
		catch(EmptyResultDataAccessException e) {
			model.addAttribute("NotFoundError", "Sorry but an organization with this ID does not exist");
			return "delete_organizations";
		}
	}
}
