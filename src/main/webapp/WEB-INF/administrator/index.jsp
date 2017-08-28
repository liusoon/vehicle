<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${path}css/common.css">
<link rel="stylesheet" href="${path}css/style.css">
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<script type="text/javascript" src="${path}js/jquery.SuperSlide.js"></script>
<script type="text/javascript">
  $(function(){
      $(".sideMenu").slide({
         titCell:"h3", 
         targetCell:"ul",
         defaultIndex:0, 
         effect:'slideDown', 
         delayTime:'500' , 
         trigger:'click', 
         triggerTime:'150', 
         defaultPlay:true, 
         returnDefault:false,
         easing:'easeInQuint',
         endFun:function(){
              scrollWW();
         }
       });
      $(window).resize(function() {
          scrollWW();
      });
  });

  function scrollWW(){
    if($(".side").height()<$(".sideMenu").height()){
       $(".scroll").show();
       var pos = $(".sideMenu ul:visible").position().top-38;
       $('.sideMenu').animate({top:-pos});
    }else{
       $(".scroll").hide();
       $('.sideMenu').animate({top:0});
       n=1;
    }
  } 

 var n=1;
function menuScroll(num){
  var Scroll = $('.sideMenu');
  var ScrollP = $('.sideMenu').position();
  /*alert(n);
  alert(ScrollP.top);*/
  if(num==1){
     Scroll.animate({top:ScrollP.top-38});
     n = n+1;
  }else{
    if (ScrollP.top > -38 && ScrollP.top != 0) { ScrollP.top = -38; }
    if (ScrollP.top<0) {
      Scroll.animate({top:38+ScrollP.top});
    }else{
      n=1;
    }
    if(n>1){
      n = n-1;
    }
  }
}
</script>
<title>车辆维护管理系统-管理员界面</title>
</head>
<body>
    <div class="top">
      <div id="top_t">
        <div id="logo" class="fl"></div>
        <div id="photo_info" class="fr">
          <div id="photo" class="fl">
            <a href="#" title="欢迎用户${User.name}登录">
              <img src="images/a.png" alt="" width="60" height="60">
            </a>
          </div>
          <div id="base_info" class="fr">
            <div class="help_info">
              <a href="1" id="hp">&nbsp;</a>
              <a href="2" id="gy">&nbsp;</a>
              <a href="${pageContext.request.contextPath}/BaseAction_logout" id="out">&nbsp;</a>
            </div>
            <div class="info_center" style="width:300px">
               administrator
              <span id="nt" style="margin-left:-20px">通知</span><span><a href="${pageContext.request.contextPath}/admin/InformAction_informList?sign=1" id="notice" target="right">3</a></span>
            </div>
          </div>
        </div>
      </div>
      <div id="side_here">
        <div id="side_here_l" class="fl"></div>
        <div id="here_area" class="fl">当前位置：</div>
      </div>
    </div>
    <div class="side">
        <div class="sideMenu" style="margin:0 auto">
           <h3>管理员管理</h3>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/UserAction_addUser?sign=2" target="right">增加管理员</a></li> 
            <li><a href="${pageContext.request.contextPath}/admin/UserAction_userList?sign=2" target="right">管理管理员</a></li>
          </ul>
          <h3>用户管理</h3>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/UserAction_addUser" target="right">增加用户</a></li> 
            <li><a href="${pageContext.request.contextPath}/admin/UserAction_userList" target="right">管理用户</a></li>
          </ul>
          <h3> 车辆管理</h3>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/VehicleAction_addVehicleList" target="right">车辆备案</a></li>
            <li><a href="${pageContext.request.contextPath}/ordinary/VehicleAction_addVehicle" target="right">录入车辆信息</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/VehicleAction_vehicleList" target="right">管理车辆</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/VehicleAction_maturityVehicleList" target="right">到期车辆列表</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/VehicleAction_vehicleList?sign=1" target="right">查看删除的车辆</a></li>
          </ul>
          <h3>维护信息管理</h3>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/MaintainAction_addMaintain" target="right">维护信息录入</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/MaintainAction_maintainList" target="right">管理维护信息</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/MaintainAction_maintainList?sign=1" target="right">查看删除的维护信息</a></li>
          </ul>
          <h3>系统设置</h3>
          <ul>
            <li><a href="${pageContext.request.contextPath}/admin/UserAction_personal?id=${User.userId}" target="right">个人中心</a></li>
            <%-- <li><a href="${pageContext.request.contextPath}/admin/UserAction_aboutSystem" target="right">关于系统</a></li> --%>
          </ul>          
       </div>
    </div>
    <div class="main">
       <iframe name="right" id="rightMain" src="${rootPath}welcome.jsp" frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe>
    </div>

    <div class="bottom">
      <div id="bottom_bg">版权:未来</div>
    </div>
    <div class="scroll">
          <a href="javascript:;" class="per" title="使用鼠标滚轴滚动侧栏" onClick="menuScroll(1);"></a>
          <a href="javascript:;" class="next" title="使用鼠标滚轴滚动侧栏" onClick="menuScroll(2);"></a>
    </div>
</body>
</html>