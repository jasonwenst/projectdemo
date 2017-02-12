package com.asiainfo.demo.dao;

import com.asiainfo.demo.domain.User;

public interface UserDao {
	
	
	public User getUserByUsername(User user);
	
	public int insertUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUser(User user);

}
