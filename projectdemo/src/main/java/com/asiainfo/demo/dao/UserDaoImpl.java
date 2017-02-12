package com.asiainfo.demo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.demo.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private static final String MAPPER_PATH = "com.asiainfo.demo.mapper.UserMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public User getUserByUsername(User user) {
		return sqlSession.selectOne(MAPPER_PATH + "selectUserByUserName", user);
	}

	@Override
	public int insertUser(User user) {
		return sqlSession.insert(MAPPER_PATH + "insert", user);
	}

	@Override
	public int updateUser(User user) {
		return sqlSession.update(MAPPER_PATH + "updateByPrimaryKeySelective", user);
	}
	
	@Override
	public int deleteUser(User user) {
		return sqlSession.update(MAPPER_PATH + "deleteByPrimaryKey", user);
	}

}
