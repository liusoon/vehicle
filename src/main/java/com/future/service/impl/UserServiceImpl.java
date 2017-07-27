package com.future.service.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.future.dao.UserDao;
import com.future.domain.User;
import com.future.service.UserService;

@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=true,propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public User getUserByCodePassword(User u) {
	  //1.根据登陆账户查询User	
      User existU=userDao.getUserByCode(u.getCode());
	  //2.判断用户是否存在 ，不存在抛出异常
      if(existU == null) {
    	  throw new RuntimeException("账号不存在！");
      }
       //3.判断密码是否正确，不正确抛出异常
      if(!existU.getPassword().equals(u.getPassword())) {
    	  throw new RuntimeException("密码错误！！");
      }
       //4.返回用户
		return existU;
	}


	@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=false,propagation=Propagation.REQUIRED)
	public void saveUser(User u) {
         userDao.save(u);		
	}
 
}
