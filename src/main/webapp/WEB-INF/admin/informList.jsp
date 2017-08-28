<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知中心</title>
<link rel="stylesheet" href="${path}css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${path}css/basic.css">
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
<style type="text/css">
	.ji{
		width:400px;
		height:35px;
		float:left;
		font-size:20px;
		line-height:35px;
		margin-top:100px;
		margin-left:220px;
	}
	#pge{
	width:400px;
	float:right;
	margin-top:-60px;
	margin-right:400px;
	}
</style>
</head>
<body>  
<form id="pageForm"  action="${pageContext.request.contextPath}/admin/InformAction_informList" method="post">
    	<!-- 隐藏域.当前页码 -->
		<input type="hidden" name="currentPage" id="currentPageInput" value="${pageBean.currentPage}" />
		<!-- 隐藏域.每页显示条数 -->
        <input type="hidden" name="pageSize" id="pageSizeInput"   value="${pageBean.pageSize}" />
</form>
<div class="tab">
        <table border="4">
          <tbody>
          	 <tr>
          	 	<th>通知时间</th>
          	 	<th>通知内容</th>
          	 	<th></th>
          	 </tr>
	         <c:forEach items="${pageBean.list}" var="list" >
	          <tr>
	            <td>${list.createDate}</td>
	            <td><a href="${list.urls}">${list.content}</a></td>
	          </tr>
              </c:forEach> 
          </tbody>
      </table>
      </div>
   <!--分页下部  -->
        <div class="ji">
        共[<b>${pageBean.totalCount}</b>]条记录,[<b>${pageBean.totalPage}</b>]页
			 ,每页显示 
			 <select name="pageSize" onchange="changePageSize(this.options[this.options.selectedIndex].value)"  id="pageSizeSelect" >
				<option  value="3"  ${pageBean.pageSize==3?'selected':''}>3</option>
				<option  value="5" ${pageBean.pageSize==5?'selected':''}>5</option>
			 </select>
			 条
     </div>
     <div class="col-lg-5 col-lg-offset-5 col-md-6 col-md-offset-4 col-xs-6 col-xs-offset-4" id="pge">
           <nav>
          <ul class="pagination">
             
            <li><a href="javaScript:void(0)" onclick="changePage(${pageBean.currentPage-1})" >前一页</a></li>
            <li><a href="">${pageBean.currentPage}</a></li>
            <li><a href="javaScript:void(0)" onclick="changePage(${pageBean.currentPage+1})" >后一页</a></li>
            <li>到</li>
            <li><input type="text" id="page" name="page" value="${pageBean.currentPage}" style="width: 30px"/></li>
            <li><button class="btn btn-primary" type="button" onclick="changePage($('#page').val())">GO</button></li>
             
          </ul>
          </nav>
        </div>
</body>
</html>