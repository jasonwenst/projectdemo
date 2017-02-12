package com.asiainfo.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.demo.database.DataSourceSwitch;
import com.asiainfo.demo.domain.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Employee> getAllEmployee() {
		DataSourceSwitch.setDataSourceType(DataSourceSwitch.ORACLE_DATA_SOURCE);
		List<Employee> emps = sqlSession.selectList("selectAllEmp");
		return emps;
	}

}
