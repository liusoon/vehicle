package com.future.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.future.domain.BaseDict;
import com.future.domain.User;
import com.future.domain.Vehicle;
import com.future.service.UserService;
import com.future.service.VehicleService;
import com.future.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Package com.future.web.action
 * 
 * @Title: VehicleAction.java 
 *
 * @author: 孤城落寞  
 *
 * @date 2017年8月19日 下午10:29:56
 * 
 * @Description:  对车辆进行增删改查
 *   
 */

public class VehicleAction implements ModelDriven<Vehicle>  {

	private  Vehicle vehicle=new Vehicle();
	private  BaseDict baseDict=new BaseDict();
	private  User user=new User();

	private  VehicleService vehicleService;
	private  UserService userService;
	
	// 当前页数
	private Integer currentPage;
	// 每页显示数据的条数
	private Integer pageSize;
	
	
	//session中获取User 
	Map session = ActionContext.getContext().getSession();
  	User UserBySession = (User) session.get("ordinary");
	
  	
    //跳转到添加车辆备案界面
    public String addVehicle() throws Exception {
		return "addVehicle";
	}
  	
	//新建车辆备案
	public String saveVehicleByAdmin() throws Exception {
	   
		BaseDict baseDict1=new BaseDict();  
		//先判断车牌号是否存在
		vehicleService.getVehicleJudge(vehicle);
	   
		User userByCode = userService.getUserByCode(user);
		
		//获取id
		String id=vehicleService.getVehicleId(); 
	    //赋值
		vehicle.setVehicleId(id);
		vehicle.setUserId(userByCode.getUserId());
		vehicle.setUserName(userByCode.getName());
	    baseDict.setDict_id("9");
	    vehicle.setOperationStatus(baseDict);
	    baseDict1.setDict_id("12");
	    vehicle.setJudge(baseDict1);    
	    vehicle.setDate(getVehicleMaturityTime()); 	   
	    //执行保存操作
	    vehicleService.saveVehicle(vehicle);
	    //执行更新操作
	    userService.updateUserVehicle(userByCode.getUserId());
	    //进行页面跳转
	    return "toVehicleList";
		
	}
	
	//提交车辆信息
	public String saveVehicleByOrdinary() throws Exception{
		//先判断车牌号是否存在
		vehicleService.getVehicleJudge(vehicle);
	    //获取id
		String id=vehicleService.getVehicleId();
		
	  	//赋值
		vehicle.setVehicleId(id);
	    vehicle.setUserId(UserBySession.getUserId());
	    vehicle.setUserName(UserBySession.getName());
	    baseDict.setDict_id("10");
	    vehicle.setOperationStatus(baseDict);
	    //执行保存操作
	    vehicleService.saveVehicle(vehicle);
	    //进行页面跳转
	    return "toVehicleList";
		}  
	
	
    //需要备案的车辆列表
	public String addVehicleList() throws Exception {

		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Vehicle.class);

		dc.add(Restrictions.like("operationStatus.dict_id", "10", MatchMode.ANYWHERE));

		// 判断并封装参数
		if (StringUtils.isNotBlank(vehicle.getPlateId())) {
			dc.add(Restrictions.like("plateId", "%" + vehicle.getPlateId() + "%"));
		}

		// 调用service 查询分页数据pagebean
		PageBean pb = vehicleService.getPageBean(dc, currentPage, pageSize);

		// 将pagebean放到request域中，转发到页面显示
		ActionContext.getContext().getSession().put("pageBean", pb);

		return "addVehicleList";

	}

	// 车辆列表
	public String vehicleList() throws Exception {

		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Vehicle.class);
        
		//多身份查询条件变化
		if(UserBySession!=null ) {
            dc.add(Restrictions.eq("userId",UserBySession.getUserId()));
        }else {        	
        	dc.add(Restrictions.like("judge.dict_id", "12", MatchMode.ANYWHERE));
        	dc.add(Restrictions.like("operationStatus.dict_id", "9", MatchMode.ANYWHERE));
        }

		// 判断并封装参数
		if (StringUtils.isNotBlank(vehicle.getPlateId())) {
			dc.add(Restrictions.like("plateId", "%" + vehicle.getPlateId() + "%"));
		}

		// 调用service 查询分页数据pagebean
		PageBean pb = vehicleService.getPageBean(dc, currentPage, pageSize);

		// 将pagebean放到request域中，转发到页面显示
		ActionContext.getContext().getSession().put("pageBean", pb);

		return "vehicleList";

	}
	
	// 到期车辆查询
	public String maturityVehicleList() throws Exception {
		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Vehicle.class);
		
		// 对时间进行进行操作
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, +120);
		Date endDate = sdf.parse(sdf.format(c.getTime()));
		dc.add(Restrictions.lt("date", endDate));
      		
		// 判断并封装参数
		if (StringUtils.isNotBlank(vehicle.getPlateId())) {
			dc.add(Restrictions.like("plateId", "%" + vehicle.getPlateId() + "%"));
		}
		if (StringUtils.isNotBlank(vehicle.getVehicleId())) {
			dc.add(Restrictions.like("vehicleId", vehicle.getVehicleId()));
		}
 
        //调用service 查询分页数据pagebean
     	PageBean pb = vehicleService.getPageBean(dc, currentPage, pageSize);

     	// 将pagebean放到request域中，转发到页面显示
     	ActionContext.getContext().getSession().put("pageBean", pb);

     	return "maturityVehicleList";
	}
	
	
	public Date getVehicleMaturityTime() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, +120);
		
		return sdf.parse(sdf.format(c.getTime()));
	   
	} 
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Vehicle getModel() {

		return vehicle;
	}
    
}
