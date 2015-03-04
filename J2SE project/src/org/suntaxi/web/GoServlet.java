package org.suntaxi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suntaxi.bean.Car;

import org.suntaxi.service.CarSevice;

public class GoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		CarSevice cs = new CarSevice();
		List<Car> cars = new ArrayList<Car>();
		cars = cs.getCars();
		req.setAttribute("cars", cars); //
		//resp.sendRedirect("find.jsp?username="+username);
		req.getRequestDispatcher("go.jsp?username="+username).forward(req,resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}


}
