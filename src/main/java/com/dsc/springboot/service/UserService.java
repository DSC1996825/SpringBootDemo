package com.dsc.springboot.service;

import java.util.List;

import com.dsc.springboot.model.User;

public interface UserService {

	public List<User> getUsers(int page, int limit) ;

	public User getUserById(int id) ;

	public int addUser(User user);

	public int updateUser(User user);
	
	public int deleteUser(int id);
	
	public int getUserCount() ;
	
}
