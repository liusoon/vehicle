<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆维护管理系统-车辆列表</title>
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<script type="text/javascript">
  function changePage(pageNum){
	  //1.将页码的值放入对应的表单隐藏域中
	  $("#currentPageInput").val(pageNum);  
	  //2.提交表单
	  $("#pageForm").submit();
  };
  
  function changePageSize(pageSize){
	   //1 将页码的值放入对应表单隐藏域中
	   $("#pageSizeInput").val(pageSize);
	   //2 提交表单
	   $("#pageForm").submit();
	};
 
	
	
</script>
</head>
<body>
  <form id="pageForm" name="ordinaryForm" action="${pageContext.request.contextPath}/AdminAction_vehicleList" method="post">
        
                     车牌号:
        <input type="text" name="vehicle.plateId" id="plateId"/>
       
       <br/>
       <input type="submit" class="button" value="筛选" name="button"/>
        
            
       	<!-- 隐藏域.当前页码 -->
		<input type="hidden" name="currentPage" id="currentPageInput" value="${pageBean.currentPage}" />
		
		<!-- 隐藏域.每页显示条数 -->
        <input type="hidden" name="pageSize" id="pageSizeInput"   value="${pageBean.pageSize}" />
        
     </form> 
        <!-- 分页查询 -->
       <div>
        <table>
          <tbody>
	          <tr>
	            <th>档案号</th>
	            <th>车牌号</th>
	            <th>车的类别</th>
	            <th>车辆型号</th>
	            <th>发动机编号</th>
	            <th>车底盘号衍射</th>
	            <th>重量</th>
	            <th>车辆状态</th>
	            <th>出厂日期</th>
	            <th>车主 </th>
	            <th>操作</th>
	          </tr>  
	          
	          <c:forEach items="${pageBean.list}" var="list" >
	          <tr>
	            <td>${list.vehicleId}</td>
	            <td>${list.plateId}</td>
	            <td>${list.category}</td>
	            <td>${list.model}</td>
	            <td>${list.engineId}</td>
	            <td>${list.carChassisId}</td>
	            <td>${list.weight}</td>
	            <td>${list.operationStatus}</td>
	            <td>${list.manufactureDate}</td>
	            <td>${list.userName}</td>
	            <td>
                  <a href="${pageContext.request.contextPath}/adminServlet?method=edit&vehicleId=${Vehicle.vehicleId}">修改</a>
                  &nbsp;&nbsp;
                  <a href="${pageContext.request.contextPath}/adminServlet?method=delete&vehicleId=${Vehicle.vehicleId}">删除</a>
	            </td>
	          </tr>
              </c:forEach>
	         
          </tbody>
      </table>
      </div>
   <!--分页下部  -->
        <div>
	                        共[<b>${pageBean.totalCount}</b>]条记录,[<b>${pageBean.totalPage}</b>]页
			 ,每页显示 
			 <select name="pageSize" onchange="changePageSize(this.options[this.options.selectedIndex].value)"  id="pageSizeSelect" >
				<option  value="3"  ${pageBean.pageSize==3?'selected':''}>3</option>
				<option  value="5" ${pageBean.pageSize==5?'selected':''}>5</option>
			 </select> 
			  条
				[<a href="javaScript:void(0)" onclick="changePage(${pageBean.currentPage-1})" >前一页</a>]
				<b>${pageBean.currentPage}</b>
				[<a href="javaScript:void(0)" onclick="changePage(${pageBean.currentPage+1})" >后一页</a>] 
			到
			<input type="text" size="3" id="page" name="page" value="${pageBean.currentPage}"/>
			页
			
			<input type="button" value="Go" onclick="changePage($('#page').val())"/>
	       <s:debug/>
        </div>
</body>
</html>