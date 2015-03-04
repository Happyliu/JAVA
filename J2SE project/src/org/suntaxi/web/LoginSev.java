package org.suntaxi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.suntaxi.bean.Customers;
import org.suntaxi.service.RegSevice;

public class LoginSev extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		
		
		RegSevice regSevice = new RegSevice();
		List<Customers> cts = new ArrayList<Customers>();
		cts = regSevice.checkUser(username, pwd);
		
		HttpSession session = req.getSession(true);
		session.setAttribute("username", username);
		
		if(cts.size()==1){
			resp.sendRedirect("loginResult.html?tag=1&username="+username);
		}else{
			resp.sendRedirect("loginResult.html?tag=0");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	


}
