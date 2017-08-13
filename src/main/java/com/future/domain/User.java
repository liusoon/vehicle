package com.future.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName vehicle
 * 
 * @ClassName User
 * 
 * @author 孤城落寞
 * 
 * @DateTime 2017年7月26日 下午4:00:57
 */
public class User {

	// id
	private Integer userId;
	// 账号
	private String code;
	// 密码
	private String password;
	// 用户名
	private String name;
	// 地址
	private String address;
	// 联系方法
	private String phone;
	// 注册日期
	private Date date;
	// 用户角色
	private String role;
	
	//车辆数目
	private Integer vehicleNumber;
	
	//信息数目
	private Integer maintainNumber;
	
	//假删除

	private String judge;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getVehicleNumber() {
		return vehicleNumber;
	}
	
	public void setVehicleNumber(Integer vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getMaintainNumber() {
		return maintainNumber;
	}
	public void setMaintainNumber(Integer maintainNumber) {
		this.maintainNumber = maintainNumber;
	}
	
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", code=" + code + ", password=" + password + ", name=" + name + ", address="
				+ address + ", phone=" + phone + ", date=" + date + ", role=" + role + ", vehicleNumber="
				+ vehicleNumber + ", maintainNumber=" + maintainNumber + ", judge=" + judge + "]";
	}
	
	
	
 
}
