package com.future.test;


import java.util.Date;
import java.util.List;
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
    	BaseDict baseDict=new BaseDict();
    	User u=new User();	
    	u.setCode("1234");
	    u.setPassword("1234");
	    u.setName("jock"); 
        u.setPhone("123456789");
	    u.setAddress("hky");
	    u.setRole("ordinary");
	    u.setDate(new Date());
	    baseDict.setDict_id("12");
	    u.setJudge(baseDict);
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
		    String vehicleId = "00001";
	  		
		    Vehicle vehicleJudge = vs.getVehicleId(vehicleId);
		   
		    
		    if(!(vehicleJudge.getPlateId().equals("12321"))) {
	  			 throw new RuntimeException("信息录入失败！档案中的车牌号与录入的车牌号不符");
	  		}
	  		
		    if(!(vehicleJudge.getUserName().equals("AA"))) {
	  			throw new RuntimeException("信息录入失败！档案中的车主与录入的车主信息不符不符");
	  		}
	  		
		    Integer userId = vehicleJudge.getUserId();
	  		/*maintain.setJudge("Y");*/
	  		maintain.setPlateId("12321");
	  		maintain.setVehicleId(vehicleId);
	  		maintain.setUserId(userId);
	  		maintain.setDate(new Date());
	  		us.updateUserMaintain(userId);
	  		ms.saveMaintain(maintain);
	  } 
	  @Test
	  //测试user信息变化
	  public void fun9() {
		 User user = ud.getById(1);		
		 System.out.println(user);
		 Integer maintainNumber= user.getMaintainNumber();
		 if(maintainNumber==null) {
			 maintainNumber=0;
		 }
		 System.out.println(maintainNumber);
		 maintainNumber++;
		 System.out.println(maintainNumber);
		 user.setMaintainNumber(maintainNumber);
		 System.out.println(user);
		 
		 ud.save(user);    
		  
		  
	  } 
	  
	  @Test
	  public void fun10() {
		 /*DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
		 dc.add(Restrictions.eq("dict_id", "10"));*/
		
		 
		  
//	    Vehicle vehicle=new Vehicle();
		  //封装离线查询对象
	    DetachedCriteria dc = DetachedCriteria.forClass(Vehicle.class); 
	    dc.add(Restrictions.like("operationStatus.dict_id", "10",MatchMode.ANYWHERE)); 
	    /* DetachedCriteria dc = DetachedCriteria.forClass(Student.class); 
	    dc.add(Restrictions.like("team.id", teamId, )); */
	    
	    /*List<Vehicle> list = (List<Vehicle>) getHibernateTemplate().findByCriteria(dc);
		
	    if(list !=null && list.size()>0) {
	    	return list.get(0);
	    }else {
	    	return null;
	    }*/
	    
	    
//  		DetachedCriteria dc2=dc.createAlias();
  		/*DetachedCriteria beautyCriteria = DetachedCriteria.forClass(Beauty.class, "b").;
  		DetachedCriteria customerCriteria = beautyCriteria.createAlias("customers", c");
  		beautyCriteria.add(Restrictions.le("b.age", new Long(20))):
  		customerCriteria.add(Restrictions.eq("c.name", "Gates")):	*/    
//  		dc2.add(Restrictions.eq("bd.dict_id", "10"));
//        dc.add(Restrictions.eq("dict.", "10"));
  		
  		//调用service 查询分页数据pagebean
  		PageBean pb=vs.getPageBean(dc,null,null);
  		
  		System.out.println(pb);
  		
	    
	  } 
	 
	  
 }