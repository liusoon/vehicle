package com.future.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.future.dao.UserDao;
import com.future.domain.User;
import com.future.service.UserService;
import com.future.utils.PageBean;

/**
 * @Package com.future.service.impl
 * 
 * @Title: UserServiceImpl.java 
 *
 * @author: 孤城落寞  
 *
 * @date 2017年7月31日 下午9:06:13
 * 
 * @Description:  
 *   
 */
@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=false,propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=true,propagation=Propagation.REQUIRED)
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

	@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=true,propagation=Propagation.REQUIRED)
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		
		//1.调用dao查询总记录
       
        Integer totalCount=userDao.getTotalCount(dc);
        
		//2.创建pageBean对象
		PageBean pb=new PageBean(currentPage,totalCount,pageSize);
       
		//3.调用dao查询分页列表数据
		List<User> list=userDao.getPageList(dc, pb.getStart(), pb.getPageSize());
		
		pb.setList(list);
		
		return pb;
	}



	@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=true,propagation=Propagation.REQUIRED)
	public User getUserJudge(User u) {
        User judgeU = userDao.getUserByCode(u.getCode());  
		if(judgeU !=null) {
		   throw new RuntimeException("添加用户失败，存在该用户 ！");		
		}
		return judgeU;
	}


    @Override
	public void saveUser(User u) {
		userDao.save(u);
		
	}

	@Override
	public void updateUserMaintain(Integer userId) {
		User user = userDao.getById(userId);		
	    Integer maintainNumber = user.getMaintainNumber();
	    if(maintainNumber==null) {
			 maintainNumber=0;
		 }
	    maintainNumber++;
	    user.setMaintainNumber(maintainNumber);
	    userDao.save(user);
	}

	@Override
	public void updateUserVehicle(Integer userId) {
		User user = userDao.getById(userId);		
		Integer vehicleNumber = user.getVehicleNumber();
		if(vehicleNumber==null) {
			vehicleNumber=0;
		 }
	    vehicleNumber++;
	    user.setVehicleNumber(vehicleNumber);
		userDao.save(user);
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=true,propagation=Propagation.REQUIRED)
	public User getUserById(Integer userId) {
		return userDao.getById(userId);
	}

	@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=true,propagation=Propagation.REQUIRED)
	public User getUserByCode(User u) {
		System.out.println(u);
		User userByCode = userDao.getUserByCode(u.getCode());
		System.out.println("user  --1");
		if(!userByCode.getName().equals(u.getName())) {
			throw new RuntimeException("车辆录入失败,不存在该车主！");
		}
		System.out.println("US2");
		return  userByCode;	
	}


	
 
}
