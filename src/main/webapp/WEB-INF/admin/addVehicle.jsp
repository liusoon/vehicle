<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆维护系统-新建车辆备案</title>  
<link rel="stylesheet" href="${path}css/bootstrap.css">
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<script type="text/javascript" src="${path}js/selectList.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	 loadSelect("001","category","category.dict_id"); 	 
});
</script>
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
 #box div{
  position: relative;
 }
 .choose{
  position: relative;
  width: 100%;
  height: 50px;
 }
 #box .box-innner{
  width: 100%;
  height: 40px;
  margin-top: 10px
 }
 #box .sel{
  width: 100px;
  height: 30px;
  margin-top:20px;
  text-align: center;
  
 }

</style>
<body>
<font color="red" ><s:property value="exception.message" /></font>
<form action="${pageContext.request.contextPath}/admin/VehicleAction_saveVehicleByAdmin" method="post">
 <div id="box">
     <div class="box-innner">
           <div class="col-lg-5 col-md-6  col-xs-6">
            <span>车主账号：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="user.code" class="form-control" required  oninvalid="setCustomValidity('请输入账号');" oninput="setCustomValidity('');" />
         </div>
     </div>
 	 <div class="box-innner">
           <div class="col-lg-5 col-md-6  col-xs-6">
            <span>车主名：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="user.name" class="form-control" required oninvalid="setCustomValidity('请输入用户名');" oninput="setCustomValidity('');"/>
         </div>
     </div>
     <div class="box-innner">
           <div class="col-lg-5 col-md-6  col-xs-6">
            <span>车牌号：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="plateId" class="form-control" id="plateId" required  oninvalid="setCustomValidity('请填写车牌号');" oninput="setCustomValidity('');" />
         </div>
     </div>
     <div class="box-innner">
           <div class="col-lg-5 col-md-6  col-xs-6"  style="width: 210px">
            <span>车辆类型：</span>
        </div>  
        <div class="col-lg-6 col-md-6  col-xs-6" style="width: 200px">
           <span id="category"></span>
         </div> 
     </div> 
     <div class="box-innner">
           <div class="col-lg-5 col-md-6  col-xs-6"  style="width: 210px">
            <span>车辆型号：</span>
        </div>  
        <div class="col-lg-6 col-md-6  col-xs-6" style="width: 200px">
            <input type="text" name="model" class="form-control" id="model" required  oninvalid="setCustomValidity('请填写车辆型号');" oninput="setCustomValidity('');" />
         </div> 
     </div> 
     <div class="box-innner">
           <div class="col-lg-5 col-md-6  col-xs-6">
            <span>发动机型号：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="engineId" class="form-control" id="engineId" required  oninvalid="setCustomValidity('请填写发动机编号');" oninput="setCustomValidity('');" />
         </div>
     </div>
     <div class="box-innner">
           <div class="col-lg-5 col-md-6  col-xs-6">
            <span>车底盘号衍射：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="carChassisId" class="form-control" id="carChassisId" required  oninvalid="setCustomValidity('请填写车底盘号衍射');" oninput="setCustomValidity('');" />
         </div>
     </div>
     <div class="box-innner">
           <div class="col-lg-5 col-md-6  col-xs-6">
            <span>车辆重量：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="weight" class="form-control" id="weight" required  oninvalid="setCustomValidity('请填写车辆重量');" oninput="setCustomValidity('');" />
         </div>
     </div>
     <div class="box-innner">
           <div class="col-lg-5 col-md-6  col-xs-6">
            <span>出厂日期：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="text" name="manufactureDate" id="manufactureDate" class="sang_Calender" required  oninvalid="setCustomValidity('请选择出厂日期');" oninput="setCustomValidity('');" />
            
         </div>
     </div>
      <button class="btn btn-primary" type="submit">进行备案</button>  
  </div>
</form> 
<script type="text/javascript" src="${path}js/datetime.js"></script>  
</body>
</html>