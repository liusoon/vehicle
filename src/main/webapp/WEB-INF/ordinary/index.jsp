<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${path}css/common.css">
<link rel="stylesheet" href="${path}css/style.css">
<style>
	#usern{
	position: relative;
	display: block;
}
#usern1{
	position: absolute;
	display: none;
	top:65px;
	/* left:  */
}
#usern:hover #usern1{
	display: block;
}
</style>
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
<title>车辆维护管理系统-普通用户首页</title>
</head>
<body>
    <div class="top">
      <div id="top_t">
        <div id="logo" class="fl"></div>
        <div id="photo_info" class="fr">
          <div id="photo" class="fl">
             <a href="#" id="usern">
              <img src="images/a.png" alt="" width="60" height="60">
              <div id="usern1">
                <p>用户姓名：${User.name}</p>
                <p>用户身份：${User.role}</p>
                <p></p>
              </div>
            </a>
          </div>
          <div id="base_info" class="fr">
            <div class="help_info">
              <a href="1" id="hp">&nbsp;</a>
              <a href="2" id="gy">&nbsp;</a>
              <a href="${pageContext.request.contextPath}/UserAction_logout" id="out">&nbsp;</a>
            </div>
            <div class="info_center">
               ordinary
              <span id="nt">通知</span><span><a href="#" id="notice">3</a></span>
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
          <h3> 车辆管理</h3>
          <ul>
            <li><a href="${pageContext.request.contextPath}/OrdinaryAction_add" target="right">车辆备案</a></li>
            <li><a href="${pageContext.request.contextPath}/OrdinaryAction_list" target="right">车辆列表</a></li>
          </ul>
          <h3>系统设置</h3>
          <ul>
            <li><a href="" target="right">修改密码</a></li>
            <li><a href="" target="right">关于系统</a></li>
          </ul>
          
       </div>
    </div>
    <div class="main">
       <iframe name="right" id="rightMain" src="welcome.jsp"  frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe>
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