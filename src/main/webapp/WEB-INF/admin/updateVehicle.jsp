<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改车辆页面</title>
<link rel="stylesheet" href="${path}css/bootstrap.css">
<link rel="stylesheet" href="${path}css/recruit.css">
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<script type="text/javascript" src="${path}js/selectList.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/VehicleAction_updateVehicle?vehicleId=${vehicle1.vehicleId}" method="post">
<div class="box">
   <div class="box-left">
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
           <input type="text" name="vehicle1.plateId" class="form-control" value="${vehicle1.plateId}"  required oninvalid="setCustomValidity('请输入车牌号');" oninput="setCustomValidity('');"/>
            
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
           
           <input type="text" name="vehicle1.weight" class="form-control" value="${vehicle1.weight}" onkeyup="value=value.replace(/[^\d]/g,'')">
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
           <input class="btn btn-primary" type="submit" value="修&nbsp;&nbsp;改" style="width: 100px;float: left"></input>
           <input class="btn btn-primary" type="reset" value="重&nbsp;&nbsp;置" style="width: 100px ;float: right;"></input>
         </div><!-- 按钮 -->
   </div>
</div>
</form>
<script type="text/javascript" src="${path}js/datetime.js"></script>
</body>
</html>