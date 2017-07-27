package com.future.web.action;

import com.future.domain.User;
import com.future.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


/**
 * @ProjectName vehicle 
 * 
 * @ClassName UserAction
 *     
 * @author   孤城落寞  
 * 
 * @DateTime 2017年7月26日 下午10:24:00          
 */

public class UserAction  extends  ActionSupport  implements ModelDriven<User>{
     
	private  User user=new User();
	private  UserService userService;
	
	public void setUserService(UserService userService) {
	
		this.userService = userService;
	}
	
	//登录方法
	public String login() throws Exception {
        //1.调用service执行登录逻辑
		User u=userService.getUserByCodePassword(user);
		//2.将返回的User对象放入session域中
		ActionContext.getContext().getSession().put("user", u);
        //3.重定向到项目主页
		return "toHome";
	}

	
	public User getModel() {
		return user;
	}
	
	
}
