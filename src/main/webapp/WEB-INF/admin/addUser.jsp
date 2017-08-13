<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆维护管理系统——添加用户</title>
</head>
<body>
   <form  action="${pageContext.request.contextPath}/AdminAction_saveUser" method="post" >
     <table align="center">
        <tr align="center">
          <th>添加用户</th>
         </tr>  
        <tr>
          <th>账号:</th>
          <th><input type="text" name="user.code" required  oninvalid="setCustomValidity('请输入账号');" oninput="setCustomValidity('');" /></th>
        </tr>       
        <tr>
          <th>密码:</th>
          <th><input type="password" name="user.password" required oninvalid="setCustomValidity('请输入密码');" oninput="setCustomValidity('');"/></th>
        </tr>
        <tr>
          <th>用户名:</th>
          <th><input type="text" name="user.name" required oninvalid="setCustomValidity('请输入用户名');" oninput="setCustomValidity('');"/></th>
        </tr>
        <tr>
          <th>联系方式:</th>
          <th><input type="tel" name="user.phone" pattern="^1[3-9]\d{9}$" required oninvalid="setCustomValidity('请输入11位手机号');" oninput="setCustomValidity('');"/></th>
        </tr>
        <tr>
          <th>地址:</th>
          <th><input type="text" name="user.address" required oninvalid="setCustomValidity('请输入地址');" oninput="setCustomValidity('');"/></th>
        </tr>           
        <tr>
          <th><input type="submit" value="增加用户"/></th>
        </tr>
     </table>
 
   </form>
   
     <s:debug/>
  </div>
</body>
</html>