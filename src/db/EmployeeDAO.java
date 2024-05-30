package db;

import java.util.List;

import model.Employee;

public interface EmployeeDAO {

	/**
	 * This method is used for finding all employees in connected database.
	 * 
	 * @return a list of Employee objects
	 * @throws DataAccesException. 
	 */
	List<Employee> findAllEmployees() throws DataAccessException;
	
	/**
	 * This method is used for finding an Employee by matching their ID with parameter.
	 * 
	 * @param id
	 * @return an Employee object
	 * @throws DataAccessException
	 */
	Employee findEmployeeByID(int id) throws DataAccessException;
}
