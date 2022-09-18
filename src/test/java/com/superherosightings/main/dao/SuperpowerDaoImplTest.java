package com.superherosightings.main.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.superherosightings.main.TestApplicationConfiguration;
import com.superherosightings.main.dto.Superpower;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class SuperpowerDaoImplTest {
	
	@Autowired
	SuperpowerDao superpowerDao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		superpowerDao.deleteAllSuperpowers();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddGetSuperpower() {
		Superpower superpower = new Superpower();
		superpower.setName("Test");
		superpowerDao.createSuperpower(superpower);

		Superpower superpowerFromDao = superpowerDao.getSuperpowerById(superpower.getSuperpowerId());
		assertEquals(superpower, superpowerFromDao);
	}
	
	@Test
	void testGetAllSuperpowers() {
		Superpower superpower = new Superpower();
		superpower.setName("Test");
		superpowerDao.createSuperpower(superpower);
		Superpower superpowerTwo = new Superpower();
		superpowerTwo.setName("Test 2");
		superpowerDao.createSuperpower(superpowerTwo);
		
		List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
		
		assertEquals(superpower, superpowers.get(1));
		assertEquals(superpowerTwo, superpowers.get(0));
	}
	
	@Test
	void testUpdate() {
		Superpower superpower = new Superpower();
		superpower.setName("Test");
		superpowerDao.createSuperpower(superpower);
		
		superpower.setName("Test 2");
		superpowerDao.updateSuperpower(superpower);
		Superpower updatedSuperpower = superpowerDao.getSuperpowerById(superpower.getSuperpowerId());
		
		assertEquals(superpower, updatedSuperpower);
	}
	
	@Test
	void testDelete() {
		Superpower superpower = new Superpower();
		superpower.setName("Test");
		superpowerDao.createSuperpower(superpower);
		
		superpowerDao.deleteSuperpowerById(superpower.getSuperpowerId());
		Superpower retrievedSuperpower = superpowerDao.getSuperpowerById(superpower.getSuperpowerId());
		
		assertNull(retrievedSuperpower);
	}
	
	

}
