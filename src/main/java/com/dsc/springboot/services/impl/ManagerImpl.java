package com.dsc.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsc.springboot.mappers.ManagerMapper;
import com.dsc.springboot.models.Manager;
import com.dsc.springboot.services.ManagerService;

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
