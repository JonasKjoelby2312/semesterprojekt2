package controller;

import java.util.List;
import db.EmployeeDAO;
import db.EmployeeDB;
import model.Employee;

/*
 * EmployeeController is for handling the different methods in EmployeeDAO
 * which is used to return objects from the database.
 */

public class EmployeeController {
	private EmployeeDAO employeeDB;
	
	public EmployeeController() throws Exception {
		employeeDB = new EmployeeDB();
	}
	
	/**
	 * This method is used to find all Employees in the database
	 * @return a list of Employee objects
	 * @throws Exception
	 */
	public List<Employee> findAllEmployees() throws Exception {
		return employeeDB.findAllEmployees();
	}
	
	/**
	 * This method is used to find the Employee with the id matching the one written in the parameter
	 * 
	 * @param id
	 * @return an Employee object with an id matching the parameter
	 * @throws Exception
	 */
	public Employee findEmployeeByID(int id) throws Exception {
		return employeeDB.findEmployeeByID(id);
	}
}
      