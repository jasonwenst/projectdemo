package com.asiainfo.demo.database;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.asiainfo.demo.aop.TransactionSignManager;

public class BaseDAO {

	@Resource
	private SqlSession sqlSession;
	@Resource
	private SqlSession jtaSqlSession;
	
	public SqlSession getSqlSession() throws Exception {
		SqlSession currentSqlSession = null;
		if (!isJTA()) {
			currentSqlSession = sqlSession;
		} else {
			currentSqlSession = jtaSqlSession;
		}
		return currentSqlSession;
	}
	

	public int insert(String id, Map<String, Object> paramsMap) throws Exception {
		return getSqlSession().insert(id, paramsMap);
	}

	public int update(String id, Map<String, Object> paramsMap) throws Exception {
		return getSqlSession().update(id, paramsMap);
	}

	public int delete(String id, Map<String, Object> paramsMap) throws Exception {
		return getSqlSession().delete(id, paramsMap);
	}
	
	private boolean isJTA() {
		String transaction = TransactionSignManager.getTransaction();
		if ("JTA".equals(transaction)) {
			return true;
		} else {
			return false;
		}
	}

}
