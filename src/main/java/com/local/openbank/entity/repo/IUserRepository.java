package com.local.openbank.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.local.openbank.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{

}
