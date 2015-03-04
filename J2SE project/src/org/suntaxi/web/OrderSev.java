package org.suntaxi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suntaxi.bean.Car;
import org.suntaxi.service.CarSevice;
import org.suntaxi.service.OrderSevice;
import org.suntaxi.util.BestUtil;
import org.suntaxi.util.DateUtil;

public class OrderSev extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateUtil du;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8"); 
		resp.setCharacterEncoding("utf-8");   
		String orderid = DateUtil.currentDatetime2();
		String cusname = req.getParameter("username");
		String starttime = req.getParameter("gotime");
		String carnum = new String(req.getParameter("taxis").getBytes("ISO-8859-1"),"utf-8");
		if(carnum.equals("error")){
			resp.sendRedirect("/error.jsp");
			return;
		}
		String cusstartplace = req.getParameter("beginmsg");
		String destination = req.getParameter("endmsg");
		String cusstartplace_txt = new String(req.getParameter("beginmsg_txt").getBytes("ISO-8859-1"),"utf-8");
		String destination_txt = new String(req.getParameter("endmsg_txt").getBytes("ISO-8859-1"),"utf-8") ;
		String distances = req.getParameter("distances"); // 
		// System.out.println(orderid+"/"+cusname+"/"+starttime+"/"+cusstartplace.substring(1,cusstartplace.length()-1).replace(",
		// ",",")+"/"+destination.substring(1,destination.length()-1).replace(",
		// ",",")+"/");
		OrderSevice os = new OrderSevice();
		CarSevice cs = new CarSevice();
		os.addOrder(orderid, cusname, du.currentDatetime(), carnum, cusstartplace
				.substring(1, cusstartplace.length() - 1).replace(", ", ","),
				destination.substring(1, destination.length() - 1).replace(
						", ", ","), distances,cusstartplace_txt,destination_txt);
		cs.updateCar(carnum, 1);
		resp.sendRedirect("orderResult.html?username=" + cusname + "&orderid="
				+ orderid);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}



}
