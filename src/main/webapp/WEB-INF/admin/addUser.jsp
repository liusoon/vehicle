<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${path}css/bootstrap.css">
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<script type="text/javascript" src="${path}js/jquery.md5.js" ></script>
<script>
 $(function(){
  $("#before").blur(function(){
   var before = $(this).val();
   var beforeVal = $.md5(before);
   $("#after").val(beforeVal);
  });
 });
</script>

<title>车辆维护管理系统——添加用户</title>
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
</style>
<body>
<font color="red" ><s:property value="exception.message" /></font>
<form  action="${pageContext.request.contextPath}/admin/UserAction_saveUser" method="post" >
<div id="box">
       <div class="col-lg-5 col-md-6  col-xs-6" >
            <span>账户：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="code" class="form-control" required  oninvalid="setCustomValidity('请输入账号');" oninput="setCustomValidity('');" />
         </div> 
      <div class="col-lg-5 col-md-6  col-xs-6">
            <span>密码：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="password" id="before" class="form-control" required oninvalid="setCustomValidity('请输入密码');" oninput="setCustomValidity('');"/>
           <input type="hidden" name="password" id="after"/>
         </div>
      <div class="col-lg-5 col-md-6  col-xs-6">
            <span>用户名：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="name" class="form-control" required oninvalid="setCustomValidity('请输入用户名');" oninput="setCustomValidity('');"/>
         </div>
      <div class="col-lg-5 col-md-6  col-xs-6">
            <span>联系方式：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="tel" name="phone" class="form-control" pattern="^1[3-9]\d{9}$" required oninvalid="setCustomValidity('请输入11位手机号');" oninput="setCustomValidity('');"/>
         </div>
      <div class="col-lg-5 col-md-6  col-xs-6">
            <span>地址：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="address" class="form-control" required oninvalid="setCustomValidity('请输入地址');" oninput="setCustomValidity('');"/>
         </div>
         <button class="btn btn-primary" type="submit">增加用户</button>
      </div>
</form>
</body>
</html>