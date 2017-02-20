package com.asiainfo.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.demo.database.BaseDAO;
import com.asiainfo.demo.database.DataSourceSwitch;
import com.asiainfo.demo.domain.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDAO implements EmployeeDao {
	
	private static final String MAPPER_PATH = "com.asiainfo.demo.mapper.EmployeeMapper.";
	
//	@Autowired
//	private SqlSession sqlSession;

	@Override
	public List<Employee> getAllEmployee() throws Exception {
		DataSourceSwitch.setDataSourceType(DataSourceSwitch.ORACLE_DATA_SOURCE);
		List<Employee> emps = getSqlSession().selectList(MAPPER_PATH + "selectAllEmp");
		return emps;
	}

	@Override
	public int insertEmp(Employee emp) throws Exception {
		DataSourceSwitch.setDataSourceType(DataSourceSwitch.ORACLE_DATA_SOURCE);
		return getSqlSession().insert(MAPPER_PATH + "insert", emp);
	}

}
