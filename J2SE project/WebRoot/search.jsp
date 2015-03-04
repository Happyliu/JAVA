<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>search</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>  
	<script type="text/javascript">
	        var url = window.location.href;
	        var username = "";
	        //alert(url.indexOf("username="));
	        if(url.indexOf("username=")>0){
	        username = url.substring(url.indexOf("username=")+9,url.length);
	        }
            //alert(username.trim().length);
	  function init(){
	        //var url = window.location.href;
	        //alert(url);
            //var username = url.substr(url.indexOf("username=")+9,url.length);
            //alert(username);
            if(username.trim().length>0){
                $("#loginmsg").html(username+"yuor have logged in successfully !");
            }else{
                $("#loginmsg").html("Please first log in !");
            }
            

	}
	
	function go(){
	var orderid = $("#orderid").val();
	
	if(username.trim().length!=0){
	   //alert(username);
	   //alert(orderid);
	   window.location.href="search.do?username="+username+"&orderid="+orderid;
	}else{
	   window.location.href="login.html";
	}

}
	</script>
  </head>
  
  <body onload="init()">
    <br><br><br><h5 align="center" style="color: red;" id="loginmsg"></h5>
    <h2 align="center" >Sun Taxi</h2><br>
    <div align="center">
    Order ID：<input type="text" value="" id="orderid" />
    <input type="button" value="findlocation" onclick="go()" style="padding: 2 4 0 4;font-size:12px;height:23;background-color:#20c020;border-width:1;"/></div>
    <br>
    <br>
    <br>
  </body>
</html>
