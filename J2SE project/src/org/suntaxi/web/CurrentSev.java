package org.suntaxi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suntaxi.bean.Orders;
import org.suntaxi.service.OrderSevice;
import org.suntaxi.util.Constant;
import org.suntaxi.util.DateUtil;
import org.suntaxi.util.EnumX;
import org.suntaxi.util.GetJwUtil;

public class CurrentSev extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GetJwUtil gutil;
	private DateUtil dateUtil;
	private Constant constant = new Constant();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("application/x-javascript;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        
        String orderid = req.getParameter("orderid");
        String begin = req.getParameter("begin");
        String end = req.getParameter("end");
        String username = req.getParameter("username");
        String Total_Distance = req.getParameter("Total_Distance");
        
        OrderSevice os = new OrderSevice();
		Orders order = new Orders();
		order = os.selectOrderByOrderId(orderid);
        int i = 0;
		try {

			i = gutil.findJw(20.00, Double.valueOf(Total_Distance), dateUtil.parseDatetime(order.getStartTime()));

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(i>=10){
        	out.println("error");
        	out.close();
        }else{
        	 StringBuffer json = new StringBuffer();
        	 if(order.getDistances().equals("line1")){
        		 json.append(EnumX.getOut("1-"+i));
        	 }else if(order.getDistances().equals("line2")){
        		 json.append(EnumX.getOut("2-"+i));
        	 }else{
        		 out.println("error");
        	 }
             
             out.println(json.toString());
             out.close();
        }
	
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}


}
