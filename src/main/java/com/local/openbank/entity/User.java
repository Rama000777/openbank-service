package com.local.openbank.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	String id;
	String email;
	String firstName;
	String lastName;
	String password;

}
