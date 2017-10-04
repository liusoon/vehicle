<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改车辆页面</title>
<link rel="stylesheet" href="${path}css/bootstrap.css">
<link rel="stylesheet" href="${path}css/recruit.css">
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<style type="text/css">
body{
   background:#efefef;
 }
html{
   background:#efefef;
} 
 .butt{
 width:80px;
 height:30px;
 border-radius:15px;
 background:rgb(153,200,207);

 }
 
 .picture{
 position:absolute;
 top:0;
 left:70%;
 }
</style>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/VehicleAction_updateVehicle?vehicleId=${vehicle1.vehicleId}" method="post">
<img src="${path }images/car.png" class="picture">
<div class="box">
   <div class="box-left">
   		<span>档案号</span>
   		<input type="text" name="vehicle1.vehicleId" value="${vehicle1.vehicleId}"  readonly="readonly">
   		<span>车辆状态</span>
   		<input type="text" name="vehicle1.operationStatus" value="${vehicle1.operationStatus}" readonly="readonly">
   		<lable id="text">类型:</lable>
		<select name="vehicle1.category" >
				<option value="货车">货车</option>
				<option value="汽车">汽车</option>
	   </select><br>
       <div class="col-lg-4 col-md-4  col-xs-4" >
            <span>发动机编号：</span>
        </div>
        <div class="col-lg-8 col-md-8  col-xs-8" style="float: right">
           <input type="text" name="vehicle1.engineId" class="form-control" value="${vehicle1.engineId}" required oninvalid="setCustomValidity('请输入发动机编号');" oninput="setCustomValidity('');"/>
         </div>
         <div class="col-lg-4 col-md-4  col-xs-4" style="margin-top: 33px;height:50px" >
            <span>车牌号:</span>
        </div>
        <div class="col-lg-8 col-md-8  col-xs-8" style="float: right">
           <input type="text" name="vehicle1.plateId" class="form-control" value="${vehicle1.plateId}"  pattern="^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$" required  oninvalid="setCustomValidity('请填写正确格式的车牌号,如浙E636UU');" oninput="setCustomValidity('');"/>
            
         </div> 
         <div class="col-lg-4 col-md-5  col-xs-5">
            <span>车辆型号：</span>
        </div>
        <div class="col-lg-8 col-md-8  col-xs-8">
           
           <input type="text" name="vehicle1.model" class="form-control" value="${vehicle1.model}" required oninvalid="setCustomValidity('请输入车辆类型');" oninput="setCustomValidity('');"/>
         </div>
         <div class="col-lg-4 col-md-5  col-xs-5">
            <span>车辆重量：</span>
        </div>
        <div class="col-lg-8 col-md-8  col-xs-8">
           
           <input type="text" name="vehicle1.weight" class="form-control" value="${vehicle1.weight}" pattern="^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$" required  oninvalid="setCustomValidity('请填写带小数点的正数,单位是吨');" oninput="setCustomValidity('');">
         </div>

   </div><!-- 左边蓝结束 -->
   <div class="box-right"><!-- 右边栏开始 -->
         <div class="col-lg-4 col-md-5  col-xs-5">
            <span>备案日期：</span>
        </div>
        <div class="col-lg-8 col-md-8  col-xs-8">
           
           <input type="text" name="vehicle1.date" value="${vehicle1.date}" class="sang_Calender" style="width:270px;height:35px;border-radius:5px">
         </div>
      <div class="col-lg-4 col-md-5  col-xs-5">
            <span >车底盘号衍射：</span>
        </div>
        <div class="col-lg-8 col-md-8  col-xs-8">
             <input type="text" name="vehicle1.carChassisId" class="form-control" value="${vehicle1.carChassisId}" required oninvalid="setCustomValidity('车底盘号衍射');" oninput="setCustomValidity('');"/>
         </div>
   			
         <div class="col-lg-4 col-md-5  col-xs-5">
            <span>出厂日期：</span>
        </div>
        <div class="col-lg-8 col-md-8  col-xs-8">
           
           <input type="text" name="vehicle1.manufactureDate" value="${vehicle1.manufactureDate}" class="sang_Calender" style="width:270px;height:35px;border-radius:5px"/>
         </div>
          <div class="col-lg-4 col-md-5  col-xs-5">
            <span>维护信息数量：</span>
        </div>
        <div class="col-lg-8 col-md-8  col-xs-8">
           <input type="text" name="vehicle1.maintainNumber" class="form-control" value="${vehicle1.maintainNumber}"  onkeyup="value=value.replace(/[^\d]/g,'')">
         </div>
         <div class="butn">
            <button class="butt" type="submit">修&nbsp;&nbsp;改</button> 
         </div><!-- 按钮 -->
   </div>
</div>
</form>
<script type="text/javascript" src="${path}js/datetime.js"></script>
</body>
</html>