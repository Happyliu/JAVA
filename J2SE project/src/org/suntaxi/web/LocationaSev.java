package org.suntaxi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suntaxi.bean.Orders;
import org.suntaxi.service.CarSevice;
import org.suntaxi.service.OrderSevice;
import org.suntaxi.util.DateUtil;

public class LocationaSev extends HttpServlet {
	
	private DateUtil du;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("utf-8") ;   
		String orderid = req.getParameter("orderid");
		OrderSevice os = new OrderSevice();
		Orders order = os.selectOrderByOrderId(orderid);
		CarSevice cs = new CarSevice();
		
		cs.addCarInfo(order.getCarNum(), order.getOrderId(), order.getCusStartPlace().split(",")[0], order.getCusStartPlace().split(",")[1], du.currentDatetime());
		
		
		if(order!=null){
			req.setAttribute("order", order); 
			req.getRequestDispatcher("location.jsp?tag=1").forward(req,resp);
			//req.setAttribute("url2", "location.jsp?tag=1");
		}
		
		return;
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	
	

}