package com.heroeducation.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.heroeducation.model.Hero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class HeroDAO {
	private JdbcTemplate jdbcTemplate;

	// Constructor injection or autowire jdbcTemplate
	public HeroDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	public List<Hero> getAllHeroes() {
		String sql = "SELECT * FROM heroes";
		return jdbcTemplate.query(sql, new HeroMapper());
	}

	public void createHero(Hero hero) {
		String sql = "INSERT INTO heroes (name, alias, alignment, gender, imageUrl) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, hero.getName(), hero.getAlias(), hero.getAlignment(), hero.getGender(),
				hero.getImageUrl());
	}

	public Hero getHeroById(int id) {
		String sql = "SELECT * FROM heroes WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new HeroMapper());
	}

	public void updateHero(Hero hero) {
		String sql = "UPDATE heroes SET name = ?, alias = ?, alignment = ?, gender = ?, imageUrl = ? WHERE id = ?";
		jdbcTemplate.update(sql, hero.getName(), hero.getAlias(), hero.getAlignment(), hero.getGender(),
				hero.getImageUrl(), hero.getId());
	}

	public void deleteHero(int id) {
		String sql = "DELETE FROM heroes WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	// RowMapper implementation to map database results to Hero objects
	private static class HeroMapper implements RowMapper<Hero> {
		@Override
		public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
			Hero hero = new Hero();
			hero.setId(rs.getInt("id"));
			hero.setName(rs.getString("name"));
			hero.setAlias(rs.getString("alias"));
			hero.setAlignment(rs.getString("alignment"));
			hero.setGender(rs.getString("gender"));
			hero.setImageUrl(rs.getString("imageUrl"));
			return hero;
		}
	}
}
