package db;

import java.util.List;

import model.Employee;

public interface EmployeeDAO {

	List<Employee> findAllEmployees() throws Exception;
	Employee findEmployeeByID(int id) throws Exception;

}
