package com.future.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.future.dao.InformDao;
import com.future.domain.Inform;
import com.future.domain.User;
import com.future.service.InformService;
import com.future.utils.PageBean;

/**
 * @author anlijie
 *
 */
@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=false,propagation=Propagation.REQUIRED)
public class InformServiceImpl implements InformService{

	private InformDao informDao;

	public void setInformDao(InformDao informDao) {
		this.informDao = informDao;
	}

	@Override
	public void save(Inform inform) {
		informDao.save(inform);
	}
	
	@Override
	public List<Inform> getAll() {
		List<Inform> inform=informDao.getAllInform();
		return inform;
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,readOnly=true,propagation=Propagation.REQUIRED)
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1.调用dao查询总记录
	       
        Integer totalCount=informDao.getTotalCount(dc);
        
		//2.创建pageBean对象
		PageBean pb=new PageBean(currentPage,totalCount,pageSize);
        
		//3.调用dao查询分页列表数据
		List<Inform> list=informDao.getPageList(dc, pb.getStart(), pb.getPageSize());
		
		pb.setList(list);
		
		return pb;
	}

	@Override
	public void delete(Inform inform) {
		
		informDao.delete(inform);
	}
}
