package com.future.web.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
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
public class AdminAction extends ActionSupport {

	// 实体类对象
	private User user = new User();
	private Vehicle vehicle = new Vehicle();
	private Maintain maintain = new Maintain();
	private BaseDict baseDict = new BaseDict();

	// service
	private MaintainService maintainService;
	private UserService userService;
	private VehicleService vehicleService;

	// 当前页数
	private Integer currentPage;
	// 每页显示数据的条数
	private Integer pageSize;
	// 前台传进起始日期
	private String beginDateString;
	// 前台传进截止日期
	private String endDateString;

	// 类型装换
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Date beginDate;
	private Date endDate;
	
	// 跳转到添加用户界面
	public String addUser() throws Exception {
		return "addUser";
	}

	// 进行保存用户操作
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

	// 进行车辆备案
	/**
	 * 在进行批量修改后再调用一下userService.updateUserVehicle(Integer userId);
	 * 
	 * 
	 * 
	 * 
	 */
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

	// 对车辆进行查询
	public String vehicleList() throws Exception {

		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Vehicle.class);

		dc.add(Restrictions.like("judge.dict_id", "12", MatchMode.ANYWHERE));
		dc.add(Restrictions.like("operationStatus.dict_id", "9", MatchMode.ANYWHERE));

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
		DetachedCriteria dc = DetachedCriteria.forClass(Maintain.class);
		Date date = new Date();

		// 对时间进行进行操作
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, +20);
		Date endDate = sdf.parse(sdf.format(c.getTime()));

		// 判断并封装参数
		if (StringUtils.isNotBlank(vehicle.getPlateId())) {
			dc.add(Restrictions.like("plateId", "%" + vehicle.getPlateId() + "%"));
		}

		// 判断并封装参数
		if (StringUtils.isNotBlank(vehicle.getVehicleId())) {
			dc.add(Restrictions.like("vehicleId", vehicle.getVehicleId()));
		}

		dc.add(Restrictions.lt("date", endDate));

		// 调用service 查询分页数据pagebean
		PageBean pb = vehicleService.getPageBean(dc, currentPage, pageSize);

		// 将pagebean放到request域中，转发到页面显示
		ActionContext.getContext().getSession().put("pageBean", pb);

		return "maturityVehicleList";
	}

	// 对维护信息录入
	public String addMaintain() throws Exception {
		return "addMaintain";
	}

	public String saveMaintain() throws Exception {

		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Vehicle.class);

		// 获取车辆档案号
		String vehicleId = maintain.getVehicleId();
		// 验证车辆
		Vehicle vehicleJudge = vehicleService.getVehicleId(vehicleId);

		if (!(vehicleJudge.getPlateId().equals(maintain.getPlateId()))) {
			throw new RuntimeException("信息录入失败！档案中的车牌号与录入的车牌号不符");
		}

		if (!(vehicleJudge.getUserName().equals(maintain.getUserName()))) {
			throw new RuntimeException("信息录入失败！档案中的车主与录入的车主信息不符不符");
		}

		if ((dc.add(Restrictions.like("operationStatus.dict_id", "9")) == null)) {
			throw new RuntimeException("信息录入失败！该车辆未备案");
		}

		// 获取车主id
		Integer userId = vehicleJudge.getUserId();

		// 获取车主对象
		User u = userService.getUserById(userId);
		baseDict.setDict_id("12");
		maintain.setJudge(baseDict);

		// 设置车辆状态和车辆类
		BaseDict category = vehicleJudge.getCategory();
		BaseDict operationStatus = vehicleJudge.getOperationStatus();
		maintain.setCategory(category);
		maintain.setOperationStatus(operationStatus);
		// 后台添加属性
		maintain.setUserPhone(u.getPhone());
		maintain.setUserId(userId);

		// 在录入信息是直接吧时间改成到期时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, +120);
		Date endDate = sdf.parse(sdf.format(c.getTime()));
		maintain.setDate(endDate);
		// 执行保存操作
		maintainService.saveMaintain(maintain);

		// 执行更新user表
		userService.updateUserMaintain(userId);
		return "toMaintainList";
	}

	// 对维护信息进行查询
	public String maintainList() throws Exception {

		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Maintain.class);

		dc.add(Restrictions.like("judge.dict_id", "12", MatchMode.ANYWHERE));

		// 判断并封装参数
		if (StringUtils.isNotBlank(maintain.getPlateId())) {
			dc.add(Restrictions.like("plateId", "%"+maintain.getPlateId()+"%"));
		}

		if (StringUtils.isNotBlank(maintain.getVehicleId())) {
			dc.add(Restrictions.like("vehicleId","%"+maintain.getVehicleId()+"%"));
		}
		if (StringUtils.isNotBlank(maintain.getUserName())) {
			dc.add(Restrictions.like("userName","%"+maintain.getUserName()+"%"));
		}

		// 起始日期和截止日期都不为空
		if (StringUtils.isNotBlank(beginDateString) && StringUtils.isNotBlank(endDateString)) {
			// 获取时间
			beginDate = sdf.parse(beginDateString);
			endDate = sdf.parse(endDateString);
			getMaintainDate();
		}

		// 起始日期不为空 截止日期为空
		if (StringUtils.isNotBlank(beginDateString) && StringUtils.isBlank(endDateString)) {
			// 获取时间
			beginDate = sdf.parse(beginDateString);
			endDate = new Date();
			getMaintainDate();
		}

		// 起始日期为空 截止日期不为空
		if (StringUtils.isBlank(beginDateString) && StringUtils.isNotBlank(endDateString)) {
			// 获取时间
			endDate = sdf.parse(endDateString);
			beginDate = maintainService.getMaintainDateById();
			getMaintainDate();
		}

		// 调用service 查询分页数据pagebean
		PageBean pb = maintainService.getPageBean(dc, currentPage, pageSize);

		// 将pagebean放到request域中，转发到页面显示
		ActionContext.getContext().getSession().put("pageBean", pb);

		return "maintainList";

	}

	// 车辆类型判断
	private void getMaintainDate() throws Exception {

		DetachedCriteria dc = DetachedCriteria.forClass(Maintain.class);

		// 判断是不是货车
		if (dc.add(Restrictions.like("category.dict_id", "5")) != null) {
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -120);
			endDate = sdf.parse(sdf.format(c.getTime()));
		}

		// 判断他是不是客车
		if (dc.add(Restrictions.like("category.dict_id", "1")) != null) {
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -90);
			endDate = sdf.parse(sdf.format(c.getTime()));
		}

		dc.add(Restrictions.between("date", beginDate, endDate)).addOrder(Order.desc("date"));
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

	public String getBeginDateString() {
		return beginDateString;
	}

	public void setBeginDateString(String beginDateString) {
		this.beginDateString = beginDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	/*
	 * public Date getBeginDate() { return beginDate; }
	 * 
	 * public void setBeginDate(Date beginDate) { this.beginDate = beginDate; }
	 * 
	 * public Date getEndDate() { return endDate; }
	 * 
	 * public void setEndDate(Date endDate) { this.endDate = endDate; }
	 */

}
