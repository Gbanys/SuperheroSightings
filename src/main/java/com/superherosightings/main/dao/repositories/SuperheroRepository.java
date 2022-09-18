package com.superherosightings.main.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.superherosightings.main.dto.Superhero;

public interface SuperheroRepository extends CrudRepository<Superhero,Integer> {

	Optional<Superhero> findBysuperheroId(Integer superheroId);
	List<Superhero> findAll();
	void deleteAll();
}
