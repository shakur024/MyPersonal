package com.heroeducation.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.heroeducation.model.Loc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocDAO {

    private final JdbcTemplate jdbcTemplate;

    public LocDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Loc> getAllLocations() {
        String sql = "SELECT * FROM loc";
        return jdbcTemplate.query(sql, new LocMapper());
    }

    public void createLocation(Loc location) {
        String sql = "INSERT INTO loc (name, address) VALUES (?, ?)";
        jdbcTemplate.update(sql, location.getName(), location.getAddress());
    }

    public Loc getLocationById(Long id) {
        String sql = "SELECT * FROM loc WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new LocMapper());
    }

    public void updateLocation(Loc location) {
        String sql = "UPDATE loc SET name = ?, address = ? WHERE id = ?";
        jdbcTemplate.update(sql, location.getName(), location.getAddress(), location.getId());
    }

    public void deleteLocation(Long id) {
        String sql = "DELETE FROM loc WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class LocMapper implements RowMapper<Loc> {
        @Override
        public Loc mapRow(ResultSet rs, int rowNum) throws SQLException {
            Loc location = new Loc();
            location.setId(rs.getInt("id"));
            location.setName(rs.getString("name"));
            location.setAddress(rs.getString("address"));
            return location;
        }
    }
}
