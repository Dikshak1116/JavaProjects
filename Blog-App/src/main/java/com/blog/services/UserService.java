package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userid);
}
