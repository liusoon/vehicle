<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆维护管理系统-维护信息录入</title>
</head>
<body>
   <form  action="${pageContext.request.contextPath}/AdminAction_saveMaintain" method="post" >
     <table align="center">        
        <tr>
          <th>车主姓名:</th>
          <th><input type="text" name="maintain.userName" required oninvalid="setCustomValidity('请填写车主姓名');" oninput="setCustomValidity('');"/></th>
        </tr>
        <tr>
          <th>车辆档案号:</th>
          <th><input type="text" name="maintain.vehicleId" required oninvalid="setCustomValidity('请输入车辆档案号');" oninput="setCustomValidity('');"/></th>
        </tr>
        <tr>
          <th>车牌号:</th>
          <th><input type="text" name="maintain.plateId" required oninvalid="setCustomValidity('请填写车牌号');" oninput="setCustomValidity('');"/></th>
        </tr>
        <tr>
          <th><input type="submit" value="维护信息录入"/></th>
        </tr>
     </table>
 
   </form>
    
</body>
</html>