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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.marketplace.dao.ProjectDao;
import com.intuit.marketplace.model.Project;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Project Rest Api", tags = "Project Rest Api")
public class ProjectService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectDao projectDao;

	/**
	 * API to Query for all Open Projects
	 */
	@GetMapping("/allOpenProjects")
	@ApiOperation(value = "View all the open projects", response = List.class)
	public List<Project> getAllOpenProjects() {
		logger.info("View all open project : " + projectDao.findAllOpenProject());
		return projectDao.findAllOpenProject();
	}

	/**
	 * Get a Project by ID
	 */
	@GetMapping("/project/{id}")
	@ApiOperation(value = "get a project by id", response = Project.class)
	public Project getProjectById(@PathVariable Integer id) {
		return projectDao.findById(id);
	}

	/**
	 * Creating a Project
	 */
	@PostMapping("/project")
	@ApiOperation(value = "create a new project", response = Project.class)
	public Project createProject(@RequestBody Project project) {
		Date clientDate = project.getEndDateDate();
		Timestamp ts = new Timestamp(clientDate.getTime());
		project.setEndDate(ts);
		projectDao.insert(project);
		Project newProject = projectDao.findByUserTimeStamp(project.getUserId(), ts);
		return newProject;
	}

	@DeleteMapping("/project/{id}")
	@ApiOperation(value = "delete a project by id")
	public void deleteUser(@PathVariable Integer id) {
		if (id != null) {
			projectDao.deleteById(id);
		}
	}
	
	@PutMapping("/project/{id}/{open}")
	@ApiOperation(value = "close a project", response = Project.class)
	public Project updatePassword(@PathVariable Integer id, @PathVariable Boolean open) {
		projectDao.update(id, "open", open);
		return projectDao.findById(id);
	}

}
