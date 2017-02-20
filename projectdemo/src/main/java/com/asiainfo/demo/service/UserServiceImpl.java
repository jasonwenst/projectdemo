package com.asiainfo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.demo.dao.UserDao;
import com.asiainfo.demo.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Transactional(propagation = Propagation.REQUIRED, value = "JTA",rollbackFor=Exception.class)
	public int addUser(User user) throws Exception {
		int ret = userDao.insertUser(user);
		throw new Exception();
//		return ret;
	}

}
