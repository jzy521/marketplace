package com.intuit.marketplace.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.intuit.marketplace.model.User;

/**
 * UserDao use to access data in user table. User can insert and delete using id
 * or email. Once user is created, user can only update name, password field.
 * 
 * @author peter
 */

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class UserRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			return user;
		}

	}

	public List<User> findAll() {
		return jdbcTemplate.query("select * from user", new UserRowMapper());
	}

	public User findById(int id) {
		return jdbcTemplate.queryForObject("select * from user where id=?", new Object[] { id },
				new BeanPropertyRowMapper<User>(User.class));
	}

	public User findByEmail(String email) {
		return jdbcTemplate.queryForObject("select * from user where email=?", new Object[] { email },
				new BeanPropertyRowMapper<User>(User.class));
	}

	public int deleteById(int id) {
		return jdbcTemplate.update("delete from user where id=?", new Object[] { id });
	}

	public int insert(User user) {
		return jdbcTemplate.update("insert into user (name, email, password) " + "values( ?, ?, ?)",
				new Object[] { user.getName(), user.getEmail(), user.getPassword() });
	}

	/**
	 * * Once project is created, only update a field once a time to save
	 * development time.
	 */
	public int update(int userId, String fieldName, Object fieldValue) {

		return jdbcTemplate.update("update user  set " + fieldName + " = ? " + " where id = ?",
				new Object[] { fieldValue, userId });
	}

}
