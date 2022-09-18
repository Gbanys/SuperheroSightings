package com.superherosightings.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String displayOrganizationById(Model model, HttpServletRequest request) {
		int organizationId = Integer.parseInt(request.getParameter("organizationId"));
		Organization organization = organizationDao.getOrganizationById(organizationId);
		model.addAttribute("organizationById", organization.toString());
		return "view_organizations";
	}
	
	@GetMapping("/edit_organizations")
	public String viewEditLocation() {
		return "edit_organizations";
	}
	
	@PostMapping("/editOrganization")
	public String editOrganization(Model model, HttpServletRequest request) {
		int organizationId = Integer.parseInt(request.getParameter("organizationId"));
		String organizationName = request.getParameter("name");
		String organizationAddress = request.getParameter("address");
		String organizationDesc = request.getParameter("description");
		String organizationEmailAddress = request.getParameter("emailAddress");
		String organizationPhoneNumber = request.getParameter("phoneNumber");
		
		Organization organization= organizationDao.getOrganizationById(organizationId);
		organization.setName(organizationName);
		organization.setAddress(organizationAddress);
		organization.setDescription(organizationDesc);
		organization.setEmailAddress(organizationEmailAddress);
		organization.setPhoneNumber(organizationPhoneNumber);
		organizationDao.updateOrganization(organization);
		model.addAttribute("successMessage", "The location has been successfully updated");
		return "edit_organizations";
	}
}
