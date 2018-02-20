package com.intuit.marketplace.rest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.marketplace.dao.BidDao;
import com.intuit.marketplace.dao.ProjectDao;
import com.intuit.marketplace.dao.UserDao;
import com.intuit.marketplace.kafka.IntuitProducer;
import com.intuit.marketplace.model.Bid;
import com.intuit.marketplace.model.Project;
import com.intuit.marketplace.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Bid Rest Api", tags = "Bid Rest Api")
public class BidService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String INTUITMARKETTOPIC = "helloworld.t";
	
	@Autowired
	private BidDao bidDao;

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IntuitProducer producer;

	@GetMapping("/getBidsFromProject/{projectId}")
	@ApiOperation(value = "get all the bids from a project id", response = List.class)
	public List<Bid> getAllBidsFromProject(@PathVariable Integer projectId) {
		logger.info("all the bids from a project id: " + bidDao.findAllBidsByProjectId(projectId));
		
		return bidDao.findAllBidsByProjectId(projectId);
	}

	@GetMapping("/bid/{id}")
	@ApiOperation(value = "get bid by id", response = Bid.class)
	public Bid getUserById(@PathVariable Integer id) {
		return bidDao.findById(id);
	}
	
	/**
	 * API to find winning bid user
	 */
	@GetMapping("/winningBid/{projectId}")
	@ApiOperation(value = "get winning bid by projectId", response = User.class)
	public User findWinningBid(@PathVariable Integer projectId) {
		//check project is close or not. 
		Project project=projectDao.findById(projectId);
		if(project.isOpen()) {
			return null;
		}
		Bid bid =bidDao.findLowestBidByProjectId(projectId);
		return userDao.findById(bid.getUserId());
	}

	/**
	 * API to Bid for a Project
	 */
	@PostMapping("/bid")
	@ApiOperation(value = "create a new bid", response = Bid.class)
	public Bid createBidFromProject(@RequestBody Bid bid) {
		Project project = projectDao.findById(bid.getProjectId());
		// check if project exist and is open to bid.
		if (project != null && project.isOpen()) {
			Date bidTime = bid.getBidTimeDate();
			Timestamp ts = new Timestamp(bidTime.getTime());
			bid.setBidTime(ts);
			bidDao.insert(bid);
			Bid newBid = bidDao.findBid(bid.getProjectId(), bid.getUserId(), ts);
			producer.send(INTUITMARKETTOPIC, newBid.toString());
			return newBid;
		} else {
			return null;
		}

	}

	@DeleteMapping("/bid/{id}")
	@ApiOperation(value = "delete bid by id")
	public void deleteBid(@PathVariable Integer id) {
		if (id != null) {
			bidDao.deleteById(id);
		}
	}

}
