<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆维护管理系统-维护信息录入</title>
<link rel="stylesheet" href="${path}css/bootstrap.css">
</head>
<style type="text/css">
body{
  background: url(${path}images/dotted.png);
}
 #box{
  width: 500px;
  height: auto;
  margin: 50px auto
 }
 #box span{
 float: right;
 margin-top: 20px;
 font-size: 20px
 }
 #box input{
  margin-top: 20px
 }
 #box button{
   float: right;
   margin-top: 20px;
   margin-right:20px;
   /*width: 300px*/
 }
</style>
<body>
<font color="red"><s:property value="exception.message" /></font>
   <form  action="${pageContext.request.contextPath}/admin/MaintainAction_saveMaintain" method="post" >
   <div id="box">
       <div class="col-lg-5 col-md-6  col-xs-6" >
            <span>车主姓名：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" class="form-control" name="userName" required oninvalid="setCustomValidity('请填写车主姓名');" oninput="setCustomValidity('');"/>
         </div> 
      <div class="col-lg-5 col-md-6  col-xs-6">
            <span>车辆档案号：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" class="form-control" name="vehicleId" required oninvalid="setCustomValidity('请输入车辆档案号');" oninput="setCustomValidity('');"/>
         </div>
      <div class="col-lg-5 col-md-6  col-xs-6">
            <span>车牌号：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" class="form-control" name="plateId" required oninvalid="setCustomValidity('请填写车牌号');" oninput="setCustomValidity('');"/>
         </div>
      
         <button class="btn btn-primary" type="submit">维护信息录入</button>
      </div>
   </form>
</body>
</html>