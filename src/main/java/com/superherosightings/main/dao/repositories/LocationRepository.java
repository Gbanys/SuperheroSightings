package com.superherosightings.main.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.superherosightings.main.dto.Location;

public interface LocationRepository extends CrudRepository<Location, Integer>{

	Optional<Location> findBylocationId(Integer locationId);
	List<Location> findAll();
	void deleteAll();
}
