<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%@ page language="java" import="org.suntaxi.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>    
  <head> 
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>        
    <script src="http://maps.googleapis.com/maps/api/js?sensor=false" type="text/javascript"></script>
    <script type="text/javascript" src="js/gmap3.js"></script> 
    <style>
      body{
        text-align:center;
      }
      .gmap3{
        margin: 20px auto;
        border: 1px dashed #C0C0C0;
        width: 590px;
        height: 350px;
      }
    </style>
    
    <script type="text/javascript">
    

    
    $(function(){
      
    $("#test").gmap3({
  getroute:{
    options:{
        origin:["39.91269883351498,116.3972282409668"],
        destination:["39.8800373323037,116.3675308227539"],
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    },
    callback: function(results){
      if (!results) return;
      $(this).gmap3({
        map:{
          options:{
            zoom: 13,  
            center: [39.9493, 116.3975]
          }
        },
        directionsrenderer:{
          options:{
            directions:results
          } 
        }
      });
    }
  }
});
        
      });
    </script>
  <body>
  <br>
  <h2 id="msg" style="color: red;">your order is created, your path is ：</h2>
     <%
    Orders order=(Orders)request.getAttribute("order"); 
    %>

  <input type="hidden" id="begin"  value="<%=order.getCusStartPlace_txt() %>"/>
  <input type="hidden" id="end" value="<%=order.getDestination_txt()%>"/>
  <input type="hidden" id="username" value="<%=order.getCusName() %>"/>
  
    <div id="test" class="gmap3"></div>
    		<br>	
<a href="index.jsp" id="back">Back</a>
  </body>
</html>