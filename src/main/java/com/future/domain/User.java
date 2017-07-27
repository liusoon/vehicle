package com.future.domain;

import java.util.Date;

/**
 * @ProjectName vehicle 
 * 
 * @ClassName User
 *     
 * @author   孤城落寞  
 * 
 * @DateTime 2017年7月26日 下午4:00:57          
 */
public class User {
    
	private Integer id;
	
	private String code;
	private String password;
	private String name;
	private String status;
	private Date   date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", code=" + code + ", password=" + password + ", name=" + name + ", status=" + status
				+ ", date=" + date + "]";
	}
	
}
