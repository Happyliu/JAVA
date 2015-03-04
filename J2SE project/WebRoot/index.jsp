<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>index</title>
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
	        if(url.indexOf("username=")>0){
	        username = url.substring(url.indexOf("username=")+9,url.length);
	        }
	  function init(){
            if(username.trim().length>0){
                $("#loginmsg").html(username+"Log in sucessful!");
            }else{
                $("#loginmsg").html("please first login!");
            }
            

	}
	
	function index_go(index_tag){
	//alert(index_tag);
	if(index_tag=="1"&&username.trim().length!=0){
	   window.location.href="go.do?username="+username;
	}else if(index_tag=="2"&&username.trim().length!=0){
	   //window.location.href="find.do?username="+username;
	   window.location.href="find_select.jsp?username="+username;
	   
	}else{
	   window.location.href="login.html";
	}

}

	</script>
  </head>
  
  <body onload="init()">
    <br><br><br><h5 align="center" style="color: red;" id="loginmsg"></h5>
    <h2 align="center" >Welcome to sun taxi!!</h2><br>
    <div align="center">
    <input type="button" value="Order" onclick="index_go(1)" style="padding: 2 4 0 4;font-size:12px;height:23;background-color:#20c020;border-width:1;"/>
    <input type="button" value="FindLocation" onclick="index_go(2)" style="padding: 2 4 0 4;font-size:12px;height:23;background-color:#20c020;border-width:1;"/></div>
    <br>
    <br>
    <br>
    
  </body>
</html>
