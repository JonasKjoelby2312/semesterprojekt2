package controller;

import java.util.List;
import db.EmployeeDAO;
import db.EmployeeDB;
import model.Employee;

/*
 * CustomerController is for handling the different methods in CustomerDAO
 * which is used to return objects from the database.
 */

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
      