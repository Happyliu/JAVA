package org.suntaxi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suntaxi.service.RegSevice;

public class RegSev extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = req.getParameter("username");
		int gender = Integer.valueOf(req.getParameter("gender"));
		String brithday = req.getParameter("birthday");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		String phonenum = req.getParameter("phonenum");
        RegSevice regSevice = new RegSevice();
        regSevice.addUser(username, gender, brithday,phonenum,email,
    			 pwd);
		//System.out.println(brithday);
		resp.sendRedirect("regResult.html");
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	

}
