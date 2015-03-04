package org.suntaxi.bean;


public class Orders {
	
	private int id;
	private String OrderId;
	private String CusName;
	private String StartTime;
	private String CarNum;  
	private String CusStartPlace;
	private String CusStartPlace_txt;
	private String Destination;
	private String Destination_txt;
	private String distances;
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public String getCusName() {
		return CusName;
	}
	public void setCusName(String cusName) {
		CusName = cusName;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getCarNum() {
		return CarNum;
	}
	public void setCarNum(String carNum) {
		CarNum = carNum;
	}
	public String getCusStartPlace() {
		return CusStartPlace;
	}
	public void setCusStartPlace(String cusStartPlace) {
		CusStartPlace = cusStartPlace;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public String getDistances() {
		return distances;
	}
	public void setDistances(String distances) {
		this.distances = distances;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCusStartPlace_txt() {
		return CusStartPlace_txt;
	}
	public void setCusStartPlace_txt(String cusStartPlace_txt) {
		CusStartPlace_txt = cusStartPlace_txt;
	}
	public String getDestination_txt() {
		return Destination_txt;
	}
	public void setDestination_txt(String destination_txt) {
		Destination_txt = destination_txt;
	}


}
