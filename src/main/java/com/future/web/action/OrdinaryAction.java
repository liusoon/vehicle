package com.future.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.future.domain.BaseDict;
import com.future.domain.User;
import com.future.domain.Vehicle;

import com.future.service.VehicleService;
import com.future.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Package com.future.web.action
 * 
 * @Title: OrdinaryAction.java 
 *
 * @author: 孤城落寞  
 *
 * @date 2017年8月9日 下午2:44:21
 * 
 * @Description: 普通用户  
 *   
 */
public class OrdinaryAction extends ActionSupport implements  ModelDriven<Vehicle>{ 
	
	
	private  Vehicle vehicle=new Vehicle();
    private	 BaseDict baseDice=new BaseDict();
	private  VehicleService vehicleService; 
	//当前页数
  	private  Integer currentPage;
	//每页显示数据的条数
  	private  Integer pageSize;
  		
  	ActionContext actionContext = ActionContext.getContext();
  	Map session = actionContext.getSession();
  	User user = (User) session.get("ordinary");
  	String userName=user.getName();   
  	Integer userId=user.getUserId();
  	
  	
	//填写车辆信息
	public String add() throws Exception {
	   return "add";
	}
   
	//提交车辆信息
	public String save() throws Exception{
		//先判断车牌号是否存在
		vehicleService.getVehicleJudge(vehicle);
	    //获取id
		String id=vehicleService.getVehicleId();
		
	    //赋值
		vehicle.setVehicleId(id);
	    vehicle.setUserId(userId);
	    vehicle.setUserName(userName);
	    baseDice.setDict_id("10");
	    vehicle.setOperationStatus(baseDice);
	    //执行保存操作
	    vehicleService.saveVehicle(vehicle);
	    //进行页面跳转
	    return "toList";
	}         
	//车辆列表
  	public String list() throws Exception {  
  		
  		//封装离线查询对象
  		DetachedCriteria dc=DetachedCriteria.forClass(Vehicle.class);
  	    
  		dc.add(Restrictions.eq("userName", userName));
  		
  		//判断并封装参数
  		if(StringUtils.isNotBlank(vehicle.getPlateId())) {
  			dc.add(Restrictions.like("plateId", "%"+vehicle.getPlateId()+"%"));
  		}
  		
  		//调用service 查询分页数据pagebean
  		PageBean pb=vehicleService.getPageBean(dc,currentPage, pageSize);
  		
  		//将pagebean放到request域中，转发到页面显示
  		ActionContext.getContext().getSession().put("pageBean", pb);
  	  
  		return "list";
  	}

	

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public Vehicle getModel() {
		return vehicle;
	}
	
	
  	
}
