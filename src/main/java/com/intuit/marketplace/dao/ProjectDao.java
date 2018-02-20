package com.intuit.marketplace.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.intuit.marketplace.model.Project;

/**
 * ProjectDao use to access data in project table. userId field is foreign key
 * references id field in User class User can insert and delete using project id
 * 
 * @author peter
 */
@Repository
public class ProjectDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class ProjectRowMapper implements RowMapper<Project> {

		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
			Project project = new Project();
			project.setId(rs.getInt("id"));
			project.setName(rs.getString("name"));
			project.setUserId(rs.getInt("userId"));
			project.setOpen(rs.getBoolean("open"));
			project.setBudget(rs.getBigDecimal("budget"));
			project.setDescription(rs.getString("description"));
			project.setEndDate(rs.getTimestamp("endDate"));
			project.setBidNum(rs.getInt("bidNum"));
			project.setWinningPrice(rs.getBigDecimal("winningPrice"));
			project.setCurrentPrice(rs.getBigDecimal("currentPrice"));
			return project;
		}

	}

	public List<Project> findAllOpenProject() {
		return jdbcTemplate.query("select * from project where open=true", new ProjectRowMapper());
	}

	public Project findById(int id) {
		return jdbcTemplate.queryForObject("select * from project where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Project>(Project.class));
	}

	public Project findByUserTimeStamp(int userId, Timestamp endDate) {
		return jdbcTemplate.queryForObject("select * from project where userId=? and endDate = ?",
				new Object[] { userId, endDate }, new BeanPropertyRowMapper<Project>(Project.class));
	}

	public int deleteById(int id) {
		return jdbcTemplate.update("delete from project where id=?", new Object[] { id });
	}

	public int insert(Project project) {
		return jdbcTemplate.update(
				"insert into project (name, userId, open, budget, description, endDate, bidNum, winningPrice, currentPrice) "
						+ "values( ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] { project.getName(), project.getUserId(), project.isOpen(), project.getBudget(),
						project.getDescription(), project.getEndDate(), project.getBidNum(), project.getWinningPrice(),
						project.getCurrentPrice() });
	}

	/**
	 * * Once project is created, only update a field once a time to save
	 * development time.
	 */
	public int update(int projectId, String fieldName, Object fieldValue) {

		return jdbcTemplate.update("update project  set " + fieldName + " = ? " + " where id = ?",
				new Object[] { fieldValue, projectId });
	}

}
