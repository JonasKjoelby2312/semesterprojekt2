package db;

import java.util.List;

import model.Employee;

public interface EmployeeDAO {

	List<Employee> findAllEmployees() throws DataAccessException;
	Employee findEmployeeByID(int id) throws DataAccessException;
}
