package com.asiainfo.demo.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.asiainfo.demo.configure.BaseJUnit4Test;
import com.asiainfo.demo.database.DataSourceSwitch;
import com.asiainfo.demo.domain.Employee;
import com.asiainfo.demo.domain.User;


public class DaoTest extends BaseJUnit4Test {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@Test
	public void testQueryUser() {
		DataSourceSwitch.setDataSourceType(DataSourceSwitch.MYSQL_DATA_SOURCE);
		User user = new User();
		user.setUsername("jason");
		User rtUser = userDao.getUserByUsername(user);
		
		Assert.assertTrue(rtUser != null && !StringUtils.isEmpty(rtUser.getUsername()));
		
		System.out.println(rtUser.toString());
	}
	
	
	@Test
//	@Ignore
	public void testInsertUser() {
		User user = new User();
		user.setAddress("杭州");
		user.setEmail("fasefa@qq.com");
		user.setPassword("12134");
		user.setSsoid("GSERTGSDRGSd");
		user.setUsername("jason");
		
		Assert.assertTrue(userDao.insertUser(user) == 1);
	}
	
	@Ignore
	@Test
	public void testUpdateUser() {
		
		User user = new User();
		user.setId(4234364);
		user.setAddress("北京");
		
		Assert.assertTrue(userDao.updateUser(user) == 1);
	}
	
	@Test
	@Ignore
	public void testDeleteUser() {
		User user = new User();
		user.setId(4234364);
		
		Assert.assertTrue(userDao.deleteUser(user) == 1);
	}
	
	
	@Test
	public void testQryEmp() {
		List<Employee> emps = employeeDao.getAllEmployee();
		
		for(Employee emp : emps) {
			System.out.println(emp.toString());
		}
	}
	
	
	@Test
	public void testMultDataSource() {
		testQryEmp();
		testQueryUser();
	}
	

}
