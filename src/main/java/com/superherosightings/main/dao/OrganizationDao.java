package com.superherosightings.main.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.superherosightings.main.dto.Organization;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroOrganization;

@Service
public interface OrganizationDao {

	void createOrganization(Organization organization);
	
	Organization getOrganizationById(Integer organizationId);
	
	List<Organization> getAllOrganizations();
	
	void deleteOrganizationById(Integer organizationId);
	
	void deleteAllOrganizations();
	
	void updateOrganization(Organization organization);
	
	List<SuperheroOrganization> findAllSuperheroOrganizations();
	
	void deleteAllSuperheroOrganizations();
	
	List<Organization> getOrganizationsBySuperhero(Superhero superhero);
}
