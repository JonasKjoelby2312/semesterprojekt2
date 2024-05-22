package controller;

import java.util.List;

import db.EmployeeDAO;
import db.EmployeeDB;
import model.Employee;

public class EmployeeController {
	private EmployeeDAO employeeDB;
	
	public EmployeeController() throws Exception {
		employeeDB = new EmployeeDB();
	}
	
	public List<Employee> findAllEmployees() throws Exception {
		return employeeDB.findAllEmployees();
	}
	
	public Employee findEmployeeByID(int id) throws Exception {
		return employeeDB.findEmployeeByID(id);
	}
}
      