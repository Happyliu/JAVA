package org.suntaxi.web;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexSev extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public void doGet(HttpServletRequest req,HttpServletResponse res)
	 throws ServletException, IOException{
		 
		 
		 String username = req.getParameter("username");
		 HttpSession session = req.getSession();
		 String username_session = (String) session.getAttribute("username");
		 if(username.trim().length()!=0||username_session.trim().length()!=0){
			 res.sendRedirect("index.jsp?username="+username);
		 }else{

			 res.sendRedirect("login.html");
		 }
  

		 
		 }
		 
		 public void doPost(HttpServletRequest req,HttpServletResponse res)
		 throws ServletException, IOException{
		  
		  this.doGet(req,res);
		 
		 }
	

}
