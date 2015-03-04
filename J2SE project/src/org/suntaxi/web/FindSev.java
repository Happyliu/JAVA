package org.suntaxi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suntaxi.bean.Orders;
import org.suntaxi.service.OrderSevice;

public class FindSev extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = req.getParameter("username");
		
		OrderSevice os = new OrderSevice();
		List<Orders> lists = new ArrayList<Orders>();
		lists=os.selectOrder(username);
		//System.out.println(lists.size());
		req.setAttribute("newslist", lists); //
		//resp.sendRedirect("find.jsp?username="+username);
		req.getRequestDispatcher("find.jsp?username="+username).forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}


}