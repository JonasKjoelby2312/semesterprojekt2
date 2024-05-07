package model;

public class Employee {
	
	private String name; 
	private String companyPosition;
	private double salary;
	private int employeeNo;
	
	
	
	public Employee(String name, String companyPosition, double salary, int employeeNo) {
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
	
	
	
	

}
