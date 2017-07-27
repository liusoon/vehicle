package com.future.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.future.dao.UserDao;
import com.future.domain.User;


/**
 * @Package com.future.dao.impl
 * 
 * @Title: UserDaoImpl.java 
 *
 * @author: 孤城落寞  
 *
 * @date 2017年7月27日 上午9:39:30
 * 
 * @Description:  
 *   
 */
public class UserDaoImpl  extends HibernateDaoSupport  implements UserDao {


	public User getUserByCode(String code) {
       //criteria
		DetachedCriteria dc=DetachedCriteria.forClass(User.class);
		
	    dc.add(Restrictions.eq("code", code));
	    
	    List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		
	    if(list !=null && list.size()>0) {
	    	return list.get(0);
	    }else {
	    	return null;
	    }
		
	}

	public void save(User u) {
    
		getHibernateTemplate().save(u);         		
	
	}

}
