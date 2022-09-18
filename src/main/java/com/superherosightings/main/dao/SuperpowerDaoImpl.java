package com.superherosightings.main.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superherosightings.main.dao.repositories.SuperpowerRepository;
import com.superherosightings.main.dto.Superpower;

@Service
public class SuperpowerDaoImpl implements SuperpowerDao{
	
	@Autowired
	SuperpowerRepository superpowerRepository;

	@Override
	public void createSuperpower(Superpower superpower) {
		superpowerRepository.save(superpower);
	}
	
	@Override
	public Superpower getSuperpowerById(Integer superpowerId) {
		return superpowerRepository.findBysuperpowerId(superpowerId);
	}
	
	@Override
	public List<Superpower> getAllSuperpowers(){
		return superpowerRepository.findAll();
	}
	
	@Override
	public void deleteSuperpowerById(Integer superpowerId) {
		superpowerRepository.deleteById(superpowerId);
	}
	
	@Override
	public void deleteAllSuperpowers(){
		superpowerRepository.deleteAll();
	}
	
	@Override
	public void updateSuperpower(Superpower superpower) {
		superpowerRepository.save(superpower);
	}
}
