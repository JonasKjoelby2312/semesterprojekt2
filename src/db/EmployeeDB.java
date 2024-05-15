package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDB implements EmployeeDAO {
	
	private static final String FIND_ALL_Q = "select employee_id, name, company_position, salary, employee_no from employee";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where employee_no = ?";
	private static final String FIND_EMPLOYEE_BY_ID_Q = FIND_ALL_Q + " where employee_id = ?";
	private PreparedStatement findAllPS;
	private PreparedStatement findByIdPS;
	private PreparedStatement findEmployeeByIDPS;
	
	public EmployeeDB() throws Exception {

		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByIdPS = con.prepareStatement(FIND_BY_ID_Q);
			findEmployeeByIDPS = con.prepareStatement(FIND_EMPLOYEE_BY_ID_Q);
		} catch (SQLException e) {
			throw new Exception("Could not prepare qurey", e);
		}
	}

	@Override
	public List<Employee> findAllEmployees() throws Exception {
		ResultSet rs;
		try {
			rs = findAllPS.executeQuery();
			List<Employee> res = buildObjects(rs);
			return res;
		} catch(SQLException e) {
			throw new Exception("Could not retrive all employees", e);
		}
		
	}

	@Override
	public Employee findEmployeeByEmployeeNo(int number) throws Exception {
		Employee res = null;
		ResultSet rs;
		try {
			findByIdPS.setInt(1, number);
			rs = findByIdPS.executeQuery();
			res = buildObject(rs);
		} catch(SQLException e) {
			throw new Exception("Could not find employee by employee_no", e);
		}
		return res;
	}
	
	private Employee buildObject(ResultSet rs) throws SQLException {
		Employee res = null;
		if(rs.next()) {
			res = new Employee(
					rs.getInt("employee_id"),
					rs.getString("name"),
					rs.getString("company_position"),
					rs.getInt("salary"),
					rs.getInt("employee_no")
					);
		}
		return res;
	}
	
	private List<Employee> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Employee> res = new ArrayList<>();
		Employee e = buildObject(rs);
		while (e != null) {
			res.add(e);
			e = buildObject(rs);
		}
		return res;
	}

	public Employee findEmployeeByID(int id) throws Exception {
		Employee res = null;
		findEmployeeByIDPS.setInt(1, id);
		try {
			ResultSet rs = findEmployeeByIDPS.executeQuery();
			res = buildObject(rs);
		} catch (Exception e) {
			throw new Exception("Could not find employee");
		}
		return res;
	}
}
		
