<%@ page language="java" import="org.suntaxi.bean.*" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>find</title>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">

		<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
		<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>

		<script type="text/javascript">
            var url = location.href;
            var username = "";
	        //alert(url.indexOf("username="));
	        if(url.indexOf("username=")>0){
	        username = url.substring(url.indexOf("username=")+9,url.length);
	        }
        
        function init(){
        
             if(username.trim().length>0){
                $("#msg").html(username+",The following is all of your orders!1");
            }
            
            $("#back").attr("href","index.jsp?username="+username);
        }
</script>
	</head>
	<body onload="init()">
		<br>
		<br>	<input type="hidden" value="" id="username_msg">
<div id="msg" style="font-size: 24px;color:red;"></div><br>
<table border="1" cellspacing="0" cellpadding="0" width="89%" style="align:center;">
    <tr bgcolor="40c040" style="font-weight:bold;">
    <th>OrderNum</th><th>CustomerName</th><th>Time</th><th>CarNum</th><th>StartPlace</th><th>Destination</th><th width="100" colspan="2">operation</th></tr>
   <%
   //use roop to display data
    List<Orders> newslist=(List<Orders>)request.getAttribute("newslist"); 
    %>

    <%
    if(newslist.size()!=0){
      for(int i=0;i<newslist.size();i++){
      Orders news =new Orders(); 
       news = newslist.get(i);   

     %>
<tr>
      <td><%=newslist.get(i).getOrderId() %></td>
      <td><%=newslist.get(i).getCusName() %></td>
      <td><%=newslist.get(i).getStartTime() %></td>
      <td><%=newslist.get(i).getCarNum()%></td>
      <td><%=newslist.get(i).getCusStartPlace_txt()%></td>
      <td><%=newslist.get(i).getDestination_txt()%></td>
      <td><a href="del.do?username=<%=newslist.get(i).getCusName()%>&orderId=<%=newslist.get(i).getOrderId()%>" onclick='return confirm("")'
          >Delete</a></td>
   </tr>
   <%
      }
    }else{
     %>
<tr><td colspan="6">Your have not orderd our car</td></tr>
    <%
    }
    %>
   </table>		<br>
		<br>	
<a href="" id="back">back</a>
	</body>
</html>
