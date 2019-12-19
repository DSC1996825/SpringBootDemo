package com.dsc.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsc.springboot.mapper.UserMapper;
import com.dsc.springboot.model.User;
import com.dsc.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	public List<User> getUsers(int page, int limit) {
		return userMapper.getUsers((page-1)*limit, limit);
	}

	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}

	@Transactional
	public int addUser(User user) {
		return userMapper.addUser(user);
	}
	
	@Transactional
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}
	
	@Transactional
	public int deleteUser(int id) {
		return userMapper.deleteUser(id);
	}
	
	public int getUserCount() {
		return userMapper.getUserCount();
	}

}
