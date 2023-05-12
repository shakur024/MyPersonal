/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dao.LocDaoDb.locationMapper;
import com.example.SuperHero.Dto.Loc;
import com.example.SuperHero.Dto.Sight;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class SightDaoDb implements SightDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sight getSightingById(int id) {
        try {

            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sight WHERE sightingId = ?";
            Sight sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new sightingMapper(), id);
            sighting.setLocation(getLocationForSighting(sighting.getSightId()));
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Loc getLocationForSighting(int sightingId) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM loc l "
                + "JOIN sight s ON s.locId = l.locId WHERE s.sightingId = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new locationMapper(), sightingId);
    }

    @Override
    public List<Sight> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sight";
        List<Sight> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new sightingMapper());
        helperForGetAllSighting(sightings);
        return sightings;
    }

    private void helperForGetAllSighting(List<Sight> sightings) {
        for (Sight sighting : sightings) {
            sighting.setLocation(getLocationForSighting(sighting.getSightId()));
        }
    }

    @Override
    @Transactional
    public Sight addSighting(Sight sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sight(sightDate, locId) "
                + "VALUES(?, ?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getSightDate(),
                sighting.getLocation().getLocId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightId(newId);
        return sighting;
    }

    @Override
    public void updateSighting(Sight sighting) {
        final String UPDATE_SIGHTING = "UPDATE sight SET sightDate = ?, locId = ? "
                + "WHERE sightingId = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getSightDate(),
                sighting.getLocation().getLocId(),
                sighting.getSightId());
    }

    @Override
    @Transactional
    public void deleteSightingById(int id) {

        final String DELETE_SUPERHERO_SIGHTING = "DELETE FROM superHeroSighting WHERE sightingId = ?";
        jdbc.update(DELETE_SUPERHERO_SIGHTING, id);

        final String DELETE_SIGHTING_BY_ID = "DELETE FROM sight WHERE sightingId = ?";
        jdbc.update(DELETE_SIGHTING_BY_ID, id);

    }

    @Override
    public List<Sight> getAllSightingByLocation(Loc location) {

        final String getAllSightingByLocation = " SELECT * from sight s "
                + "join loc l on s.sightingId=l.locId where s.sightingId=?";

        List<Sight> sightings = jdbc.query(getAllSightingByLocation,
                new sightingMapper(), location.getLocId());
        helperForGetAllSighting(sightings);
        return sightings;
    }

    @Override
    public List<Sight> getAllSightingByDate(LocalDate date) {

        final String GET_ALL_SIGHTING_BY_DATE = "SELECT * FROM sight WHERE sightDate = ?";

        List<Sight> sightings = jdbc.query(GET_ALL_SIGHTING_BY_DATE,
                new sightingMapper(), date);
        helperForGetAllSighting(sightings);
        return sightings;

    }

    public static final class sightingMapper implements RowMapper<Sight> {

        @Override
        public Sight mapRow(ResultSet rs, int i) throws SQLException {
            Sight sighting = new Sight();

            sighting.setSightId(rs.getInt("sightingId"));
            sighting.setSightDate(rs.getDate("sightDate").toLocalDate());
            return sighting;
        }

    }
}
