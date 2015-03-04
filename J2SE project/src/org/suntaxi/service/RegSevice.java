package org.suntaxi.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suntaxi.bean.Customers;
import org.suntaxi.db.DBConn;

public class RegSevice {
	
	
	/*
	 * add new a user
	 */
	public boolean addUser(String username, int gender, String brithday,String phonenum, String Email,
			String pwd){
		//System.out.println(brithday);
		DBConn db=new DBConn();
   	    db.getConn();
   	    try {
   	    	
   	    	db.doInsert("insert into customers (cusname,password) values ('"+username+"','"+pwd+"');");
   	    	db.doInsert("insert into personalinfo (cusname,surname,gender,firstname,birthday) values ('"+username+"','"+phonenum+"','"+gender+"','"+Email+"','"+brithday+"');");
			//db.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	public List<Customers> checkUser(String username, String pwd){
		
		DBConn db=new DBConn();
   	    db.getConn();
   	    ResultSet rs = db.doSelect("select cusid,cusname,password from customers where cusname='"+username
   	    		+"' and password = '"+pwd+"';");
   	    System.out.println("select cusid,cusname,password from customers where cusname='"+username
   	    		+"' and password = '"+pwd+"';");
   	    List<Customers> cts = new ArrayList<Customers>();
   	   try {
		while(rs.next()){
			   Customers ct = new Customers();
			   ct.setCusId(rs.getInt(1));
			   ct.setCusName(rs.getString(2));
			   ct.setPassword(rs.getString(3));
			   cts.add(ct);
		   }
		//db.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return cts;
	}

}
