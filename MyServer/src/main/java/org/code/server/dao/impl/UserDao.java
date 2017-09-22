package org.code.server.dao.impl;


import org.code.server.dao.IUserDao;
import org.code.server.model.User;
import org.springframework.stereotype.Repository;
@Repository
public class UserDao extends BaseDao implements IUserDao{
	
	public int deleteByPrimaryKey(Integer uid) {
		return 0;
	}

	public int insert(User record) {
		return 0;
	}

	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public User selectByPrimaryKey(Integer uid) {
		String sql="User.selectByPrimaryKey";
		User user = this.getSqlSession().selectOne(sql, 14);
		return user;
	}

	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}



}
