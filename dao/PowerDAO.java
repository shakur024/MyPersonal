package com.heroeducation.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.heroeducation.model.Power;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PowerDAO {

    private final JdbcTemplate jdbcTemplate;

    public PowerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Power> getAllPowers() {
        String sql = "SELECT * FROM power";
        return jdbcTemplate.query(sql, new PowerMapper());
    }

    public void createPower(Power power) {
        String sql = "INSERT INTO power (name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, power.getName(), power.getDescription());
    }

    public Power getPowerById(Long id) {
        String sql = "SELECT * FROM power WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PowerMapper());
    }

    public void updatePower(Power power) {
        String sql = "UPDATE power SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, power.getName(), power.getDescription(), power.getId());
    }

    public void deletePower(Long id) {
        String sql = "DELETE FROM power WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class PowerMapper implements RowMapper<Power> {
        @Override
        public Power mapRow(ResultSet rs, int rowNum) throws SQLException {
            Power power = new Power();
            power.setId(rs.getInt("id"));
            power.setName(rs.getString("name"));
            power.setDescription(rs.getString("description"));
            return power;
        }
    }
}
