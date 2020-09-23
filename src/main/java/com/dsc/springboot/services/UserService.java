package com.dsc.springboot.services;

import java.util.List;

import com.dsc.springboot.models.User;

public interface UserService {

	public List<User> getUsers(int page, int limit) ;

	public User getUserById(int id) ;

	public int addUser(User user);

	public int updateUser(User user);
	
	public int deleteUser(int id);
	
	public int getUserCount() ;
	
}
