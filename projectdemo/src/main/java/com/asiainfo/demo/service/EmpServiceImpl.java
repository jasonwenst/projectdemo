package com.asiainfo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.demo.dao.EmployeeDao;
import com.asiainfo.demo.dao.UserDao;
import com.asiainfo.demo.domain.Employee;
import com.asiainfo.demo.domain.User;
import com.asiainfo.demo.utils.ExceptionUtils;

@Service("empService")
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private UserDao userDao;

	@Transactional(propagation = Propagation.REQUIRED, value = "JTA",rollbackFor=Exception.class)
	public void addEmp(Employee emp, User user) throws Exception {
		
		employeeDao.insertEmp(emp);
		
		
		ExceptionUtils.throwNullPointException();  // 抛出空指针异常，测试事务一致性
		
		userDao.insertUser(user);

	}

}
