<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="${Path}js/jquery.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
        $("#div1").animate({top:'300px',left:'600px',opacity:'1',fontSize:'50'},2000);
        $("#div2").animate({top:'220px'},2000);
});
     
    </script>
    <style type="text/css">
     body{
        background: url(${path}images/welcome.jpg)no-repeat;
        background-size:100%; 
        font-family: "宋体"
    }
    #div1{ 
        font-size: 20px;
        color: #136f96;
        position:absolute;
        left: 750px;
        opacity: 0.5;
    }
     #div2{ 
        font-size: 50px;
        color: #136f96;
        position:absolute;
        left: 650px;
        top:1000px;
    }
    </style>
</head>
<body>
    <div id="div1">车辆维护管理平台</div>
    <div id="div2">欢迎进入</div>
</body>
</html>