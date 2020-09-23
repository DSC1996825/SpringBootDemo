package com.dsc.springboot.services;

import com.dsc.springboot.models.Manager;

public interface ManagerService {

	Manager login(String loginName,String password);
	
}
