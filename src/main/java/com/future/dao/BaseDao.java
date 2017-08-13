package com.future.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.future.domain.User;

/**
 * @Package com.future.dao
 * 
 * @Title: BaseDao.java 
 *
 * @author: 孤城落寞  
 *
 * @date 2017年7月31日 下午9:05:45
 * 
 * @Description:  
 *   
 */
public interface BaseDao<T> {
  
	//增
	void save(T t);
	
	//删
	void delete(T t);
	
	//删  用户序列化的id修改
	void delete(Serializable id);
	
	//改
	void update(T t);

    //查  根据ID查询
    T  getById(Serializable id);
	
    //查  符合条件的总记录
    Integer getTotalCount(DetachedCriteria dc);
    
	//查  查询分页列表数据
    List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize); 
    
	
  	/***
	 * 
	 * 目前我光写了常用的几个借口，如果过还需要的话，找那些公共接口写在这里
	 */
    
} 
