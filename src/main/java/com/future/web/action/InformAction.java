package com.future.web.action;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.future.domain.Inform;
import com.future.service.InformService;
import com.future.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InformAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Inform inform=new Inform();
	
	//当前页数
	private Integer currentPage;
	// 每页显示数据的条数
	private Integer pageSize;	
		
	
	private InformService informService;
	
	private int sign;
		
		// 对通知信息的分页查询
		public String informList() throws Exception {
			// 封装离线查询对象
			DetachedCriteria dc = DetachedCriteria.forClass(Inform.class);
			if(sign==1){
				dc.add(Restrictions.eq("role", "administrator"));
			}else if(sign==2){
				dc.add(Restrictions.eq("role", "admin"));
			}else if(sign==3){
				dc.add(Restrictions.eq("role", "ordinary"));
			}
				
			// 调用service 查询分页数据pagebean
			PageBean pb = informService.getPageBean(dc, currentPage, pageSize);

			// 将pagebean放到request域中，转发到页面显示
			ActionContext.getContext().getSession().put("pageBean", pb);
			return "informList";

		}

		public Integer getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}

		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

		public void setInformService(InformService informService) {
			this.informService = informService;
		}

		public Inform getInform() {
			return inform;
		}

		public void setInform(Inform inform) {
			this.inform = inform;
		}

		public int getSign() {
			return sign;
		}

		public void setSign(int sign) {
			this.sign = sign;
		}
		
		
		
	
}
