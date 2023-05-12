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
public class HeroDaoDbTest {

    @Autowired
    LocDao locationDao;

    @Autowired
    OrgDao organizationDao;

    @Autowired
    SightDao sightingDao;

    @Autowired
    HeroDao superHeroDao;

    public HeroDaoDbTest() {
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
    public void testAddGetSuperHeroById() {

        Loc location = new Loc();

        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(4563);
        location.setLatitude(8787);
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

        hero.setHeroes(" Name");
        hero.setDescription(" Description");
        hero.setPowers(" Power");
        hero.setSights(sightings);
        hero.setOrganizations(orgs);
        hero = superHeroDao.addSuperHero(hero);

        Hero fromDao = superHeroDao.getSuperHeroById(hero.getHeroId());
        System.out.println(fromDao);
        assertEquals(hero.getHeroId(), fromDao.getHeroId());
    }


    @Test
    public void testGetAllSuperHero() {

        Loc location = new Loc();

        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(9878);
        location.setLatitude(3567);
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

        hero.setHeroes(" Name");
        hero.setDescription(" Description");
        hero.setPowers(" Power");
        hero.setOrganizations(new ArrayList());
        hero.setSights(new ArrayList());
        hero.setSights(sightings);
        hero.setOrganizations(orgs);
        hero = superHeroDao.addSuperHero(hero);

        Hero hero2 = new Hero();

        hero2.setHeroes("Name");
        hero2.setDescription("Description");
        hero2.setPowers(" Power");
        hero2.setOrganizations(new ArrayList());
        hero2.setSights(new ArrayList());
        hero2.setSights(sightings);
        hero2.setOrganizations(orgs);
        hero2 = superHeroDao.addSuperHero(hero);

        List<Hero> heroes = superHeroDao.getAllSuperHero();
        assertEquals(2, heroes.size());

    }


    @Test
    public void testUpdateSuperHero() {

        Loc location = new Loc();

        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(5678);
        location.setLatitude(8744);
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

        hero.setHeroes(" Name");
        hero.setDescription(" Description");
        hero.setPowers(" Power");
        hero.setOrganizations(new ArrayList());
        hero.setSights(new ArrayList());
        hero.setSights(sightings);
        hero.setOrganizations(orgs);
        hero = superHeroDao.addSuperHero(hero);

        Hero fromDao = superHeroDao.getSuperHeroById(hero.getHeroId());

        hero.setHeroes(" HERO NAME");
        Org org2 = new Org();
        org2.setOrgName("organization name");
        org2.setDescription(" organization description");
        org2.setAddressInfo("ADDRESS");
        org2 = organizationDao.addOrganization(org2);
        orgs.add(org2);
        hero.setOrganizations(orgs);

        Sight sighting2 = new Sight();

        sighting2.setSightDate(LocalDate.now());
        sighting2.setLocation(location);
        sighting2 = sightingDao.addSighting(sighting2);
        sightings.add(sighting2);
        hero.setSights(sightings);

        superHeroDao.updateSuperHero(hero);

        assertNotEquals(hero, fromDao);

        fromDao = superHeroDao.getSuperHeroById(hero.getHeroId());
        assertEquals(hero.getHeroId(), fromDao.getHeroId());

    }


    @Test
    public void testDeleteSuperHeroById() {

        Loc location = new Loc();

        location.setLocName("name");
        location.setDescription("description");
        location.setAddressInfo("address");
        location.setLongitude(2222);
        location.setLatitude(4444);
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

        hero.setHeroes("Name");
        hero.setDescription(" Description");
        hero.setPowers(" Power");
        hero.setOrganizations(new ArrayList());
        hero.setSights(new ArrayList());
        hero.setSights(sightings);
        hero.setOrganizations(orgs);
        hero = superHeroDao.addSuperHero(hero);

        Hero fromDao = superHeroDao.getSuperHeroById(hero.getHeroId());

        superHeroDao.deleteSuperHeroById(hero.getHeroId());

        fromDao = superHeroDao.getSuperHeroById(hero.getHeroId());
        assertNull(fromDao);
    }

}
