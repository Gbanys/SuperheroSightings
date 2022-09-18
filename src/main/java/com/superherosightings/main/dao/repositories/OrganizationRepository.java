package com.superherosightings.main.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.superherosightings.main.dto.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Integer>{

	Optional<Organization> findByorganizationId(Integer organizationId);
	List<Organization> findAll();
	void deleteAll();
}
