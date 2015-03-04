<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="org.suntaxi.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html >    
  <head> 
    <script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>        
    <script src="http://maps.googleapis.com/maps/api/js?sensor=false&language=en" type="text/javascript"></script>
    <script type="text/javascript" src="js/gmap3.js"></script>
    
    <script class="jqueryui library" src="time_ui/abl-core-min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="time_ui/ui-core-min.css">
	
    <link rel="stylesheet" type="text/css" href="css/gmap3-menu.css" />
    <script type="text/javascript" src="js/gmap3-menu.js"></script>
    <style>
      #container{
        position:relative;
        height:700px;
      }
      #directions{
        position:absolute;
        width: 23%;
        right:1%;
        height: 690px;
        overflow:auto;
      }
      #googleMap{
        border: 1px dashed #C0C0C0;
        width: 70%;
        height: 700px;
      }
    </style>
    <script type="text/javascript">
      var url = window.location.href;
      var username = "";

      $(function(){
	        //alert(url.indexOf("username="));
	        if(url.indexOf("username=")>0){
	           username = url.substring(url.indexOf("username=")+9,url.length);
	           $("#username").attr("value",username);
	           //alert(username);
	        }else{
	          window.location.href="login.html";
	        }

          var $map = $("#googleMap"), 
          menu = new Gmap3Menu($map),
            
          current,  // current click event (used to save as start / end position)
          m1,       // marker "from"
          m2;       // marker "to"
          
          
        // update marker
        function updateMarker(marker, isM1){
          if (isM1){
            m1 = marker;
          } else {
            m2 = marker;
          }
          updateDirections();
        }
        
        
        function getAddr(latLng,tag){
             var lat_lng = latLng+"";
             
             lat_lng = lat_lng.substring(1,lat_lng.length-1).replace(", ",",");
             $.ajax({
                 //request type is post
                 type:"POST",
                 //json file place
                 url:"http://maps.googleapis.com/maps/api/geocode/json?language=en&latlng="+lat_lng+"&sensor=false",
                 //type is json
                 dataType: "json",
                 
                 success: function(data){

						$.each(data, function(i, item) {
							  //alert(!(item[0].formatted_address==undefined));
							  if(!(item[0].formatted_address==undefined)){
							        if(tag==0){
							           $("#beginaddr").attr("value",item[0].formatted_address.trim());
							           
							           if(item[0].formatted_address.indexOf("Haidian")>0){
							               
							                $("#line_tag").attr("value","line1");
							           }else if(item[0].formatted_address.indexOf("Dongcheng")>0){
							               
							                $("#line_tag").attr("value","line2");
							           }
							        }else if(tag==1){
							           $("#endaddr").attr("value",item[0].formatted_address.trim());
							        } 
							  }
						});
                    }
               })
       }
        
        
     function distance(){
        //calculate the distance
        $map.gmap3({
          getdistance:{
            options:{ 
              origins:[$("#beginaddr").val()],
              destinations:[$("#endaddr").val()],
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
                        //html += elements[j].distance.text + " (" + elements[j].duration.text + ")<br />";
                        alert(elements[j].distance.text);
                        $("#distances").attr("value",elements[j].distance.text);
                        break;
                      case "NOT_FOUND":
                      alert("NOT_FOUND");
                        //html += "The origin and/or destination of this pairing could not be geocoded<br />";
                        $("#distances").attr("value","error");
                        break;
                      case "ZERO_RESULTS":
                      alert("ZERO_RESULTS");
                        $("#distances").attr("value","error");
                        //html += "No route could be found between the origin and destination.<br />";
                        break;
                    }
                  }
                } 
              } else {
                html = "error";
              }
              //$("#results").html( html );
            }
          }
        });

      }
      
        
      

        // add marker and manage which one it is (A, B)
        function addMarker(isM1){
          // clear previous marker if set
          var clear = {name:"marker"};
          if (isM1 && m1) {
            clear.tag = "from";
            $map.gmap3({clear:clear});
          } else if (!isM1 && m2){
            clear.tag = "to";
            $map.gmap3({clear:clear});
          }
          // add marker and store it
          $map.gmap3({
            marker:{
              latLng:current.latLng,
              options:{
                draggable:true,
                icon:new google.maps.MarkerImage("http://maps.gstatic.com/mapfiles/icon_green" + (isM1 ? "A" : "B") + ".png")
              },
              tag: (isM1 ? "from" : "to"),
              events: {
                dragend: function(marker){
                  updateMarker(marker, isM1);
                }
              },
              callback: function(marker){
                updateMarker(marker, isM1);
                //alert(isM1);
                if(isM1){
                    $("#beginmsg").attr("value",current.latLng);
                    //alert(m1.getPosition());
                    getAddr(current.latLng,0);
                    if($("#beginaddr").val().trim().length!=0&&$("#endaddr").val().trim().length!=0){
                         distance();
                    }

                }else{
                    //alert(current.latLng);
                    $("#endmsg").attr("value",current.latLng);
                    getAddr(current.latLng,1);
                    if($("#beginaddr").val().trim().length!=0&&$("#endaddr").val().trim().length!=0){
                         distance();
                    }
                }
                
              }
            }
          });
        }
        
        // function called to update direction is m1 and m2 are set
        function updateDirections(){
          if (!(m1 && m2)){
            return;
          }
          $map.gmap3({
            getroute:{
              options:{
                origin:m1.getPosition(),
                destination:m2.getPosition(),
                travelMode: google.maps.TravelMode.DRIVING
              },
              callback: function(results){
                if (!results) return;
                $map.gmap3({get:"directionrenderer"}).setDirections(results);
                distance();
              }
            }
          });
        }
        
        // MENU : ITEM 1
        menu.add("Direction to here", "itemB", 
          function(){
            menu.close();
            addMarker(false);
          });
        
        // MENU : ITEM 2
        menu.add("Direction from here", "itemA separator", 
          function(){
            menu.close();
            addMarker(true);
          })
        
        // MENU : ITEM 3
        menu.add("Zoom in", "zoomIn", 
          function(){
            var map = $map.gmap3("get");
            map.setZoom(map.getZoom() + 1);
            menu.close();
          });
        
        // MENU : ITEM 4
        menu.add("Zoom out", "zoomOut",
          function(){
            var map = $map.gmap3("get");
            map.setZoom(map.getZoom() - 1);
            menu.close();
          });
        
        // MENU : ITEM 5
        menu.add("Center here", "centerHere", 
          function(){
              $map.gmap3("get").setCenter(current.latLng);
              menu.close();
          });
        
        // INITIALIZE GOOGLE MAP
        $map.gmap3({
          map:{
            options:{
              center:[39.9493, 116.3975],
              zoom: 12
            },
            events:{
              rightclick:function(map, event){
                current = event;
                menu.open(current);
              },
              click: function(){
                menu.close();
              },
              dragstart: function(){
                menu.close();
              },
              zoom_changed: function(){
                menu.close();
              }
            }
          },
          // add direction renderer to configure options (else, automatically created with default options)
          directionsrenderer:{
            divId:"directions",
            options:{
              preserveViewport: true,
              markerOptions:{
                visible: false
              }
            }
          }
        });
      });

   function sub(){

          var beginaddr = $("#beginaddr").val();
          var endaddr = $("#endaddr").val();
          var gotime = $("#date").val();
          var username = $("#username").val();
          //
          var beginmsg = $("#beginmsg").val();
          var endmsg = $("#endmsg").val();
          
          var distances = $("#distances").val();
          //alert(distances);
          var line_tag = $("#line_tag").val();
          //alert(line_tag);
          
          var taxis = $("#taxis").val();
          //alert(taxis);
          if(beginaddr.trim().length==0){
           alert("please choose your start place");
           return;
          }
          if(endaddr.trim().length==0){
           alert("please choose your destination");
           return;
          }
          
          //if(gotime.trim().length==0){
           //alert("please choose start time");
           //return;
          //}
          
          if(username.trim().length==0){
           alert("Please first login");
           window.location.href="login.html";
           return;
          }
        window.location.href="order.do?username="+username+"&gotime=&beginmsg="+beginmsg+"&endmsg="+endmsg+"&distances="+distances+"&beginmsg_txt="+beginaddr+"&endmsg_txt="+endaddr+"&taxis="+taxis;
      }
      
    </script>  
  </head>
    
  <body>
  <!-- start -->
   <div style="position:absolute; right:0;top:20px;width:390px; z-index:1200;">Start place：<input type="text" id="beginaddr" style="width: 289px;" >&nbsp;<br>Destination：<input type="text" id="endaddr" style="width: 289px;">&nbsp;<br>
   <!-- Start time： --><input type="text" id="date" name="date" onFocus="WdatePicker({lang:'en'})" style="width: 160px;display:none;" disabled="disabled">&nbsp;&nbsp;<br>
   Carnum：<select id="taxis" name="taxis">
      <%
 
    List<Car> newslist=(List<Car>)request.getAttribute("cars"); 
    %>
    
    <%
    if(newslist.size()!=0){
      for(int i=0;i<newslist.size();i++){
      Car news =new Car(); 
       news = newslist.get(i);   //record the value in newslist

     %>
               <option value="<%=newslist.get(i).getCarNum() %>"><%=newslist.get(i).getCarNum() %></option>
               
                  <%
      }
    }else{
     %>
     <option value="error">there is no car available</option>
         <%
    }
    %>
   </select>&nbsp;<br>
   <script type="text/javascript">
	ABL('ui-datepicker','ui-slider',function(){
		var d = new Date();
		$('#date').datepicker({
			showTimes:true,
			hourOptions:{ value:d.getHours() },
			minOptions:{ value:d.getMinutes() }
		}); 
	});
	</script>
   <br>
   <input type="button" value="submit" onclick="sub()">&nbsp;
   <input type="hidden" value="" id="distances" />
   <input type="hidden"  value="" id="beginmsg" />
   <input type="hidden"  value="" id="endmsg" />
   <input type="hidden"  value="" id="username" />
   <input type="hidden" value="" id="line_tag" /></div>
   <!-- end -->
   <br>
    <div id="container">
      <div id="directions" style="display: none"></div>
      <div id="googleMap"></div>
    </div>
  </body>
</html>