package com.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	public UserEntity findByEmailAndPassword(String email, String password);
	
}
