package org.suntaxi.bean;

public class CarLocation {
	
	private int CarNum;
	private int OrderId;
	private String Lat;
	private String Lng;
	private String CurrentTime;
	public int getCarNum() {
		return CarNum;
	}
	public void setCarNum(int carNum) {
		CarNum = carNum;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public String getLat() {
		return Lat;
	}
	public void setLat(String lat) {
		Lat = lat;
	}
	public String getLng() {
		return Lng;
	}
	public void setLng(String lng) {
		Lng = lng;
	}
	public String getCurrentTime() {
		return CurrentTime;
	}
	public void setCurrentTime(String currentTime) {
		CurrentTime = currentTime;
	}
	
	

}
