package com.asiainfo.demo.dao;

import java.util.List;

import com.asiainfo.demo.domain.Employee;

public interface EmployeeDao {
	
	public List<Employee> getAllEmployee()  throws Exception;
	
	
	public int insertEmp(Employee emp) throws Exception;

}
