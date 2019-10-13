package com.local.openbank.service;

import com.local.openbank.dto.UserDto;
import com.local.openbank.model.User;

public interface IUserService {

	User createUser(UserDto user);

	User getCurrentUser();

}
