package com.superherosightings.main.dao;

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

import com.superherosightings.main.TestApplicationConfiguration;
import com.superherosightings.main.dto.Location;
import com.superherosightings.main.dto.Organization;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.SuperheroLocation;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class LocationDaolmplTest {
	
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
	void tearDown() throws Exception {
	}

	@Test
	void testAddGetLocation() {
		Location location = new Location();
		location.setName("Test location");
		location.setDescription("This is a test location");
		location.setAddress("Test road");
		locationDao.createLocation(location);
		
		Location fromDao = locationDao.getLocationById(location.getLocationId());
		
		assertEquals(location, fromDao);
	}
	
	
	@Test
	void testAddGetLocationBySuperhero() {
		
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
		
		List<Location> locationsFromDao = locationDao.getLocationsBySuperhero(superhero);
		Location locationFromDao = locationsFromDao.get(0);
		
		assertEquals(location, locationFromDao);
		
	}
	
	@Test
	void testGetSightingByDate() {
		
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
		
		List<SuperheroLocation> sightings = locationDao.getAllSightingsByDate(timestamp);
		SuperheroLocation sighting = sightings.get(0);
		List<Superhero> superheroesFromDao = superheroDao.getSuperheroByLocation(location);
		Superhero superheroFromDao = superheroesFromDao.get(0);
		List<Location> locationsFromDao = locationDao.getLocationsBySuperhero(superhero);
		Location locationFromDao = locationsFromDao.get(0);
		
		assertEquals(superheroFromDao, sighting.getSuperhero());
		assertEquals(locationFromDao, sighting.getLocation());
		assertEquals(timestamp, sighting.getSightingDate());
	}

}
