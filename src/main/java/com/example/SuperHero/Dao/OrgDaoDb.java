/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dto.Org;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class OrgDaoDb implements OrgDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Org getOrganizationById(int id) {
        try {

            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM org WHERE orgId = ?";
            return jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new organizationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Org> getAllOrganization() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM org";
        return jdbc.query(SELECT_ALL_ORGANIZATIONS, new organizationMapper());
    }

    @Override
    @Transactional
    public Org addOrganization(Org org) {
        final String INSERT_ORGANIZATION = "INSERT INTO org(orgName, description, addressInfo) "
                + "VALUES(?, ?, ?)";
        jdbc.update(INSERT_ORGANIZATION,
                org.getOrgName(),
                org.getDescription(),
                org.getAddressInfo());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setOrgId(newId);
        return org;
    }

    @Override
    public void updateOrganization(Org org) {
        final String UPDATE_ORGANIZATION = "UPDATE org SET orgName = ?, description = ?, addressInfo = ? "
                + "WHERE orgId = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                org.getOrgName(),
                org.getDescription(),
                org.getAddressInfo(),
                org.getOrgId());
    }

    @Override
    public void deleteOrganizationById(int id) {
        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE from superHeroOrganization WHERE organizationId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, id);
        
        final String DELETE_ORGANIZATION_BY_ID = "DELETE FROM org WHERE orgId = ?";
        jdbc.update(DELETE_ORGANIZATION_BY_ID, id);
    }

    public static final class organizationMapper implements RowMapper<Org> {

        @Override
        public Org mapRow(ResultSet rs, int i) throws SQLException {
            Org org = new Org();
            
            org.setOrgId(rs.getInt("orgId"));
            org.setOrgName(rs.getString("orgName"));
            org.setDescription(rs.getString("description"));
            org.setAddressInfo(rs.getString("addressInfo"));
            return org;
        } 
    }
}
