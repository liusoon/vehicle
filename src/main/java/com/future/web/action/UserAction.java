package com.future.web.action;

import com.future.domain.Role;
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
		Role r=(Role) u.getRole();
		System.out.println(1);
		System.out.println(r);
		System.out.println(2);
		//2.将返回的User对象放入session域中
        //3.重定向到项目主页
	    if(r.getAdmin().equals("admin") && r.getAdmin().equals("ordinary")){
	    	ActionContext.getContext().getSession().put("User", u);
	    	return "both";
	    }else if(r.getAdmin().equals("admin")){
	    	ActionContext.getContext().getSession().put("User", u);
	    	return "admin";
	    }else {
	    	ActionContext.getContext().getSession().put("User", u);
	    	return "ordinary";
	    }
		
	}

	
	public User getModel() {
		return user;
	}
	
	
}
