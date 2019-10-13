package com.local.openbank.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.local.openbank.dto.UserDto;
import com.local.openbank.model.User;
import com.local.openbank.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value="User API")
public class UserResource {

	@Autowired
	private IUserService userService;

	@PostMapping("/register")
	@ApiOperation(value = "Register the user",response = User.class)
	public User register(@RequestBody UserDto user) {
		log.info("In the Register User :{}", user);
		return userService.createUser(user);
	}
	
	@GetMapping("/users/current")
	@ApiOperation(value = "Get Current User",response = User.class)
	public User getCurrentUser() {
		log.info("In the getCurrentUser User :{}");
		return userService.getCurrentUser();
	}
}
