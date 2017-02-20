package com.asiainfo.demo.service;

import com.asiainfo.demo.domain.Employee;
import com.asiainfo.demo.domain.User;

public interface EmpService {
	
	public void addEmp(Employee emp, User user)  throws Exception;

}
