package com.future.web.action;





import com.future.domain.User;

import com.future.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Package com.future.web.action
 * 
 * @Title: BaseAction.java 
 *
 * @author: 孤城落寞  
 *
 * @date 2017年8月20日 上午9:32:10
 * 
 * @Description: 登录，注销 ，改密  ，找回密码 ，关于系统 ，通知 ，帮助
 *   
 */
public class BaseAction  extends  BaseData  implements ModelDriven<User>{
    
	private static final long serialVersionUID = 1L;
	
	private  User user=new User();
	
	private  UserService userService;
   
	private  String old;
	
	
	
	//登录方法
	public String login() throws Exception {
		
        //1.调用service执行登录逻辑
		User u=userService.getUserByCodePassword(user);
	
		//2.将返回的User对象放入session域中
		ActionContext.getContext().getSession().put("User", u);
		

		//3.重定向到项目主页
	    if(u.getRole().equals("admin")) {
           ActionContext.getContext().getSession().put("admin", u);           
           return "admin";
	    }else if(u.getRole().equals("administrator")) {
	       ActionContext.getContext().getSession().put("administrator", u);
	       return "administrator";
        }else if(u.getRole().equals("both")) {
           ActionContext.getContext().getSession().put("both", u);
		   return "both";
        }else {
            return "error";    	
        }
	}
	
	//注销登录
	public String logout() throws Exception {
		
		ActionContext.getContext().getSession().remove("user");

		return "logout";
	}
	
	/*//页面跳转
	public String sign() throws Exception {
		
		ActionContext.getContext().getSession().remove("user");
		
		return "logout";
	}*/
	
	
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		
		this.userService = userService;
	}

	public String getOld() {
		return old;
	}

	public void setOld(String old) {
		this.old = old;
	}
	
    

		
}

