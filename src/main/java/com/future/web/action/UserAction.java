package com.future.web.action;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import java.util.Map;
import com.future.domain.User;
import com.future.service.UserService;
import com.future.utils.PageBean;
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
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  User user=new User();
	
	private  UserService userService;
		
	
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
        }else if(u.getRole().equals("both")) {
           ActionContext.getContext().getSession().put("both", u);
		   return "both";
        }else if(u.getRole().equals("ordinary")) {
        	ActionContext.getContext().getSession().put("ordinary", u);
        	return "ordinary";
        }else {
            return "error";    	
        }
	}
	
		
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		
		this.userService = userService;
	}
	
}
