package org.suntaxi.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suntaxi.bean.Orders;
import org.suntaxi.service.OrderSevice;
import org.suntaxi.util.DateUtil;

public class SearchSev extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateUtil du;
	private String currentPlace;//get the latlng
	private double p;
	private double speed = 20.0;//speed is 20km/h
	
	private double t1;
	private double t2;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//resp.setCharacterEncoding("utf-8");
		
		String cusname = req.getParameter("username");
		String orderid = req.getParameter("orderid");
		//System.out.println(cusname+";"+orderid);
		OrderSevice os = new OrderSevice();
		Orders order = new Orders();
		order = os.selectOrderByOrderId(orderid);
		
		if(order.getOrderId()!=null){
			try {
				p = getPValue(order.getStartTime(), order.getDistances(), speed);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentPlace = getNowPlace(order.getCusStartPlace(), order.getDestination(), p);
			req.setAttribute("order", order); //
			req.setAttribute("ordertag", t1>=t2?"1":"0"); //0:order is not finished 1:order is finished
			req.setAttribute("currentPlace", currentPlace); //
			req.getRequestDispatcher("location2.jsp?username=" + cusname + "&orderid="
					+ orderid).forward(req,resp);
			//req.setAttribute("url2", "location.jsp?tag=1");
		}
		return;
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Writer out =  resp.getWriter();
		//out.write("");
		doGet(req, resp);
	}
	
	
	
	/**
	 * calculate the P value
	 * @param beginTime start time
	 * @param allM total distance
	 * @param speed  20km/h
	 * @return
	 * @throws ParseException 
	 */
	public double getPValue(String beginTime,String allM,double speed) throws ParseException{
		
		t1 = du.xj(du.parseDatetime(du.currentDatetime()), du.parseDatetime(beginTime))/60;
		t2 = Double.valueOf(allM.substring(0, allM.indexOf("km")).trim())/speed;
		System.out.println("your car has ran£º"+t1);
		System.out.println("total time£º"+t2);
		System.out.println("current time£º"+du.currentDatetime());
		System.out.println(t1>=t2?"your order is finished":"your order is not finished");
		return t1/t2;
	}
	
	
	/**
	 * calculate the location
	 * @param beginPlace 
	 * @param endPlace  
	 * @param p the ratio of t1/t2
	 * @return
	 */
	public String getNowPlace(String beginPlace,String endPlace,double p){
		List<String> beginp = splistLatLng(beginPlace);
		List<String> endp = splistLatLng(endPlace);
		BigDecimal x = new BigDecimal(beginp.get(0));
		BigDecimal y = new BigDecimal(beginp.get(1));
		BigDecimal m = new BigDecimal(endp.get(0));
		BigDecimal n = new BigDecimal(endp.get(1));
		String lat = "";
		String lng = "";
		if(m.doubleValue()>x.doubleValue()&&n.doubleValue()>y.doubleValue()){
			lat = String.valueOf(x.doubleValue()+Math.abs(x.doubleValue()-m.doubleValue())*p);
			lng = String.valueOf(y.doubleValue()+Math.abs(y.doubleValue()-n.doubleValue())*p);
		}else if(m.doubleValue()>x.doubleValue()&&n.doubleValue()<y.doubleValue()){
			lat = String.valueOf(x.doubleValue()+Math.abs(x.doubleValue()-m.doubleValue())*p);
			lng = String.valueOf(y.doubleValue()-Math.abs(y.doubleValue()-n.doubleValue())*p);
		}else if(m.doubleValue()<x.doubleValue()&&n.doubleValue()<y.doubleValue()){
			lat = String.valueOf(x.doubleValue()-Math.abs(x.doubleValue()-m.doubleValue())*p);
			lng = String.valueOf(y.doubleValue()-Math.abs(y.doubleValue()-n.doubleValue())*p);
		}else if(m.doubleValue()<x.doubleValue()&&n.doubleValue()>y.doubleValue()){
			lat = String.valueOf(x.doubleValue()-Math.abs(x.doubleValue()-m.doubleValue())*p);
			lng = String.valueOf(y.doubleValue()+Math.abs(y.doubleValue()-n.doubleValue())*p);
		}else{
			System.out.println("error");
		}

		return lat+","+lng;
	}
	
	/**
	 *  separate the lat and lng
	 * @param str
	 * @return
	 */
	public List<String> splistLatLng(String str){
		List<String> lists = new ArrayList<String>();
		if(str==null||str.trim().equals("")||str.equals("null")){
			return null;
		}
		String[] ss = str.split(",");
		lists.add(ss[0]);//get latitude
		lists.add(ss[1]);//get longitude
		
		return lists;
	}

}
