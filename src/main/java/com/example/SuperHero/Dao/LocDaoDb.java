/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dto.Hero;
import com.example.SuperHero.Dto.Loc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class LocDaoDb implements LocDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Loc getLocationById(int id) {
        try {

            final String SELECT_LOCATION_BY_ID = "SELECT * FROM loc WHERE locId = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new locationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Loc> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM loc";
        return jdbc.query(SELECT_ALL_LOCATIONS, new locationMapper());
    }

    @Override
    public Loc addLocation(Loc location) {
        final String INSERT_LOCATION = "INSERT INTO loc(LocName, description, addressInfo, latitude, longitude) "
                + "VALUES(?, ?, ?, ? ,?)";
        jdbc.update(INSERT_LOCATION,
                location.getLocName(),
                location.getDescription(),
                location.getAddressInfo(),
                location.getLatitude(),
                location.getLongitude());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocId(newId);
        return location;
    }

    @Override
    public void updateLocation(Loc location) {

        final String UPDATE_LOCATION = "UPDATE loc SET locName = ?, description = ?,addressInfo =?,  longitude = ?, latitude = ? "
                + "WHERE locId = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getLocName(),
                location.getDescription(),
                location.getAddressInfo(),
                location.getLongitude(),
                location.getLatitude(),
                location.getLocId());

    }

    @Override
    public void deleteLocationById(int id) {

        final String DELETE_BY_SUPERHERO_SIGHITNG = "DELETE ss.* FROM superHeroSighting ss " 
                + "JOIN sight s ON ss.sightingId = s.sightingId WHERE locId = ?";
        
         jdbc.update(DELETE_BY_SUPERHERO_SIGHITNG, id);

        final String DELETE_SIGHTING_BY_LOCATION = "DELETE from sight WHERE locId = ?";
        jdbc.update(DELETE_SIGHTING_BY_LOCATION, id);

        final String DELETE_LOCATION_BY_ID = "DELETE FROM loc WHERE locId = ?";
        jdbc.update(DELETE_LOCATION_BY_ID, id);
    }

    @Override
    public List<Loc> getAllLocationBySuperHero(Hero hero) {

/*        final String SELECT_ALL_LOCATION_SUPERHERO = "SELECT l. * FROM location l "
                + "JOIN sighting s on l.id = s.locationId" +  " JOIN superHeroSighting hs on s.id = hs.sightingId WHERE hs.superHeroId = ?";*/

        final String SELECT_ALL_LOCATION_SUPERHERO = "SELECT l.* FROM loc l "
                + "JOIN sight s ON l.locId = s.locationId "
                + "JOIN superHeroSighting hs ON s.id = hs.sightingId "
                + "WHERE hs.superHeroId = ?";

        return jdbc.query(SELECT_ALL_LOCATION_SUPERHERO, new Object[]{hero.getHeroId()}, new locationMapper());}


    public static final class locationMapper implements RowMapper<Loc> {

        @Override
        public Loc mapRow(ResultSet rs, int i) throws SQLException {
            Loc location = new Loc();

            location.setLocId(rs.getInt("locId"));
            location.setLocName(rs.getString("LocName"));
            location.setDescription(rs.getString("description"));
            location.setAddressInfo(rs.getString("addressInfo"));
            location.setLatitude(rs.getDouble("latitude"));
            location.setLongitude(rs.getDouble("longitude"));
            return location;
        }
    }

}

