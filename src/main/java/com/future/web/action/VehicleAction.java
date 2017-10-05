package com.future.web.action;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
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

public class VehicleAction extends BaseData implements ModelDriven<Vehicle>  {

	private  Vehicle vehicle=new Vehicle();
	private  BaseDict baseDict=new BaseDict();
	private  User user=new User();

	private  VehicleService vehicleService;
	private  UserService userService;
	private MaintainService maintainService;
	
	private String many;
	private  Vehicle vehicle1;
	private Vehicle vehicleId;
	private int id;
	private int sign;
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
		Vehicle judgeV = vehicleService.getVehicleJudge(vehicle);
		//判断是否存在
		if(judgeV !=null) {
			ActionContext.getContext().getSession().put("vehicleMessage", "车辆添加失败，存在该车辆 ！");
		}else{
		    ActionContext.getContext().getSession().put("vehicleMessage", "车辆添加成功");
		}
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
	
	//查询单个用户车辆信息
  	public String selectVehicle() throws Exception{
		Vehicle vehicle1=vehicleService.getVehicleId(vehicle.getVehicleId());
		ActionContext.getContext().getSession().put("vehicle1",vehicle1);
		if(sign==1){
			return "findVehicle";
		}
		return "selectVehicle";
	}
  	
    //修改车辆信息
  	public String updateVehicle() throws Exception{
  		Vehicle v=vehicleService.getVehicleId(vehicle.getVehicleId());
  		v.setCarChassisId(vehicle1.getCarChassisId());
  		v.setDate(vehicle1.getDate());
  		v.setEngineId(vehicle1.getEngineId());
  		v.setMaintainNumber(vehicle1.getMaintainNumber());
  		v.setManufactureDate(vehicle1.getManufactureDate());
  		v.setModel(vehicle1.getModel());
  		v.setWeight(vehicle1.getWeight());
  		v.setPlateId(vehicle1.getPlateId());
  	    v.setCategory(vehicle1.getCategory());
  		vehicle=v;
  		vehicleService.updateVehicle(vehicle);
  		ActionContext.getContext().getSession().put("vehicleMessage", "车辆修改成功");
		if(sign==1) {
  			return "toaddVehicleList";
  		}
  		return "toVehicleList";
  	}
	
  	//删除车辆信息
  	public String deleteVehicle() throws Exception{
  		//对于车辆信息的删除
  		Vehicle vehicle1=vehicleService.getVehicleId(vehicle.getVehicleId());
  		baseDict.setDict_id("11");
  		vehicle1.setJudge(baseDict);
  		vehicle=vehicle1;
  		vehicleService.updateVehicle(vehicle);
  		//对于维护信息的删除
  		List<Maintain> m=maintainService.get();
  		for(Maintain maintain:m){
  			if(maintain.getUserId().equals(vehicle1.getUserId())){
  				maintain.setJudge(baseDict);
  				maintainService.updateMaintain(maintain);
  			}else{
  				break;
  			}
  		}
  		ActionContext.getContext().getSession().put("vehicleMessage", "车辆删除成功");
		if(sign==1) {
  			return "toaddVehicleList";
  		}
  		return "toVehicleList";
  	}
  
  	//一键备案
  	public void fileAll() throws Exception{
  		String s =many;
  		String[] re = s.split(" ");//用split()函数直接分割
        for (String vehicleId : re) {
            Vehicle vehicle=vehicleService.getVehicleId(vehicleId);
      		baseDict.setDict_id("9");   		
      		vehicle.setOperationStatus(baseDict);
	    		Date endDate = getVehicleMaturityTime();
	      		vehicle.setDate(endDate);
	      		vehicleService.updateVehicle(vehicle);
	      		this.getResponse().getWriter().println(1);
        }  
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
		dc.add(Restrictions.like("judge.dict_id", "12", MatchMode.ANYWHERE));
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
		   if(sign!=1){
			   dc.add(Restrictions.like("judge.dict_id", "12", MatchMode.ANYWHERE));
			   dc.add(Restrictions.like("operationStatus.dict_id", "9", MatchMode.ANYWHERE));
		   }else{
			   dc.add(Restrictions.like("judge.dict_id", "11", MatchMode.ANYWHERE));  
		   }
		}
		// 判断并封装参数
		if (StringUtils.isNotBlank(vehicle.getPlateId())) {
			dc.add(Restrictions.like("plateId", "%" + vehicle.getPlateId() + "%"));
		}
		// 调用service 查询分页数据pagebean
		PageBean pb = vehicleService.getPageBean(dc, currentPage, pageSize);

		// 将pagebean放到request域中，转发到页面显示
		ActionContext.getContext().getSession().put("pageBean", pb);
		ActionContext.getContext().getSession().put("sign",sign);
		return "vehicleList";

	}
	
	// 到期车辆查询
	public String maturityVehicleList() throws Exception {
		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Vehicle.class);
		
		// 对时间进行进行操作
		Date endDate = getVehicleMaturityTime();
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
		c.add(Calendar.DAY_OF_MONTH, +20);
		
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
	
	public void setMaintainService(MaintainService maintainService) {
		this.maintainService = maintainService;
	}
	
	public String getMany() {
		return many;
	}

	public void setMany(String many) {
		this.many = many;
	}

	@Override
	public Vehicle getModel() {

		return vehicle;
	}

	public Vehicle getVehicle1() {
		return vehicle1;
	}

	public void setVehicle1(Vehicle vehicle1) {
		this.vehicle1 = vehicle1;
	}

	

	public Vehicle getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Vehicle vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

}
