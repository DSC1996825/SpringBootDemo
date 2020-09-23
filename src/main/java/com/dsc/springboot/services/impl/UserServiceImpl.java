package com.dsc.springboot.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsc.springboot.mappers.UserMapper;
import com.dsc.springboot.models.User;
import com.dsc.springboot.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getUsers(int page, int limit) {
		return userMapper.getUsers((page-1)*limit, limit);
	}

	@Override
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}

	@Override
	@Transactional
	public int addUser(User user) {
		return userMapper.addUser(user);
	}
	
	@Override
	@Transactional
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}
	
	@Override
	@Transactional
	public int deleteUser(int id) {
		return userMapper.deleteUser(id);
	}
	
	@Override
	public int getUserCount() {
		return userMapper.getUserCount();
	}

}
