package com.superherosightings.main.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.superherosightings.main.dto.Superpower;

@Service
public interface SuperpowerDao {

	void createSuperpower(Superpower superpower);
	
	Superpower getSuperpowerById(Integer superpower);
	
	List<Superpower> getAllSuperpowers();

	void deleteSuperpowerById(Integer superpowerId);
	
	void deleteAllSuperpowers();
	
	void updateSuperpower(Superpower superpower);
	
}
