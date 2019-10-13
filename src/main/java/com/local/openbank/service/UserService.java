package com.local.openbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.openbank.clientapi.OpenBankAPIClient;
import com.local.openbank.dto.UserDto;
import com.local.openbank.model.User;

@Service
public class UserService implements IUserService {
	@Autowired
	private OpenBankAPIClient openBankAPIClient;

	@Override
	public User createUser(UserDto user) {
		return openBankAPIClient.createUser(user);
	}

	@Override
	public User getCurrentUser() {
		return openBankAPIClient.getCurrentUser();
	}

}
