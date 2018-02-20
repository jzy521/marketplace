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

import com.intuit.marketplace.model.Bid;

@Repository
public class BidDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class BidRowMapper implements RowMapper<Bid> {

		public Bid mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bid bid = new Bid();
			bid.setId(rs.getInt("id"));
			bid.setProjectId(rs.getInt("projectId"));
			bid.setUserId(rs.getInt("userId"));
			bid.setBidTime(rs.getTimestamp("bidTime"));
			bid.setBidAmount(rs.getBigDecimal("bidAmount"));
			bid.setBidPrice(rs.getBigDecimal("bidPrice"));
			return bid;
		}

	}

	public Bid findById(int id) {
		return jdbcTemplate.queryForObject("select * from bid where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Bid>(Bid.class));
	}

	public List<Bid> findAllBidsByProjectId(int projectId) {
		return jdbcTemplate.query("select * from bid where projectId=?", new BidRowMapper(),
				new Object[] { projectId });
	}

	public int deleteById(int id) {
		return jdbcTemplate.update("delete from bid where id=?", new Object[] { id });
	}

	public int insert(Bid bid) {
		return jdbcTemplate.update(
				"insert into bid (projectId, userId, bidTime, bidAmount, bidPrice) " + "values( ?, ?, ?, ?, ?)",
				new Object[] { bid.getProjectId(), bid.getUserId(), bid.getBidTime(), bid.getBidAmount(),
						bid.getBidPrice() });
	}

	public Bid findBid(int projectId, int userId, Timestamp bidTime) {
		return jdbcTemplate.queryForObject("select * from bid where projectId=? and userId =? and bidTime = ?",
				new BidRowMapper(), new Object[] { projectId, userId, bidTime });
	}
	
	public Bid findLowestBidByProjectId(int projectId) {
		return jdbcTemplate.queryForObject("select * from bid where projectId=? order by bidPrice desc limit 1, 1",
				new BidRowMapper(), new Object[] { projectId});
	}

}
