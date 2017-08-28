package com.future.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.future.domain.Inform;
import com.future.utils.PageBean;

/**
 * @author anlijie
 * 
 * 通知信息
 */
public interface InformService {
	//储存通知信息
	public void save(Inform inform);
	//分页查询
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//查询所有通知消息
	List<Inform> getAll();
	//删除通知信息
	public void delete(Inform inform);

}
