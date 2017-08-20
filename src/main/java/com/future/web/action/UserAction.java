package com.future.web.action;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.Map;

import com.future.domain.BaseDict;
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
	 * 对用户进行进行增删改查
	 */
	private static final long serialVersionUID = 1L;
	
	private  User user=new User();
	private  BaseDict baseDict=new BaseDict();
	private  UserService userService;
	//当前页数
	private Integer currentPage;
	// 每页显示数据的条数
	private Integer pageSize;	
	
	
	
	 //跳转到添加用户界面
 	public String addUser() throws Exception {
 		return "addUser";
 	}

    //进行保存用户操作
	public String saveUser() throws Exception {
		// 1.执行判断操作
		userService.getUserJudge(user);
		// 2.添加身份 ，日期
		user.setRole("ordinary");
		user.setDate(new Date());
		baseDict.setDict_id("12");
		user.setJudge(baseDict);
		// 3.执行保存操作
		userService.saveUser(user);
		// 4.进行页面跳转
		return "toUserList";
	}

	// 对用户的分页查询
	public String userList() throws Exception {
		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);

		// 封装查询条件
		dc.add(Restrictions.like("judge.dict_id", "12", MatchMode.ANYWHERE));
		dc.add(Restrictions.eq("role", "ordinary"));

		// 判断并封装参数
		if (StringUtils.isNotBlank(user.getName())) {
			dc.add(Restrictions.like("name", "%" + user.getName() + "%"));
		}

		// 调用service 查询分页数据pagebean
		PageBean pb = userService.getPageBean(dc, currentPage, pageSize);

		// 将pagebean放到request域中，转发到页面显示
		ActionContext.getContext().getSession().put("pageBean", pb);

		return "userList";

	}
	
	
	
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		
		this.userService = userService;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
    
	
	
	
	
}
