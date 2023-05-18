package com.heroeducation.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.heroeducation.model.Org;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrgDAO {

    private final JdbcTemplate jdbcTemplate;

    public OrgDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Org> getAllOrganizations() {
        String sql = "SELECT * FROM org";
        return jdbcTemplate.query(sql, new OrgMapper());
    }

    public void createOrganization(Org organization) {
        String sql = "INSERT INTO org (name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, organization.getName(), organization.getDescription());
    }

    public Org getOrganizationById(int id) {
        String sql = "SELECT * FROM org WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrgMapper());
    }

    public void updateOrganization(Org organization) {
        String sql = "UPDATE org SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, organization.getName(), organization.getDescription(), organization.getId());
    }

    public void deleteOrganization(int id) {
        String sql = "DELETE FROM org WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class OrgMapper implements RowMapper<Org> {
        @Override
        public Org mapRow(ResultSet rs, int rowNum) throws SQLException {
            Org organization = new Org();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            return organization;
        }
    }
}
