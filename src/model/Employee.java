package model;

public class Employee {
	
	private int employeeID;
	private String name; 
	private String companyPosition;
	private double salary;
	private int employeeNo;
	
	
	
	public Employee(int employeeID, String name, String companyPosition, double salary, int employeeNo) {
		this.employeeID =  employeeID;
		this.name = name;
		this.companyPosition = companyPosition;
		this.salary = salary;
		this.employeeNo = employeeNo;
	}
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCompanyPosition() {
		return companyPosition;
	}


	public void setCompanyPosition(String companyPosition) {
		this.companyPosition = companyPosition;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public int getEmployeeNo() {
		return employeeNo;
	}


	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}



	public int getEmployeeID() {
		return employeeID;
	}



	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	
	
	

}
