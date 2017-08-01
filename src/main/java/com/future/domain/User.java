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
	private Integer phone;
	// 备案日期
	private Date date;
    
	//车辆数目
	private Set<Vehicle> vehicle = new HashSet<>();
	private Set<Maintain> maintain = new HashSet<>();
	
	// 用户角色
    private Set<Role> role=new HashSet<>();
	
	

	public Set<Maintain> getMaintain() {
		return maintain;
	}

	public void setMaintain(Set<Maintain> maintain) {
		this.maintain = maintain;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public Set<Role> getRole() {
		return role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Set<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Set<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", code=" + code + ", password=" + password + ", name=" + name + ", address="
				+ address + ", phone=" + phone + ", date=" + date + ", vehicle=" + vehicle + ", maintain=" + maintain
				+ ", role=" + role + "]";
	}

}
