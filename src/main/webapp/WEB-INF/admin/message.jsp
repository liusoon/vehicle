<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆维护管理系统--消息通知</title>
</head>
<body>
<table>
   <tr>  
     <td><font color="red">${message}</font></td>
     <td>该系统将在 </td>  
     <td><strong> <span id="time">3</span></strong> </td>  
     <td>秒钟后自动跳转至登录界面 </td>  
   </tr>  
</table>  
       
 <script type="text/javascript">  
      delayURL();    
      function delayURL() { 
      var delay = document.getElementById("time").innerHTML;
      var t = setTimeout("delayURL()", 1000);
      if (delay > 0) {
         delay--;
         document.getElementById("time").innerHTML = delay;
      }else {
        clearTimeout(t);
        top.location.href ="${pageContext.request.contextPath}/BaseAction_login";
      }        
    } 
</script>
</body>
</html>