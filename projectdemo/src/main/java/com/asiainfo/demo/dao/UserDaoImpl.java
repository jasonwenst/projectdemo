package com.asiainfo.demo.dao;

import org.springframework.stereotype.Repository;

import com.asiainfo.demo.database.BaseDAO;
import com.asiainfo.demo.database.DataSourceSwitch;
import com.asiainfo.demo.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDAO implements UserDao {
	
	private static final String MAPPER_PATH = "com.asiainfo.demo.mapper.UserMapper.";
	

	@Override
	public User getUserByUsername(User user) throws Exception {
		DataSourceSwitch.setDataSourceType(DataSourceSwitch.MYSQL_DATA_SOURCE);
		return getSqlSession().selectOne(MAPPER_PATH + "selectUserByUserName", user);
	}

	@Override
	public int insertUser(User user) throws Exception {
		DataSourceSwitch.setDataSourceType(DataSourceSwitch.MYSQL_DATA_SOURCE);
		return getSqlSession().insert(MAPPER_PATH + "insert", user);
	}

	@Override
	public int updateUser(User user) throws Exception {
		DataSourceSwitch.setDataSourceType(DataSourceSwitch.MYSQL_DATA_SOURCE);
		return getSqlSession().update(MAPPER_PATH + "updateByPrimaryKeySelective", user);
	}
	
	@Override
	public int deleteUser(User user) throws Exception {
		DataSourceSwitch.setDataSourceType(DataSourceSwitch.MYSQL_DATA_SOURCE);
		return getSqlSession().update(MAPPER_PATH + "deleteByPrimaryKey", user);
	}

}
