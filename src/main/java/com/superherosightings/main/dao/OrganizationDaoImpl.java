package com.superherosightings.main.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superherosightings.main.dao.repositories.OrganizationRepository;
import com.superherosightings.main.dao.repositories.SuperheroOrganizationRepository;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Organization;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroOrganization;

@Service
public class OrganizationDaoImpl implements OrganizationDao{
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	SuperheroOrganizationRepository superheroOrganizationRepository;
	
	@Override
	public void createOrganization(Organization organization) {
		organizationRepository.save(organization);
	}
	@Override
	public Organization getOrganizationById(Integer organizationId) {
		Optional<Organization> organizations = organizationRepository.findByorganizationId(organizationId);
		Organization organization = organizations.get();
		return organization;
	}
	
	@Override
	public List<Organization> getAllOrganizations(){
		return organizationRepository.findAll();
	}
	
	@Override
	public void deleteOrganizationById(Integer organizationId) {
		organizationRepository.deleteById(organizationId);
	}
	
	@Override
	public void deleteAllOrganizations() {
		organizationRepository.deleteAll();
	}
	
	@Override
	public void updateOrganization(Organization organization) {
		organizationRepository.save(organization);
	}
	
	@Override
	public List<SuperheroOrganization> findAllSuperheroOrganizations(){
		return superheroOrganizationRepository.findAll();
		
	}
	
	@Override
	public void deleteAllSuperheroOrganizations() {
		superheroOrganizationRepository.deleteAll();
		
	}
	
	@Override
	public List<Organization> getOrganizationsBySuperhero(Superhero superhero){
		
		List<SuperheroOrganization> superheroOrganizations = superheroOrganizationRepository.findBySuperhero(superhero);
		List<Organization> organizations = new ArrayList<Organization>();
		
		for(SuperheroOrganization tempSuperheroOrganization : superheroOrganizations) {
			organizations.add(tempSuperheroOrganization.getOrganization());
		}
		return organizations;
	}
}
