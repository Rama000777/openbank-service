package com.local.openbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.openbank.clientapi.OpenBankAPIClient;
import com.local.openbank.dto.UserDto;
import com.local.openbank.entity.repo.IUserRepository;
import com.local.openbank.model.User;

@Service
public class UserService implements IUserService {
	@Autowired
	private OpenBankAPIClient openBankAPIClient;

	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	CryptWithMD5 cryptWithMD5;

	@Override
	public User createUser(UserDto user) {
		User registeredUser = openBankAPIClient.createUser(user);
		com.local.openbank.entity.User usertoSave = new com.local.openbank.entity.User();
		usertoSave.setEmail(user.getEmail());
		usertoSave.setFirstName(user.getFirstName());
		usertoSave.setId(registeredUser.getUserId());
		usertoSave.setLastName(user.getLastName());
		usertoSave.setPassword(cryptWithMD5.cryptWithMD5(user.getPassword()));
		userRepository.save(usertoSave);
		return registeredUser;
	}

	@Override
	public User getCurrentUser() {
		return openBankAPIClient.getCurrentUser();
	}

}
