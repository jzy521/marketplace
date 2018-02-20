package com.intuit.marketplace.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.marketplace.dao.UserDao;
import com.intuit.marketplace.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "User Rest Api", tags = "User Rest Api")
public class UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;

	@GetMapping("/allUsers")
	@ApiOperation(value = "View a list of users", response = List.class)
	public List<User> getUsers() {
		logger.info("View a list of users :" + userDao.findAll());
		return userDao.findAll();
	}

	@GetMapping("/user/{id}")
	@ApiOperation(value = "get user by id", response = User.class)
	public User getUserById(@PathVariable Integer id) {
		return userDao.findById(id);
	}

	@PostMapping("/user")
	@ApiOperation(value = "create a new user", response = User.class)
	public User createUser(@RequestBody User user) {
		userDao.insert(user);
		User newUser = userDao.findByEmail(user.getEmail());
		return newUser;
	}

	@DeleteMapping("/user/{id}")
	@ApiOperation(value = "delete user by id")
	public void deleteUser(@PathVariable Integer id) {
		if (id != null) {
			userDao.deleteById(id);
		}
	}

	@PutMapping("/user/{userId}/{password}")
	@ApiOperation(value = "update user password", response = User.class)
	public User updatePassword(@PathVariable Integer userId, @PathVariable String password) {
		userDao.update(userId, "password", password);
		return userDao.findById(userId);
	}

}
