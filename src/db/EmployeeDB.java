package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDB implements EmployeeDAO {
	
	private static final String FIND_ALL_Q = "select employee_id, name, company_position, salary, barber_type from employee";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where employee_id = ?";
	private PreparedStatement findAllPS;
	private PreparedStatement findByIdPS;
	
	public EmployeeDB() throws DataAccessException {

		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByIdPS = con.prepareStatement(FIND_BY_ID_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare qurey", e);
		}
	}

	
	/**
	 * This method is used fir finding all of our employees. 
	 * The method calls the buildObjects method.
	 *@return a list of employee objects
	 *@throws DataAccesException. 
	 */
	@Override
	public List<Employee> findAllEmployees() throws DataAccessException {
		ResultSet rs;
		try {
			rs = findAllPS.executeQuery();
			List<Employee> res = buildObjects(rs);
			return res;
		} catch(SQLException e) {
			throw new DataAccessException("Could not retrive all employees", e);
		}
		
	}
	
	
	/**
	 * This method is used for finding employees by employeeID
	 * The method takes the found data from the database, and calls the buildObject method. 
	 * @param employee id
	 * @return employee object
	 * @throws DataAccessException
	 */
	@Override 
	public Employee findEmployeeByID(int id) throws DataAccessException {
		Employee res = null;
		ResultSet rs;
		try {
			findByIdPS.setInt(1, id);
			rs = findByIdPS.executeQuery();
			res = buildObject(rs);
		} catch(SQLException e) {
			throw new DataAccessException("Could not find employee by employee_no", e);
		}
		return res;
	}
	
	/**
	 * This method is used for creating employee objects 
	 * The method checks if the ResusltSet have a next element. 
	 * @param rs
	 * @return employee object
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private Employee buildObject(ResultSet rs) throws DataAccessException, SQLException {
		Employee res = null;
		if(rs.next()) {
			res = new Employee(
					rs.getInt("employee_id"),
					rs.getString("name"),
					rs.getString("company_position"),
					rs.getInt("salary"),
					rs.getString("barber_type")
					);
		}
		return res;
	}
	
	/**
	 * This method is used for creating a list of employee objects.
	 * The method calls the buildObject method. 
	 * The method keeps adding employee objects to our list, until the local variable is null. 
	 * @param rs
	 * @return a list of employee objects. 
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private List<Employee> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		ArrayList<Employee> res = new ArrayList<>();
		Employee e = buildObject(rs);
		while (e != null) {
			res.add(e);
			e = buildObject(rs);
		}
		return res;
	}
}
		
