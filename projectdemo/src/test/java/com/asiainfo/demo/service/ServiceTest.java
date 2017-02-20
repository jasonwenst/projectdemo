package com.asiainfo.demo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.asiainfo.demo.configuration.JTAConfiguration;
import com.asiainfo.demo.configuration.MybatisConfiguration;
import com.asiainfo.demo.configure.BaseJUnit4Test;
import com.asiainfo.demo.domain.Employee;
import com.asiainfo.demo.domain.User;

public class ServiceTest 
	extends BaseJUnit4Test{
	
	@Autowired
	private EmpService empService;
	@Autowired
	private UserService userService;
	
	
	
	/**
	 * 测试多数据源事务提交
	 */
	@Test
	public void testTrans() throws Exception {
		
		Employee emp = new Employee();
		emp.setEmpName("习大大");
		emp.setEmpEmail("fasefas");
		emp.setEmpType("D");
		
		User user = new User();
		user.setUsername("kkkkkk");
		user.setSsoid("asdfsfgdhg");
		user.setAddress("fads");
		user.setEmail("faefa");
		user.setPassword("fasdfds");
		
		empService.addEmp(emp, user);
	}
	
	
	@Test
	public void testTransMysql() throws Exception {
		User user = new User();
		user.setUsername("kkkkkk");
		user.setSsoid("asdfsfgdhg");
		user.setAddress("fads");
		user.setEmail("faefa");
		user.setPassword("fasdfds");
		userService.addUser(user);
	}
	
	
	/**
	 * 另一种测试启动方式 ， 用AnnotationConfigApplicationContext 加载spring上下文配置
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JTAConfiguration.class, MybatisConfiguration.class);
		
		EmpService empService = (EmpService) context.getBean("empService");
		
		Employee emp = new Employee();
		
		emp.setEmpName("习大大");
		emp.setEmpEmail("fasefas");
		emp.setEmpType("D");
		
		empService.addEmp(emp, null);
	}

}
