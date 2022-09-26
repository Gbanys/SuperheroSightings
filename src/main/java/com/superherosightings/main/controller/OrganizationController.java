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
		return viewOrganizations(model);
	}
	
	@GetMapping("/view_organizations")
	public String viewOrganizations(Model model) {
		List<Organization> organizations = organizationDao.getAllOrganizations();
		model.addAttribute("organizations", organizations);
		return "view_organizations";
	}
	
	
	@GetMapping("/edit_organizations/{organizationId}")
	public String viewEditLocation(@PathVariable int organizationId, Model model) {
		model.addAttribute("organizationId", ""+organizationId);
		return "edit_organizations";
	}
	
	@PostMapping("/editOrganization/{organizationId}")
	public String editOrganization(@PathVariable int organizationId, Model model, HttpServletRequest request) {
		
		Organization organization;
		String organizationName = request.getParameter("name");
		String organizationAddress = request.getParameter("address");
		String organizationDesc = request.getParameter("description");
		String organizationEmailAddress = request.getParameter("emailAddress");
		String organizationPhoneNumber = request.getParameter("phoneNumber");
		
		organization = organizationDao.getOrganizationById(organizationId);
		model.addAttribute("organizationById", organization.toString());
		organization.setName(organizationName);
		organization.setAddress(organizationAddress);
		organization.setDescription(organizationDesc);
		organization.setEmailAddress(organizationEmailAddress);
		organization.setPhoneNumber(organizationPhoneNumber);
		organizationDao.updateOrganization(organization);
		model.addAttribute("successMessage", "The location has been successfully updated");
		return viewOrganizations(model);
	}
	
	@GetMapping("/delete_organizations/{organizationId}")
	public String viewDeleteOrganization() {
		return "delete_organizations";
	}
	
	@PostMapping("/deleteOrganization/{organizationId}")
	public String deleteOrganization(@PathVariable int organizationId, Model model, HttpServletRequest request) {
		organizationDao.deleteOrganizationById(organizationId);
		return viewOrganizations(model);
	}
		
}
