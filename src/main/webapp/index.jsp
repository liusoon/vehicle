<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆维护管理系统-用户登录</title>
<link rel="stylesheet" href="${path}css/login.css">
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<script type="text/javascript" src="${path}js/jquery.md5.js" ></script>
<script>
   $(function(){
	    $("#begin").blur(function(){
	     var begin = $(this).val();
	     var beginval = $.md5(begin);
	     $("#end").val(beginval);
	    });
	    
	    $('form').submit(function(){
	    	$("#begin").blur();
	    });
	    
   });
</script>
</head>
<body>
	<div id="login_top">
		<div id="welcome">
			欢迎使用车辆维护管理系统
		</div>
		<div id="back">
			<a href="index.jsp">返回首页</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="#">帮助</a>
		</div>
	</div>
	<div id="login_center">
		<div id="login_area">
			<div id="login_form">
				<form action="BaseAction_login" method="post">
					<div id="login_tip">
						用户登录&nbsp;&nbsp;UserLogin
					</div>
					<div><input type="text" class="username" name="code" required oninvalid="setCustomValidity('请输入账号');" oninput="setCustomValidity('');" ></div>
					<div><input type="password" class="pwd" id="begin" required oninvalid="setCustomValidity('请输入密码');" oninput="setCustomValidity('');"></div>
					 <!--加密转换 -->
					<div><input type="hidden"  name="password" id="end" ></div>
			        <div><font color="red" ><s:property value="exception.message" /></font></div>
					<a href="${pageContext.request.contextPath}/admin/UserAction_forgetPassword" target="right">忘记密码</a>
					<div id="btn_area">
						<input type="submit" name="submit" id="sub_btn" value="登&nbsp;&nbsp;录">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="login_bottom"></div>  
</body>
</html>