package com.superherosightings.main.dao;
import com.superherosightings.main.*;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Organization;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class SuperheroDaoImplTest {
	
	@Autowired
	SuperheroDao superheroDao;
	
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	OrganizationDao organizationDao;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		superheroDao.deleteAllSuperheroes();
		locationDao.deleteAllLocations();
		organizationDao.deleteAllOrganizations();
		
	}

	@AfterEach
	void tearDown() throws Exception{
		
	}
	
	@Test
	void testAddGetSuperhero() throws Exception {
		
		Superhero superhero = new Superhero();
		superhero.setName("Test name");
		superhero.setDescription("Test description");
		superheroDao.createSuperhero(superhero);
		
		Superhero fromDao = superheroDao.getSuperheroById(superhero.getSuperheroId());
		
		assertEquals(superhero, fromDao);
	}
	
	@Test
	void testAddGetSuperheroByLocation() throws Exception{
		
		Location location = new Location();
		location.setName("Test location");
		location.setDescription("This is a test location");
		location.setAddress("Test road");
		locationDao.createLocation(location);
		
		Superhero superhero = new Superhero();
		superhero.setName("Test name");
		superhero.setDescription("Test description");
		superheroDao.createSuperhero(superhero);
		
		LocalDate timestamp = LocalDate.now();
		superheroDao.recordSighting(superhero, location, timestamp);
		
		List<Superhero> superheroesFromDao = superheroDao.getSuperheroByLocation(location);
		Superhero superheroFromDao = superheroesFromDao.get(0);
		
		assertEquals(superhero, superheroFromDao);
		
	}
	
	@Test
	void testGetAddSuperheroByOrganization() {
		
		Organization organization = new Organization();
		organization.setName("Test organization");
		organization.setDescription("This is a test organization");
		organization.setAddress("Test road");
		organization.setEmailAddress("75yu68@yahoo.com");
		organization.setPhoneNumber("0995867584");
		organizationDao.createOrganization(organization);
		
		Superhero superhero = new Superhero();
		superhero.setName("Test name");
		superhero.setDescription("Test description");
		superheroDao.createSuperhero(superhero);
		
		superheroDao.assignSuperheroToOrganization(superhero, organization);
		
		List<Superhero> superheroes = superheroDao.getSuperheroesByOrganization(organization);
		Superhero superheroFromDao = superheroes.get(0);
		
		assertEquals(superhero, superheroFromDao);
		
	}
	
}
