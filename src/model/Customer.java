package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private int customerID;
	private String name;
	private String address;
	private String phoneNo;
	private String email;
	private List<Dog> dogs;
	
	
	public Customer(int customerID, String name, String address, String phoneNo, String email) {
		this.customerID = customerID;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		dogs = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public void addDog(Dog d) {
		if(d != null) {
			dogs.add(d);
		}
	}
	public List<Dog> getDogs() {
		return new ArrayList<Dog>(dogs);
	}
}
