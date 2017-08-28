package com.future.web.action;

import org.apache.commons.lang3.StringUtils;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import java.util.Date;
import java.util.List;
import com.future.domain.BaseDict;
import com.future.domain.Inform;
import com.future.domain.Maintain;
import com.future.domain.User;
import com.future.domain.Vehicle;
import com.future.service.InformService;
import com.future.service.MaintainService;
import com.future.service.UserService;
import com.future.service.VehicleService;
import com.future.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
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

public class UserAction extends BaseData implements ModelDriven<User>{

	/**
	 * 对用户进行进行增删改查
	 */
	
	private  User user=new User();
	private  BaseDict baseDict=new BaseDict();
	private  UserService userService;
	private  VehicleService vehicleService;
	private MaintainService maintainService;
	private User user1;
	private int sign;

	private int id;
	//当前页数
	private Integer currentPage;
	// 每页显示数据的条数
	private Integer pageSize;	
	
	//	测试版本
	private InformService informService;
	
	
	//跳转到添加用户界面
 	public String addUser() throws Exception {
 		Inform inform=new Inform("您进行了添加用户的操作请点击查看",date(),url(),role(),informName());
	    informService.save(inform);
 		return "addUser";
 	}

    //进行保存用户操作
	public String saveUser() throws Exception {
		// 1.执行判断操作
		// 1.执行判断操作
		userService.getUserJudge(user);
		// 2.添加身份 ，日期
		if(sign!=2){
			user.setRole("ordinary");
		}else{
			user.setRole("admin");
		}	
		user.setDate(new Date());
		baseDict.setDict_id("12");
		user.setJudge(baseDict);
		// 3.执行保存操作
		userService.saveUser(user);
		Inform inform=new Inform("您进行了保存用户操作",date(),role(),informName());
		informService.save(inform);
		// 4.进行页面跳转
		return "toUserList";
	}

	// 对用户的分页查询
	public String userList() throws Exception {
		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		
		dc.add(Restrictions.like("judge.dict_id", "12", MatchMode.ANYWHERE));
		
		// 封装查询条件	
		if(sign==2){
			dc.add(Restrictions.eq("role", "admin"));	
		}else{
			dc.add(Restrictions.eq("role", "ordinary"));
		}

		// 判断并封装参数
		if (StringUtils.isNotBlank(user.getName())) {
			dc.add(Restrictions.like("name", "%" + user.getName() + "%"));
		}

		// 调用service 查询分页数据pagebean
		PageBean pb = userService.getPageBean(dc, currentPage, pageSize);

		// 将pagebean放到request域中，转发到页面显示
		ActionContext.getContext().getSession().put("pageBean", pb);
		//系统的通知消息下同
		Inform inform=new Inform("您进行了查询所有用户的操作请点击查看",date(),url(),role(),informName());
	    informService.save(inform);
	    
		return "userList";

	}
	
	public String personal() throws Exception{
		Inform inform=new Inform("您进行了查看个人信息操作",date(),role(),informName());
		informService.save(inform);
		User user=userService.select(id);
	  	request.put("user1", user);
	  	return "personal";
	}
	
	public String forgetPassword() throws Exception{
		//通知消息
		return "forgetPassword";
	}
	

	//验证账号是否正确
	public String codeValidate() throws Exception{
		List<User> u=userService.getAll();
		for(User s:u){
			if(s.getCode().equals(user.getCode())){
				String str=s.getPhone();
				String src=str.substring(str.length()-4,str.length());
				request.put("user",s);
				request.put("src",src);
		  		return "phoneValidate";
			}else{
		  		request.put("errorMeg","账号不正确");
		  	}
		}
	  		return "forgetPassword";
	 }
		
	 //手机号验证
	 public String phoneValidate() throws Exception{
		 List<User> u=userService.getAll();
		 for(User s:u){
			if(s.getPhone().equals(user.getPhone())){
				request.put("user",s);
				return "updatePassword";	
			}else{
			  	request.put("errorMeg","手机号不正确");
			}
		  }
		return "phoneValidate";	 
	 }
	
     //根据提供的手机号可以修改自己的密码
	 public String updatePassword() throws Exception{
		User u=userService.select(id);
	  	u.setPassword(user.getPassword());
	  	user=u;
	  	userService.updateUser(user);
	  	request.put("successMeg","密码修改成功");
	  	return "success";
	  }
		
	 //根据id查询用户
	 public String selectUser() throws Exception{
		Inform inform=new Inform("您进行了查询用户的操作",date(),role(),informName());
		informService.save(inform);
	  	User user=userService.select(id);
	  	request.put("user1", user);
	  	return "selectUser";
	 }
	  	
	  //修改用户
	  public String updateUser() throws Exception{
		  Inform inform=new Inform("您进行了修改用户的操作",date(),role(),informName());
		  informService.save(inform);
		  User user=userService.select(id);
		  user.setCode(user1.getCode());
		  user.setName(user1.getName());
		  user.setAddress(user1.getAddress());
		  user.setPhone(user1.getPhone());
		  if(user1.getVehicleNumber()!=null){
			  user.setVehicleNumber(user1.getVehicleNumber());
			  user.setMaintainNumber(user1.getMaintainNumber());
		  }
		  userService.updateUser(user);
		  if(user.getRole().equals("admin")||user.getRole().equals("administrator")){
			  return "welcome";
		  } 
	  	 //对于车辆信息的同步修改
	  	 List<Vehicle> list=vehicleService.getAll();
	  	  for(Vehicle vehicle:list){
	  		if(vehicle.getUserId().equals(user1.getUserId())){
	  			vehicle.setUserName(user1.getName());
	  			vehicleService.updateVehicle(vehicle);
	  		}
	  	  }
	  	//对于维护信息的同步修改
	  	List<Maintain> m=maintainService.get();
	  	   for(Maintain maintain:m){
	  		   if(maintain.getUserId().equals(user1.getUserId())){
	  			   maintain.setUserName(user1.getName());
	  			   maintain.setUserPhone(user1.getPhone());
	  			   maintainService.updateMaintain(maintain);
	  			}
	  		}
	  	 return  userList();
	  }
	  	
	  //删除用户信息
	  public String deleteUser() throws Exception{
		  Inform inform=new Inform("您进行了删除用户的操作",date(),role(),informName());
		  informService.save(inform);
	  	  //对于用户信息的删除
	  	  User user=userService.select(id);
	  	  baseDict.setDict_id("11");
	  	  user.setJudge(baseDict);
	  	  userService.updateUser(user);
	  	  //对于车辆信息的删除
	  	  List<Vehicle> list=vehicleService.getAll();
	  	  for(Vehicle vehicle:list){
	  		if(vehicle.getUserId().equals(id)){
	  			vehicle.setJudge(baseDict);
	  			vehicleService.updateVehicle(vehicle);
	  		}
	  	  }
	  	//对于维护信息的删除
	  	List<Maintain> m=maintainService.get();
	  	   for(Maintain maintain:m){
	  		   if(maintain.getUserId().equals(id)){
	  			   maintain.setJudge(baseDict);
	  			   maintainService.updateMaintain(maintain);
	  			}
	  		}
	  		return userList();
	  	}	 
	 	
	//关于系统
	public String aboutSystem() throws Exception{
		Inform inform=new Inform("您进行了查看本系统的操作请点击查看",date(),url(),role(),informName());
		informService.save(inform);
		return "aboutSystem";
	}
	
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		
		this.userService = userService;
	}
	
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public void setMaintainService(MaintainService maintainService) {
		this.maintainService = maintainService;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}
	
	public void setInformService(InformService informService) {
		this.informService = informService;
	}
	
}
