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
    <script src="http://maps.googleapis.com/maps/api/js?sensor=false&language=en" type="text/javascript"></script>
    <script type="text/javascript" src="js/gmap3.js"></script> 
    <style>
      body{
        text-align:center;
      }
      .gmap3{
        margin: 20px auto;
        border: 1px dashed #C0C0C0;
        width: 889px;
        height: 460px;
      }
    </style>
    
    <script type="text/javascript">
    
            //var url = "";
	        //var tag = "";
	        
	        //if(url.indexOf("tag=")>0){
	             //tag = url.substring(url.indexOf("tag=")+4,url.length);
	        //}

	function init(){
	        $("#test").gmap3({
    getroute:{
    options:{
        origin:$("#begin").val(),
        destination:$("#end").val(),
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    },
    callback: function(results){
      if (!results) return;
      $(this).gmap3({
        map:{
          options:{
            zoom: 12,  
            center: [$("#end").val()]
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
	   
	   getDistance();
	   initmsg();
	      
	 }
	 
	 function initmsg(){
	 
	 var ordertag = $("#ordertag").val();
	 if(ordertag=="0"){
	      $("#msg").html("Your order is in progress, and your path is ：");
	 }else{
	      $("#msg").html("Your order has been completed!!");
	 }
	 
	 }

	  function getDistance(){
	     $("#test").gmap3({
          getdistance:{
            options:{ 
              origins:[$("#begin").val()], 
              destinations:[$("#end").val()],
              travelMode: google.maps.TravelMode.DRIVING
            },
            callback: function(results, status){
              //var html = "";
              if (results){
                for (var i = 0; i < results.rows.length; i++){
                  var elements = results.rows[i].elements;
                  for(var j=0; j<elements.length; j++){
                    switch(elements[j].status){
                      case "OK":
                        $("#Total_Distance").attr("value",elements[j].distance.text);
                        break;
                      case "NOT_FOUND":
                        $("#Total_Distance").attr("value","error");
                        break;
                      case "ZERO_RESULTS":
                        $("#Total_Distance").attr("value","error");
                        break;
                    }
                  }
                } 
              } else {
                //html = "error";
              }
              //getAllMsg();
              //$("#results").html( html );
              var ordertag = $("#ordertag").val();
              //alert(ordertag=="0");
              if(ordertag=="0"){
	             make_tag($("#Current_Distance").val());
	          }else{
	              alert("your order has been created successfully !");
	          }
              
              
            }
          }
        });
	  
	  }
	  
	  function getAllMsg(){
	  
	       var orderid = $("#orderid").val();
	       var Total_Distance = $("#Total_Distance").val().replace("km","").trim();
	       var url="current.do?orderid="+orderid+"&Total_Distance="+Total_Distance;
           $.get(url, null, function(data){
                  getAddr(data);

         });
	  
	  }
	  
	  
	  function make_tag(Current_Distance){

	  $('#test').gmap3({
          marker:{
              address: Current_Distance
          },
          map:{
            options:{
              zoom: 12
            }
          }
        });
	  
	  }
	  
	  
        function getAddr(latLng){
             //alert(latLng);
             var lat_lng = latLng+"";
             
             //lat_lng = lat_lng.substring(1,lat_lng.length-1).replace(", ",",");
             $.ajax({
                 type:"POST",
                 //the file location of json
                 url:"http://maps.googleapis.com/maps/api/geocode/json?language=en&latlng="+lat_lng+"&sensor=false",
                 //data type is json
                 dataType: "json",
                 //the method
                 success: function(data2){
						$.each(data2, function(i, item2) {
							        $("#Current_Distance").attr("value",item2[0].formatted_address.trim());
							        var tag = item2[0].formatted_address.trim();
							        if(tag!='error'){
							        
							           alert("your place is："+$("#Current_Distance").val());
							           make_tag($("#Current_Distance").val());
							        
							        }else{
							           
							           alert("your order has been created successfully !");
							        }
							        
							  //}
						});
						
						
                    }
               })
               

       }
    

    </script>
  <body onload="init();">
  <br>
  <h2 id="msg" style="color: red;">your order is created, and your path is ：</h2>
     <%
   //display the data
    Orders order=(Orders)request.getAttribute("order"); // get the object in the request
    String ordertag=(String)request.getAttribute("ordertag"); // 
    String currentPlace=(String)request.getAttribute("currentPlace"); // 
    %>
  <input  id="orderid" type="hidden" value="<%=order.getOrderId() %>"/>
  <input  id="begin" type="hidden" value="<%=order.getCusStartPlace_txt() %>"/>
  <input  id="end" type="hidden" value="<%=order.getDestination_txt()%>"/>
  <input  id="username"  type="hidden" value="<%=order.getCusName() %>"/>
  <input  id="Total_Distance"  value="" type="hidden"/>
  <input  id="Current_Distance"  type="hidden" value="<%=currentPlace %>"/>
  <input  id="ordertag"   type="hidden" value="<%=ordertag %>"/>
    <div id="test" class="gmap3"></div>
    		<br>	
    		
<a href="index.jsp" id="back">Back</a>
  </body>
</html>
