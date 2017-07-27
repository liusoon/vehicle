package com.future.service;

import com.future.domain.User;

/**
 * @ProjectName vehicle 
 * 
 * @ClassName UserService
 *     
 * @author   孤城落寞  
 * 
 * @DateTime 2017年7月26日 上午10:53:18          
 */
public interface UserService {
    
	//登录方法
	User getUserByCodePassword(User  u);
    
	//注册方法
	void  saveUser(User u);
     
}
