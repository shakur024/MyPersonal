/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dto.Hero;
import com.example.SuperHero.Dto.Loc;
import com.example.SuperHero.Dto.Org;
import com.example.SuperHero.Dto.Sight;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SightDaoDbTest {

    @Autowired
    LocDao locationDao;

    @Autowired
    OrgDao organizationDao;

    @Autowired
    SightDao sightingDao;

    @Autowired
    HeroDao superHeroDao;

    public SightDaoDbTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        List<Loc> locations = locationDao.getAllLocations();
        for (Loc location : locations) {
            locationDao.deleteLocationById(location.getLocId());
        }

        List<Org> organizations = organizationDao.getAllOrganization();
        for (Org organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getOrgId());
        }

        List<Sight> sightings = sightingDao.getAllSightings();
        for (Sight sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSightId());
        }

        List<Hero> superHeros = superHeroDao.getAllSuperHero();
        for (Hero superHero : superHeros) {
            superHeroDao.deleteSuperHeroById(superHero.getHeroId());
        }
    }

    @After
    public void tearDown() {
    }


    @Test
    public void testGetSightingById() {

        Loc location = new Loc();
        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(8787);
        location.setLatitude(3773);
        location = locationDao.addLocation(location);

        Sight sighting = new Sight();
        sighting.setSightDate(LocalDate.now());
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);

        Sight fromDao = sightingDao.getSightingById(sighting.getSightId());
        System.out.println(fromDao);

        assertEquals(sighting, fromDao);
    }


    @Test
    public void testGetAllSightings() {

        Loc location = new Loc();

        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(1111);
        location.setLatitude(7575);
        location = locationDao.addLocation(location);

        Loc location2 = new Loc();

        location2.setLocName("name");
        location2.setDescription("description");
        location2.setAddressInfo("address");
        location2.setLongitude(1111);
        location2.setLatitude(7575);
        location2 = locationDao.addLocation(location2);

        Sight sighting = new Sight();
        sighting.setSightDate(LocalDate.now());
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);

        Sight sighting2 = new Sight();
        sighting2.setSightDate(LocalDate.now());
        sighting2.setLocation(location2);
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sight> sightings = sightingDao.getAllSightings();

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }


    @Test
    public void testUpdateSighting() {
        
        Loc location = new Loc();

        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(5654);
        location.setLatitude(2222);
        location = locationDao.addLocation(location);

        Sight sighting = new Sight();
        sighting.setSightDate(LocalDate.now());
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);

        Sight fromDao = sightingDao.getSightingById(sighting.getSightId());

        assertEquals(sighting, fromDao);

        sighting.setSightDate(LocalDate.of(2019, Month.DECEMBER, 28));
        sightingDao.updateSighting(sighting);

        assertNotEquals(sighting, fromDao);

        fromDao = sightingDao.getSightingById(sighting.getSightId());

        assertEquals(sighting, fromDao);
    }


    @Test
    public void testDeleteSightingById() {

        Loc location = new Loc();
        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(8765);
        location.setLatitude(4647);
        location = locationDao.addLocation(location);

        Sight sighting = new Sight();
        sighting.setSightDate(LocalDate.now());
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        List<Sight> sightings = new ArrayList<>();
        sightings.add(sighting);

        Org org = new Org();
        org.setOrgName("name");
        org.setDescription("description");
        org.setAddressInfo("address");
        org = organizationDao.addOrganization(org);
        List<Org> orgs = new ArrayList<>();
        orgs.add(org);

        Hero hero = new Hero();
        hero.setHeroes("Test Name");
        hero.setDescription("Test Description");
        hero.setPowers("Test Power");
        hero.setOrganizations(orgs);
        hero.setSights(new ArrayList<>());
        hero = superHeroDao.addSuperHero(hero);

        Sight fromDao = sightingDao.getSightingById(sighting.getSightId());
        assertEquals(sighting, fromDao);

        sightingDao.deleteSightingById(sighting.getSightId());
        fromDao = sightingDao.getSightingById(sighting.getSightId());
        assertNull(fromDao);
    }


}
