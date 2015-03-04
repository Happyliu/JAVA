package org.suntaxi.bean;

public class Customers {
	
	private int CusId;
	private String CusName;
	private String Gender;
	private int Birthday;  
	private String Email;
	private String password;
	private int PhoneNum;
	private String Firstname;
	private String Surname;	
	
	public int getCusId() {
		return CusId;
	}
	public void setCusId(int cusId) {
		CusId = cusId;
	}
	public String getCusName() {
		return CusName;
	}
	public void setCusName(String cusName) {
		CusName = cusName;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public int getBirthday() {
		return Birthday;
	}
	public void setBirthday(int birthday) {
		Birthday = birthday;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getPhoneNum() {
		return PhoneNum;
	}
	public void setPhoneNum(int phonenum) {
		PhoneNum = phonenum;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
