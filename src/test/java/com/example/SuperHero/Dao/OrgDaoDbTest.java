/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dto.Hero;
import com.example.SuperHero.Dto.Loc;
import com.example.SuperHero.Dto.Org;

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
public class OrgDaoDbTest {

    @Autowired
    LocDao locationDao;

    @Autowired
    OrgDao organizationDao;

    @Autowired
    SightDao sightingDao;

    @Autowired
    HeroDao superHeroDao;

    public OrgDaoDbTest() {
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

        List<Org> orgs = organizationDao.getAllOrganization();
        for (Org org : orgs) {
            organizationDao.deleteOrganizationById(org.getOrgId());
        }

    }

    @After
    public void tearDown() {
    }


    @Test
    public void testAddGetOrganizationById() {
        Org org = new Org();

        org.setOrgName("name");
        org.setDescription("description");
        org.setAddressInfo("address");
        org = organizationDao.addOrganization(org);

        Org fromDao = organizationDao.getOrganizationById(org.getOrgId());

        assertEquals(org, fromDao);
    }


    @Test
    public void testGetAllOrganization() {
        Org org = new Org();

        org.setOrgName("name");
        org.setDescription("description");
        org.setAddressInfo("address");
        org = organizationDao.addOrganization(org);

        Org org2 = new Org();

        org2.setOrgName("name");
        org2.setDescription("description");
        org2.setAddressInfo("address");
        org2 = organizationDao.addOrganization(org);

        List<Org> orgs = organizationDao.getAllOrganization();

       // assertEquals(2, organizations.size());
        assertTrue(orgs.contains(org));
        assertTrue(orgs.contains(org2));
    }


    @Test
    public void testUpdateOrganization() {
        Org org = new Org();

        org.setOrgName("name");
        org.setDescription("description");
        org.setAddressInfo("address");
        org = organizationDao.addOrganization(org);

        Org fromDao = organizationDao.getOrganizationById(org.getOrgId());

        assertEquals(org, fromDao);

        org.setOrgName("New Name");
        organizationDao.updateOrganization(org);

        assertNotEquals(org, fromDao);

        fromDao = organizationDao.getOrganizationById(org.getOrgId());

        assertEquals(org, fromDao);
    }


    @Test
    public void testDeleteOrganizationById() {
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

        Org fromDao = organizationDao.getOrganizationById(org.getOrgId());
        
        assertEquals(org, fromDao);

        organizationDao.deleteOrganizationById(org.getOrgId());
        fromDao = organizationDao.getOrganizationById(org.getOrgId());
        assertNull(fromDao);
    }

}
