package org.suntaxi.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suntaxi.bean.Orders;
import org.suntaxi.db.DBConn;

public class OrderSevice {

	private DBConn db = new DBConn();

	/*
	 * add new a order
	 */
	public boolean addOrder(String orderid, String cusname, String starttime,
			String carnum, String cusstartplace, String destination, String distances, String cusstartplace_txt, String destination_txt) {
		db.getConn();
		try {
			String sql_txt = "insert into orders (orderid,cusname,starttime,carnum,cusstartplace,destination,distances,cusstartplace_txt,destination_txt) values ('"
					+ orderid
					+ "','"
					+ cusname
					+ "','"
					+ starttime
					+ "','"
					+ carnum
					+ "','"
					+ cusstartplace
					+ "','"
					+ destination+"','"+distances
					+ "','"+cusstartplace_txt+"','"+destination_txt+"');";
			//System.out.println(sql_txt);
			db.doInsert(sql_txt);
			//db.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	/*
	 * 
	 */
	public List<Orders> selectOrder(String cusname) {
		db.getConn();
		List<Orders> os = new ArrayList<Orders>();
		try {
			String sql_txt = "select orderid,cusname,starttime,carnum,cusstartplace,destination,distances,cusstartplace_txt,destination_txt from orders where cusname='"
				+cusname+"' order by orderid desc limit 20;";
			//System.out.println(sql_txt);
			ResultSet rs =  db.doSelect(sql_txt);
			while(rs.next()){
				Orders o2 = new Orders();
				//o2.setId(rs.getInt(1));
				o2.setOrderId(rs.getString(1));
				o2.setCusName(rs.getString(2));
				o2.setStartTime(rs.getString(3));
				o2.setCarNum(rs.getString(4));
				o2.setCusStartPlace(rs.getString(5));
				o2.setDestination(rs.getString(6));
				o2.setDistances(rs.getString(7));
				o2.setCusStartPlace_txt(rs.getString(8));
				o2.setDestination_txt(rs.getString(9));
				os.add(o2);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return os;
	}
	
	
	/*
	 * 
	 */
	public Orders selectOrderByOrderId(String orderid) {
		db.getConn();
		Orders o2 = new Orders();
		try {
			String sql_txt = "select orderid,cusname,starttime,carnum,cusstartplace,destination,distances,cusstartplace_txt,destination_txt from orders where orderid='"
				+orderid+"' ;";
			System.out.println(sql_txt);
			ResultSet rs =  db.doSelect(sql_txt);
			while(rs.next()){

				o2.setOrderId(rs.getString(1));
				o2.setCusName(rs.getString(2));
				o2.setStartTime(rs.getString(3));
				o2.setCarNum(rs.getString(4));
				o2.setCusStartPlace(rs.getString(5));
				o2.setDestination(rs.getString(6));
				o2.setDistances(rs.getString(7));
				o2.setCusStartPlace_txt(rs.getString(8));
				o2.setDestination_txt(rs.getString(9));

			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return o2;
	}
	
	
	/*
	 * 
	 */
	public void delOrder(String orderId) {
		db.getConn();

		try {
			String sql_txt = "DELETE FROM orders WHERE orderid = '"+orderId+"';";
			//System.out.println(sql_txt);
			db.doDelete(sql_txt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * 
	 */
	public List<Orders> selectOrderByOrderid(String orderid) {
		db.getConn();
		List<Orders> os = new ArrayList<Orders>();
		try {
			String sql_txt = "select orderid,cusname,starttime,carnum,cusstartplace,destination,distances,cusstartplace_txt,destination_txt from orders where orderid='"
				+orderid+"' order by orderid desc limit 20;";
			//System.out.println(sql_txt);
			ResultSet rs =  db.doSelect(sql_txt);
			while(rs.next()){
				Orders o2 = new Orders();
				//o2.setId(rs.getInt(1));
				o2.setOrderId(rs.getString(1));
				o2.setCusName(rs.getString(2));
				o2.setStartTime(rs.getString(3));
				o2.setCarNum(rs.getString(4));
				o2.setCusStartPlace(rs.getString(5));
				o2.setDestination(rs.getString(6));
				o2.setDistances(rs.getString(7));
				o2.setCusStartPlace_txt(rs.getString(8));
				o2.setDestination_txt(rs.getString(9));
				os.add(o2);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return os;
	}


}
