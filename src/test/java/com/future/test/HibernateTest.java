package com.future.test;


import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.future.dao.UserDao;
import com.future.domain.User;
import com.future.service.UserService;

/**
 * @Package com.future.test
 * @Title: HibernateTest.java 
 * @author: 孤城落寞  
 * @date 2017年7月27日 上午9:34:07
 * @Description: hibernate和框架整合 测试
 *   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {

	@Test
	//单独测试hibernate
	public void fun() {
	
	Configuration conf=new Configuration().configure();
	SessionFactory sf=conf.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx=session.beginTransaction();
	 
	 User u=new User();	
	  
	   u.setCode("12345");
	   u.setPassword("12345");
	   u.setName("tom");
       u.setPhone(123456789);
	   u.setAddress("hky");
	   u.setDate(new Date());
	   
	   session.save(u);
	   tx.commit();
	   session.close();
	   sf.close();

	}
	
    @Resource(name="sessionFactory")
	private SessionFactory sf;
   
    @Test
    //测试spring管理sessionFactory
	public void fun2() {
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
	    
		User u=new User();	
		   u.setCode("123456");
		   u.setPassword("123456");
		   u.setName("jock");
	       u.setPhone(123456789);
		   u.setAddress("hsd");
		   u.setDate(new Date());
		session.save(u);
		
		tx.commit();
		session.close();
		
	}
   
    @Resource(name="userDao")
    private UserDao ud;
    @Test
    //测试hibernateTemple模板
    public void fun3() {
//    	User user = ud.getUserByCode("123456");
//        System.out.println(user);
    } 

    
    @Resource(name="userService")
    private UserService us; 
    @Test
    //测试aop事务
    public void fun4() {
    	
    	User u=new User();	
    	u.setCode("12");
	    u.setPassword("12");
	    u.setName("tom");
        u.setPhone(123456789);
	    u.setAddress("xxxy");
	    u.setDate(new Date());
        us.saveUser(u);
    } 
	
}
