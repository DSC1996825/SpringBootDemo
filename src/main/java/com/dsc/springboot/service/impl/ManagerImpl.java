package com.dsc.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsc.springboot.mapper.ManagerMapper;
import com.dsc.springboot.model.Manager;
import com.dsc.springboot.service.ManagerService;

@Service
public class ManagerImpl implements ManagerService {
	
	@Autowired
	private ManagerMapper managerMapper;

	@Override
	public Manager login(String loginName,String password) {
		Manager manager = managerMapper.login(loginName, password);
		return manager;
	}

}
