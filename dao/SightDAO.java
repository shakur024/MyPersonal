package com.heroeducation.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.heroeducation.model.Hero;
import com.heroeducation.model.Loc;
import com.heroeducation.model.Sight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SightDAO {

    private final JdbcTemplate jdbcTemplate;

    public SightDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sight> getAllSightings() {
        String sql = "SELECT * FROM sight";
        return jdbcTemplate.query(sql, new SightMapper());
    }
    public List<Sight> getTenSightings() {
        String sql = "SELECT *\r\n"
        		+ "FROM sight\r\n"
        		+ "ORDER BY dateTime DESC\r\n"
        		+ "LIMIT 10;";
        return jdbcTemplate.query(sql, new SightMapper());
    }

    public void createSighting(Sight sighting) {
        String sql = "INSERT INTO sight (hero_id, loc_id, sight_time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, sighting.getHero().getId(), sighting.getLocation().getId(), sighting.getDateTime());
    }

    public Sight getSightingById(int id) {
        String sql = "SELECT * FROM sight WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new SightMapper());
    }
    


    public void updateSighting(Sight sighting) {
        String sql = "UPDATE sight SET hero_id = ?, loc_id = ?, sight_time = ? WHERE id = ?";
        jdbcTemplate.update(sql, sighting.getHero().getId(), sighting.getLocation().getId(), sighting.getDateTime(), sighting.getId());
    }

    public void deleteSighting(int id) {
        String sql = "DELETE FROM sight WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class SightMapper implements RowMapper<Sight> {
        @Override
        public Sight mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sight sighting = new Sight();
            sighting.setId(rs.getInt("id"));
            Hero hero = new Hero();
            hero.setId(rs.getInt("hero_id"));
            sighting.setHero(hero);
            Loc location = new Loc();
            location.setId(rs.getInt("loc_id"));
            sighting.setLocation(location);
            sighting.setDateTime(rs.getTimestamp("sight_time").toLocalDateTime());
            return sighting;
        }
    }
}