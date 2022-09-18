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
import com.superherosightings.main.dto.Organization;
import com.superherosightings.main.dto.Superhero;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class OrganizationDaolmplTest {
	
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
	void testAddGetOrganization() {
		Organization organization = new Organization();
		organization.setName("Test organisation");
		organization.setDescription("This is a test organization");
		organization.setAddress("Test road");
		organization.setEmailAddress("test@yahoo.com");
		organization.setPhoneNumber("048573859");
		organizationDao.createOrganization(organization);
		
		Organization fromDao = organizationDao.getOrganizationById(organization.getOrganizationId());
		
		assertEquals(organization, fromDao);
	}
	
	@Test
	void testGetAddOrganizationBySuperhero() {
		
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
		
		List<Organization> organizations = organizationDao.getOrganizationsBySuperhero(superhero);
		Organization organizationFromDao = organizations.get(0);
		
		assertEquals(organization, organizationFromDao);
		
	}

}
