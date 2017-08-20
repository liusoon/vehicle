<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆维护系统-车辆备案</title>  
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<script type="text/javascript" src="${path}js/selectList.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
	 loadSelect("001","category","category.dict_id"); 
	 loadSelect("002","operationStatus","operationStatus.dict_id"); 
   });
</script>

</head>
<body>
  <font color="red" ><s:property value="exception.message" /></font>
  <form action="${pageContext.request.contextPath}/ordinary/VehicleAction_saveVehicleByOrdinary" method="post"> 
     <table>
       <tr>
         <th>车牌号</th>
         <td><input type="text" name="plateId" id="plateId" required  oninvalid="setCustomValidity('请填写车牌号');" oninput="setCustomValidity('');" /></td>    
       </tr>
       <tr>
         <th>车辆类型</th>
         <td id="category"></td> 
       </tr>
       <tr>
         <th>车辆型号</th>
         <td><input type="text" name="model" id="model" required  oninvalid="setCustomValidity('请填写车牌号');" oninput="setCustomValidity('');" /></td> 
       </tr>
       <tr>
         <th>发动机型号</th>
         <td><input type="text" name="engineId" id="engineId" required  oninvalid="setCustomValidity('请填写发动机编号');" oninput="setCustomValidity('');" /></td>
          
       </tr>
       <tr>
         <th>车底盘号衍射</th>
         <td><input type="text" name="carChassisId" id="carChassisId" required  oninvalid="setCustomValidity('请填写车底盘号衍射');" oninput="setCustomValidity('');" /></td>
          
       </tr>
       <tr>
         <th>车辆重量</th>
         <td><input type="text" name="weight" id="weight" required  oninvalid="setCustomValidity('请填写车辆重量');" oninput="setCustomValidity('');" /></td>
       </tr>
       <tr>
         <th>出厂日期</th>
         <td><input type="date" name="manufactureDate" id="manufactureDate" required  oninvalid="setCustomValidity('请选择出厂日期');" oninput="setCustomValidity('');" /></td> 
       </tr>  
       <tr>
         <th><input type="submit" value="进行备案"/></th>
       </tr>       
     </table>
  </form>

   
</body>
</html>