package org.code.server.service.impl;


import org.code.server.dao.IUserDao;
import org.code.server.model.User;
import org.code.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;  
	
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);  
	}

}
