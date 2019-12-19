package com.dsc.springboot.service;

import com.dsc.springboot.model.Manager;

public interface ManagerService {

	Manager login(String loginName,String password);
	
}
