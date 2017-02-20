package com.asiainfo.demo.dao;

import com.asiainfo.demo.domain.User;

public interface UserDao {
	
	
	public User getUserByUsername(User user) throws Exception ;
	
	public int insertUser(User user) throws Exception ;
	
	public int updateUser(User user) throws Exception ;
	
	public int deleteUser(User user) throws Exception ;

}
