package model;

/*
 * This is a constructor for Employee with getters and setters. 
 */
 class Employee {	
	private int employeeID;
	private String name; 
	private String companyPosition;
	private double salary;
	private String barberType;
	
	public Employee(int employeeID, String name, String companyPosition, double salary, String barberType) {
		this.employeeID =  employeeID;
		this.name = name;
		this.companyPosition = companyPosition;
		this.salary = salary;
		this.barberType = barberType;
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

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getBarberType() {
		return barberType;
	}

	public void setBarberType(String barberType) {
		this.barberType = barberType;
	}
}
