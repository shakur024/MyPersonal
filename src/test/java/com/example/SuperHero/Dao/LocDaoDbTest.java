/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dto.Hero;
import com.example.SuperHero.Dto.Loc;
import com.example.SuperHero.Dto.Sight;

import java.time.LocalDate;
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
public class LocDaoDbTest {

    @Autowired
    LocDao locationDao;

    @Autowired
    OrgDao organizationDao;

    @Autowired
    SightDao sightingDao;

    @Autowired
    HeroDao superHeroDao;

    public LocDaoDbTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    /*   List<Loc> locations = locationDao.getAllLocations();
        for (Loc location : locations) {
            locationDao.deleteLocationById(location.getLocId());
        }*/

    }

    @After
    public void tearDown() {
    }


    @Test
    public void testAddGetLocationById() {

        Loc location = new Loc();

        location.setLocName("dsjkfhsdjkf");
        location.setDescription("ejrtsjkldf");
        location.setAddressInfo("sjkfsdjkfljskl");
        location.setLatitude(9876);
        location.setLongitude(5467);
        location = locationDao.addLocation(location);

        Loc fromDao = locationDao.getLocationById(location.getLocId());

        assertEquals(location, fromDao);
    }


    @Test
    public void testGetAllLocations() {

        Loc location = new Loc();

        location.setLocName("setname");
        location.setDescription("setdescription");
        location.setAddressInfo("setaddress");
        location.setLongitude(2342);
        location.setLatitude(4345);
        location = locationDao.addLocation(location);

        Loc location2 = new Loc();

        location2.setLocName("setname");
        location2.setDescription("setdescription");
        location2.setAddressInfo("setaddress");
        location2.setLongitude(5654);
        location2.setLatitude(7574);
        location2 = locationDao.addLocation(location);

        List<Loc> locations = locationDao.getAllLocations();

       // assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }


    @Test
    public void testUpdateLocation() {

        Loc location = new Loc();

        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(3241);
        location.setLatitude(4345);
        location = locationDao.addLocation(location);

        Loc fromDao = locationDao.getLocationById(location.getLocId());

        assertEquals(location, fromDao);

        location.setLocName("New Name");
        locationDao.updateLocation(location);

        assertNotEquals(location, fromDao);

        fromDao = locationDao.getLocationById(location.getLocId());

        assertEquals(location, fromDao);

    }


    @Test
    public void testDeleteLocationById() {

        Loc location = new Loc();

        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(5677);
        location.setLatitude(8777);
        location = locationDao.addLocation(location);

        Sight sighting = new Sight();

        sighting.setSightDate(LocalDate.now());
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        List<Sight> sightings = new ArrayList<>();
        sightings.add(sighting);

        Hero hero = new Hero();

        hero.setHeroes("Test Name");
        hero.setDescription("Test Description");
        hero.setPowers("Test Power");
        hero.setOrganizations(new ArrayList());
        hero.setSights(new ArrayList());
        hero.setSights(sightings);
        hero = superHeroDao.addSuperHero(hero);

        Loc fromDao = locationDao.getLocationById(location.getLocId());
        assertEquals(location, fromDao);

        locationDao.deleteLocationById(location.getLocId());
        
        fromDao = locationDao.getLocationById(location.getLocId());
        assertNull(fromDao);

    }



}
