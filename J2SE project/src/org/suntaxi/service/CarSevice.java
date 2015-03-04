package org.suntaxi.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.suntaxi.bean.Car;
import org.suntaxi.bean.Orders;

import org.suntaxi.db.DBConn;

public class CarSevice {
	
	private DBConn db = new DBConn();

	public Car getCar(int id){
		
		db.getConn();
		Car c2 = new Car();
		try {
			String sql_txt = "SELECT id,carnum,status  from car WHERE id = "+id+" LIMIT 1;";
			ResultSet rs =  db.doSelect(sql_txt);
			while(rs.next()){	
				c2.setId(rs.getInt(1));
				c2.setCarNum(rs.getString(2));
				c2.setStatus(rs.getInt(3));

			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return c2;
	}
	
	
	
	public void updateCar(String carnum , int i){
		db.getConn();
		try {
			String sql_txt = "UPDATE car c SET c.status="+i+" WHERE c.carnum = '"+carnum+"';";
			db.doUpdate(sql_txt);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * add car information
	 * 
	 */
	public void addCarInfo(String carnum,String orderid,String lat,String lng,String currenttime){
		
		db.getConn();
   	    try {
   	    	
   	    	db.doInsert("insert into carlocation (carnum,orderid,lat,lng,currenttime) values ('"+carnum+"',"+orderid+",'"+lat+"','"+lng+
   	    			"','"+currenttime+"');");

			//db.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * 
	 */
	public List<Car> getCars() {
		db.getConn();
		List<Car> cs = new ArrayList<Car>();//cs means car status
		try {
			String sql_txt = "select id,carnum,status from car where status=0;";
			//System.out.println(sql_txt);
			ResultSet rs =  db.doSelect(sql_txt);
			while(rs.next()){
				Car c2 = new Car();
				//o2.setId(rs.getInt(1));
				c2.setId(rs.getInt(1));
				c2.setCarNum(rs.getString(2));
				c2.setStatus(rs.getInt(3));

				cs.add(c2);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cs;
	}


}
