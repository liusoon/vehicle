package com.future.test;


import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.future.dao.UserDao;
import com.future.dao.VehicleDao;
import com.future.domain.BaseDict;
import com.future.domain.Maintain;
import com.future.domain.User;
import com.future.domain.Vehicle;

import com.future.service.MaintainService;

import com.future.service.UserService;
import com.future.service.VehicleService;
import com.future.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;

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
       u.setPhone("123456789");
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
	       u.setPhone("123456789");
		   u.setAddress("hsd");
		   u.setDate(new Date());
		session.save(u);
		
		tx.commit();
		session.close();
		//测试hibernateTemple模板
		
	}
   
    @Resource(name="userDao")
    private UserDao ud;
    @Resource(name="vehicleDao")
    private VehicleDao vd;
    @Test
    public void fun3() {
        Vehicle user = vd.getById("00001");
        //User user = ud.getById(1);
        
    	System.out.println(user);
    } 

    
    @Resource(name="userService")
    private UserService us; 
    @Test
    //测试aop事务
    public void fun4() {
    	User u=new User();	
    	u.setCode("12");
	    u.setPassword("12");
	    u.setName("AA"); 
        u.setPhone("123456789");
	    u.setAddress("xxxy");
	    u.setRole("admin");
	    u.setDate(new Date());
	    u.setJudge("Y");
        us.saveUser(u);
    } 
    
    
    @Test
    //测试分页查询
    public void fun5() {
        DetachedCriteria dc=DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("role","ordinary"));
        dc.add(Restrictions.eq("judge","Y"));
        
//      dc.createAlias("judge", "bd"); 
//      dc.add(Restrictions.like("bd.dict_id","12")); 
//      DetachedCriteria dc = DetachedCriteria.forClass(Student.class); 
//      dc.createAlias("team", "t"); 
//      dc.add(Restrictions.like("t.teamName", teamName, MatchMode.ANYWHERE)); 
        
//      dc.add(Restrictions.eq("role","ordinary")).createAlias("judge", "bd")
//      .add(Restrictions.eq("bd.dict_id","12"));
        //dc.add(Restrictions.eq("judge", "12"));
        //detachedCriteria.add(Restrictions.eq("name", "department"))
       /* DetachedCriteria.forClass(Beauty.class);
        beautyCriteria.add(Restrictions.eq("name", "Gates")):*/
        PageBean bean = us.getPageBean(dc, null,null);
        System.out.println(bean); 
    } 
  

	  @Test
	  //测试code不能重复
	  public void fun6() {
		
		User u=new User();	
		u.setCode("12345678");
		u.setPassword("1211");
		u.setName("jock"); 
		u.setRole("both");
		u.setPhone("123456789");
		u.setAddress("xxxy");
		
		User judge = us.getUserJudge(u);
		System.out.println(judge);
		u.setDate(new Date());
		System.out.println("init...");
		us.saveUser(u); 
	  } 
	  
	  @Resource(name="vehicleService")
	  private VehicleService vs;
	  //添加车辆信息测试
	  @Test
	  public void fun7() {
		/* PLATE_ID, MODEL, ENGINE_ID, CAR_CHASSIS_ID, MANU_FACTURE_DATE, WEIGHT, USERS_ID, CATEGORY, OPERATION_STATUS, VEHICLE_ID*/
		 Vehicle ve=new Vehicle(); 
		 ve.setWeight(12133.00);
		 ve.setManufactureDate(new Date());
		 ve.setPlateId("FF");
		 ve.setCarChassisId("123456");
		 ve.setEngineId("123456");
		 ve.setModel("123456");
		 String id=vs.getVehicleId();
	     ve.setVehicleId(id);
		 vs.saveVehicle(ve);
	  } 
	  
	  @Resource(name="maintainService")
	  private MaintainService ms;

	  @Test
	  //维护信息录入
	  public void fun8() {
		    Maintain maintain =new Maintain();
		    BaseDict baseDict=new BaseDict();		    
		    String vehicleId = "00001";
	  		
		    Vehicle vehicleJudge = vs.getVehicleId(vehicleId);
		    System.out.println(vehicleJudge);
		    
//		    String plateId = ;	  		
//		    System.out.println(vehicleJudge.getPlateId());
//		    12121
//		    System.out.println(!(vehicleJudge.getPlateId().equals("12121")));
		    
		    if(!(vehicleJudge.getPlateId().equals("12121"))) {
	  			 throw new RuntimeException("信息录入失败！档案中的车牌号与录入的车牌号不符");
	  		}
	  		
		    if(!(vehicleJudge.getUserName().equals("rose"))) {
	  			throw new RuntimeException("信息录入失败！档案中的车主与录入的车主信息不符不符");
	  		}
	  		
		    Integer userId = vehicleJudge.getUserId();
	  		maintain.setJudge("Y");
	  		maintain.setPlateId("12121");
	  		maintain.setVehicleId(vehicleId);
	  		maintain.setUserId(userId);
	  		maintain.setDate(new Date());
	  		ms.saveMaintain(maintain);
	  } 
	  @Test
	  //维护信息录入
	  public void fun9() {
		     
		  String vehicleId = "00001";
		  
		  Vehicle vehicleJudge = vs.getVehicleId(vehicleId);
		  
	  } 
	  
	  
 }