package com.future.domain;

import java.util.Date;

public class Inform {
	private int informId;
	
	private String content;//通知内容
	
	private Date createDate;//发布时间
	
	private StringBuffer urls;
	
	private String role;
	
	private String name;
	

	


	public Inform(String content, Date createDate,String role,String name) {
		super();
		this.content = content;
		this.createDate = createDate;
		this.role = role;
		this.name=name;
	}
	
	
	
	public Inform(String content, Date createDate, StringBuffer urls, String role,String name) {
		super();
		this.content = content;
		this.createDate = createDate;
		this.urls = urls;
		this.role = role;
		this.name=name;
	}



	public Inform() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public int getInformId() {
		return informId;
	}

	public void setInformId(int informId) {
		this.informId = informId;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public StringBuffer getUrls() {
		return urls;
	}

	public void setUrls(StringBuffer urls) {
		this.urls = urls;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
