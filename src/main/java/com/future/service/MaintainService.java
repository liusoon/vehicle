package com.future.service;

import org.hibernate.criterion.DetachedCriteria;

import com.future.domain.Maintain;
import com.future.utils.PageBean;

/**
 * @Package com.future.service
 * 
 * @Title: MaintainService.java 
 *
 * @author: 孤城落寞  
 *
 * @date 2017年8月12日 下午8:11:28
 * 
 * @Description:  
 *   
 */
public interface MaintainService {
   
	//录入维护信息
	void  saveMaintain(Maintain maintain);
	
	//分页方法
	PageBean getPageBean(DetachedCriteria dc,Integer currentPage,Integer pageSize);
}
