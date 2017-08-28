<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTmL 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${path}css/bootstrap.css">
<title>修改维护信息</title>
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
   margin-right:50px;
 }
 .butn{
   width: 400px;
   float: right;
 }
  .butn input{
    float: right;
    width: 90px;
    margin-right: 50px
  }
</style>
<body>
<form action="${pageContext.request.contextPath}/admin/MaintainAction_updateMainTain?id=${maintain1.maintainId}" method="post">
<div id="box">
       <div class="col-lg-5 col-md-6  col-xs-6" >
            <span>车主姓名：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" class="form-control" name="maintain1.userName" value="${maintain1.userName}" required  oninvalid="setCustomValidity('请输入车主姓名');" oninput="setCustomValidity('');"/>
         </div> 
      <div class="col-lg-5 col-md-6  col-xs-6">
            <span>车牌号：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" class="form-control" name="maintain1.plateId" value="${maintain1.plateId}" required  oninvalid="setCustomValidity('请输入车牌号');" oninput="setCustomValidity('');"/>
         </div>
      <div class="col-lg-5 col-md-6  col-xs-6">
            <span>联系方式：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" class="form-control" name="maintain1.userPhone" value="${maintain1.userPhone}"   pattern="^1[3-9]\d{9}$" required oninvalid="setCustomValidity('请输入11位手机号');" oninput="setCustomValidity('');"/>
         </div>
      <div class="butn">
         <input class="btn btn-primary" type="submit" value="修&nbsp;&nbsp;改"> 
         <input class="btn btn-primary" type="reset" value="重&nbsp;&nbsp;置">  
  
      </div>
</div>
</form>
</body>
</html>