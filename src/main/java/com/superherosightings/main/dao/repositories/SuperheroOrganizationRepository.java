package com.superherosightings.main.dao.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.superherosightings.main.dto.Organization;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroOrganization;

public interface SuperheroOrganizationRepository extends CrudRepository<SuperheroOrganization, Integer>{

	List<SuperheroOrganization> findAll();
	void deleteAll();
	
	List<SuperheroOrganization> findByOrganization(Organization organization);
	
	List<SuperheroOrganization> findBySuperhero(Superhero superhero);
	
}
