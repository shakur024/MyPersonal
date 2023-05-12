/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dao.OrgDaoDb.organizationMapper;
import com.example.SuperHero.Dao.SightDaoDb.sightingMapper;
import com.example.SuperHero.Dto.Hero;
import com.example.SuperHero.Dto.Loc;
import com.example.SuperHero.Dto.Org;
import com.example.SuperHero.Dto.Sight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class HeroDaoDb implements HeroDao {

    @Autowired
    JdbcTemplate jdbc;


    private List<Sight> getSightingBySuperHero(int id) {
        final String GET_SIGHTING_BY_SUPERHERO = "SELECT s.* FROM sight s "
                + "JOIN superheroSighting hs on s.sightingId = hs.sightingId WHERE hs.superHeroId = ?";
        List<Sight> sightings = jdbc.query(GET_SIGHTING_BY_SUPERHERO, new sightingMapper(), id);
        helperForGetAllSighting(sightings);
        return sightings;

    }

    @Override
    public Hero getSuperHeroById(int id) {
        try {
            final String GET_HERO_BY_ID = "SELECT * FROM Hero WHERE heroId = ?";
            Hero hero = jdbc.queryForObject(GET_HERO_BY_ID, new superheroMapper(), id);
            hero.setSights(getSightingBySuperHero(id));
            hero.setOrganizations(getOrganizationBySuperHero(id));
            return hero;
        } catch (DataAccessException ex) {
            return null;
        }

    }

    private List<Org> getOrganizationBySuperHero(int id) {
        final String GET_ORGANIZATION_BY_SUPERHERO = "SELECT o.* FROM org o "
                + "JOIN superheroOrganization ho on o.orgId = ho.organizationId WHERE ho.superheroId = ?";
        return jdbc.query(GET_ORGANIZATION_BY_SUPERHERO, new organizationMapper(), id);

    }

    private Loc getLocationForSighting(int locationId) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM loc l "
                + "JOIN sight s ON s.locId = l.locId WHERE s.sightingId = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocDaoDb.locationMapper(), locationId);
    }

    private void helperForGetAllSighting(List<Sight> sightings) {
        for (Sight sighting : sightings) {
            sighting.setLocation(getLocationForSighting(sighting.getSightId()));

        }
    }

    @Override
    public List<Hero> getAllSuperHero() {
        final String GET_ALL_SUPERHEROS = "SELECT * from Hero";
        List<Hero> hero = jdbc.query(GET_ALL_SUPERHEROS, new superheroMapper());
        getAllOrganizationSightings(hero);
        return hero;
    }

    @Override
    public Hero addSuperHero(Hero superHero) {
        final String INSERT_SUPERHERO = "INSERT INTO Hero(heroes, description, Powers) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SUPERHERO,
                superHero.getHeroes(),
                superHero.getDescription(),
                superHero.getPowers());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superHero.setHeroId(newId);
        //insertSuperHeroSighting(superHero);
        //insertSuperHeroOrganization(superHero);
        return superHero;

    }

    private void insertSuperHeroSighting(Hero superHero) {
        final String INSERT_SUPERHERO_SIGHTING = "INSERT INTO "
                + "superHeroSighting(superHeroId, sightingId) VALUES(?,?)";
        for (Sight sighting : superHero.getSights()) {
            jdbc.update(INSERT_SUPERHERO_SIGHTING,
                    superHero.getHeroId(),
                    sighting.getSightId());
        }
    }

    private void insertSuperHeroOrganization(Hero superHero) {
        final String INSERT_SUPERHERO_ORGANIZATION = "INSERT INTO "
                + "superHeroOrganization(superHeroId, organizationId) VALUES(?,?)";
        for (Org org : superHero.getOrganizations()) {
            jdbc.update(INSERT_SUPERHERO_ORGANIZATION,
                    superHero.getHeroId(),
                    org.getOrgId());
        }
    }

    @Override
    public void updateSuperHero(Hero superHero) {
        final String UPDATE_SUPERHERO = "UPDATE Hero SET heroes = ?, description = ?, Powers = ? "
                + "WHERE heroId = ?";
        jdbc.update(UPDATE_SUPERHERO,
                superHero.getHeroes(),
                superHero.getDescription(),
                superHero.getPowers(),
                superHero.getHeroId());

        final String DELETE_HERO_ORGANIZATION = "DELETE FROM superHeroOrganization WHERE superHeroId = ? ";
        jdbc.update(DELETE_HERO_ORGANIZATION, superHero.getHeroId());
        insertSuperHeroOrganization(superHero);

        final String DELETE_HERO_SIGHTING = "DELETE FROM superHeroSighting WHERE superHeroId = ? ";
        jdbc.update(DELETE_HERO_SIGHTING, superHero.getHeroId());
        insertSuperHeroSighting(superHero);
    }

    @Override
    public void deleteSuperHeroById(int id) {
        final String DELETE_SUPERHERO_SIGHTING = "DELETE from superHeroSighting WHERE superHeroId = ?";
        jdbc.update(DELETE_SUPERHERO_SIGHTING, id);

        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE from superHeroOrganization WHERE superHeroId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, id);

        final String DELETE_SUPERHERO_BY_ID = "DELETE from Hero where heroId = ?";
        jdbc.update(DELETE_SUPERHERO_BY_ID, id);
    }

    @Override
    public List<Hero> getAllSuperHeroByLocation(Loc location) {
        final String GET_ALL_SUPERHERO_BY_LOCATION = "SELECT h. * FROM Hero h"
                + "JOIN superHeroSighting hs on h.heroId = hs.superHeroId"
                + "JOIN sight s on hs.sightingId = s.sightingIf WHERE s.locId = ? ";

        List<Hero> heroes = jdbc.query(GET_ALL_SUPERHERO_BY_LOCATION,
                new superheroMapper(), location.getLocId());
        getAllOrganizationSightings(heroes);
        return heroes;
    }

    @Override
    public List<Hero> getAllSuperHeroByOrganization(Org org) {
        final String GET_ALL_SUPERHERO_BY_LOCATION = "SELECT h. * FROM Hero h"
                + "JOIN superHeroOrganization hO on h.heroId = hO.superHeroId"
                + "JOIN org 0 on hO.organizationId = o.orgId WHERE o.orgId = ? ";

        List<Hero> heroes = jdbc.query(GET_ALL_SUPERHERO_BY_LOCATION,
                new superheroMapper(), org.getOrgId());
        getAllOrganizationSightings(heroes);
        return heroes;
    }

    private void getAllOrganizationSightings(List<Hero> heroes) {
        for (Hero hero : heroes) {
            hero.setSights(getSightingBySuperHero(hero.getHeroId()));
            hero.setOrganizations(getOrganizationBySuperHero(hero.getHeroId()));
        }
    }


    public static final class superheroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero superhero = new Hero();

            superhero.setHeroId(rs.getInt("heroId"));
            superhero.setHeroes(rs.getString("heroes"));
            superhero.setDescription(rs.getString("description"));
            superhero.setPowers(rs.getString("Powers"));
            return superhero;
        }

    }
}
