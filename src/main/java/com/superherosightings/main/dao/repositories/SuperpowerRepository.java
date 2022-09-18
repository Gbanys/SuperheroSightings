package com.superherosightings.main.dao.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.superherosightings.main.dto.Superpower;

public interface SuperpowerRepository extends CrudRepository<Superpower, Integer>{

	Superpower findBysuperpowerId(Integer superpower);
	List<Superpower> findAll();
}
