package com.future.web.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.future.domain.BaseDict;
import com.future.domain.Maintain;
import com.future.domain.User;
import com.future.domain.Vehicle;

import com.future.service.MaintainService;
import com.future.service.UserService;
import com.future.service.VehicleService;
import com.future.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author 孤城落寞
 *
 */
public class AdminAction extends ActionSupport{
    
	private User user;
	private Vehicle vehicle;
	private Maintain maintain;
	
	private MaintainService maintainService;
	private UserService   userService;
    private VehicleService vehicleService;
    
    //当前页数
  	private  Integer currentPage;
  	//每页显示数据的条数
  	private  Integer pageSize;
	
  	
  	//跳转到添加用户界面
  	public String addUser() throws Exception {   
  		return "addUser";
  	}  	
  	
  	//进行保存用户操作
  	public String saveUser() throws Exception {   
  	    //1.执行判断操作
  		userService.getUserJudge(user);
  	    //2.添加身份 ，日期
  		user.setRole("ordinary");
  		user.setDate(new Date());
  	    user.setJudge("Y");
  	    //3.执行保存操作
  		userService.saveUser(user); 
  	    //4.进行页面跳转		
  		return "toUserList";
  	}
  	
  	//对用户的分页查询
  	public String userList() throws Exception {
  		//封装离线查询对象
  		DetachedCriteria dc=DetachedCriteria.forClass(User.class);
        
  		//封装查询条件
  		dc.add(Restrictions.eq("role","ordinary"));
  		dc.add(Restrictions.eq("judge","Y"));
  		
  		
  		//判断并封装参数
  	   if(StringUtils.isNotBlank(user.getName())){
  		  dc.add(Restrictions.like("name", "%"+user.getName()+"%"));
  	   }
  		
  		
  		//调用service 查询分页数据pagebean
  		PageBean pb=userService.getPageBean(dc,currentPage, pageSize);
  	    
  		//将pagebean放到request域中，转发到页面显示
  		ActionContext.getContext().getSession().put("pageBean", pb);
  	    		
  		return "userList";
  	
  	}
  	
    //进行车辆备案
  	/**
     * 在车辆备案成功后在通过userId查询对象，并在车辆数目上+1
  	 * 
  	 * 
  	 * 
  	 * 
  	 * */
  	public String addVehicleList() throws Exception{
  	    
  		//封装离线查询对象
  		DetachedCriteria dc=DetachedCriteria.forClass(Vehicle.class);
  	    
  		/*BaseDict dict = vehicle.getOperationStatus();
        dc.add(Restrictions.eq("B", "10"));*/
  		
  		//判断并封装参数
  		if(StringUtils.isNotBlank(vehicle.getPlateId())) {
  			dc.add(Restrictions.like("plateId", "%"+vehicle.getPlateId()+"%"));
  		}
  		
  		//调用service 查询分页数据pagebean
  		PageBean pb=vehicleService.getPageBean(dc,currentPage, pageSize);
  		
  		//将pagebean放到request域中，转发到页面显示
  		ActionContext.getContext().getSession().put("pageBean", pb);
  	  
  	    return "addVehicleList";
  	
  	}
  	
  	//对车辆进行查询
  	public String vehicleList() throws Exception {
  	    
  		//封装离线查询对象
  		DetachedCriteria dc=DetachedCriteria.forClass(Vehicle.class);
  	    
        // dc.add(Restrictions.eq("judge", "Y"));
  		
  		//判断并封装参数
  		/*if(StringUtils.isNotBlank(vehicle.getPlateId())) {
  			dc.add(Restrictions.like("plateId", "%"+vehicle.getPlateId()+"%"));
  		}*/
  	
  		//调用service 查询分页数据pagebean
  		PageBean pb=vehicleService.getPageBean(dc,currentPage, pageSize);
  		
  		//将pagebean放到request域中，转发到页面显示
  		ActionContext.getContext().getSession().put("pageBean", pb);
  	  
  		return "vehicleList";
  	
  	}
  	
  	//对维护信息录入
  	public String addMaintain() throws Exception {
  		return "addMaintain";
  	}
  
  	public String saveMaintain() throws Exception{
  		
  		String vehicleId = maintain.getVehicleId();
  		Vehicle vehicleJudge = vehicleService.getVehicleId(vehicleId);
  		if(!(vehicleJudge.getPlateId().equals(maintain.getPlateId()))) {
  			 throw new RuntimeException("信息录入失败！档案中的车牌号与录入的车牌号不符");
  		}
  		if(!(vehicleJudge.getUserName().equals(maintain.getUserName()))) {
  			throw new RuntimeException("信息录入失败！档案中的车主与录入的车主信息不符不符");
  		}
  		Integer userId = vehicleJudge.getUserId();
		maintain.setJudge("Y");
  		maintain.setUserId(userId);
  		maintain.setDate(new Date());
  		maintainService.saveMaintain(maintain);
  		
  		return "toMaintainList";
  	}
  	
    //对维护信息进行查询
  	public String maintainList() throws Exception {
        
  	    //封装离线查询对象
  		DetachedCriteria dc=DetachedCriteria.forClass(Vehicle.class);
  	    
  		dc.add(Restrictions.eq("judge", "Y"));
  		
  	    //判断并封装参数
  		/*if(StringUtils.isNotBlank(maintain.getPlateId())) {
  			dc.add(Restrictions.like("plateId",maintain.getPlateId()));
  		}
  		
  		if(StringUtils.isNotBlank(maintain.getVehicleId())) {
  			dc.add(Restrictions.like("vehicleId",maintain.getVehicleId()));
  		}
  		if(StringUtils.isNotBlank(maintain.getUserName())) {
  			dc.add(Restrictions.like("userName",maintain.getUserName()));
  		}*/
  		
  		//调用service 查询分页数据pagebean
  		PageBean pb=maintainService.getPageBean(dc,currentPage, pageSize);
  		
  		//将pagebean放到request域中，转发到页面显示
  		ActionContext.getContext().getSession().put("pageBean", pb);
  	  
  		return "maintainList";
  		
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

	public void setMaintainService(MaintainService maintainService) {
		this.maintainService = maintainService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Maintain getMaintain() {
		return maintain;
	}

	public void setMaintain(Maintain maintain) {
		this.maintain = maintain;
	}
    
	
	
}
