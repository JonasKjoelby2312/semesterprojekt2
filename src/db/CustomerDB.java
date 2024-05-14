package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDB implements CustomerDAO {
	
	private static final String FIND_ALL_Q = "select customer_id, name, email, a_id, phone_no, d_id from customer"; 
	private static final String FIND_BY_PHONE_Q = FIND_ALL_Q + " where phone_no = ?";
	private PreparedStatement findAllPS;
	private PreparedStatement findByPhonePS;
	
	public CustomerDB() throws Exception {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByPhonePS = con.prepareStatement(FIND_BY_PHONE_Q);
			
		} catch (SQLException e) {
			throw new Exception("Prepare query at the moment, i could not - Yoda", e);
		}
	}
	
	public List<Customer> findAllCustomers() throws Exception {
		List<Customer> res = new ArrayList<>();
		try {
			ResultSet rs = findAllPS.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new Exception("Could not find customers", e);
		}
		return res;
	}

	@Override
	public Customer findCustomerByPhone(String phoneNo) throws Exception {
		Customer res = null;
		try {
			findByPhonePS.setString(1, phoneNo);
			ResultSet rs = findByPhonePS.executeQuery();
			res = buildObject(rs);
		} catch (SQLException e) {
			throw new Exception("Could not Find by Phone", e);
		}
		return res;
	}
	
	private List<Customer> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Customer> res = new ArrayList<>();
		Customer c = buildObject(rs);
		while (c != null) {
			res.add(c);
			c = buildObject(rs);
		}
		return res;
	}
	
	private Customer buildObject(ResultSet rs) throws SQLException {
		Customer c = null;
		if (rs.next()) {
				c = new Customer(
						rs.getString("customer_id"),
						rs.getString("name"), 
						rs.getString("email"),
						rs.getString("phone_no"));
					
		}
		return c;
	}
}
