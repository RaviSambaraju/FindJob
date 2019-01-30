package com.job.findJob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.findJob.model.User;
import com.job.findJob.service.UserService;

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public String register(@RequestBody User user) {
		return userService.addUser(user);
	}

}
