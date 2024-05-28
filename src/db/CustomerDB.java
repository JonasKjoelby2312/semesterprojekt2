package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDB implements CustomerDAO {
	
	private static final String FIND_ALL_Q = "select customer_id, name, email, a_id, phone_no from customer";
	private static final String FIND_CUSTOMER_ADDRESS_Q = "select address_id, house_no, road_name, zip, city from address right outer join zip_city on zip = zipcode where address_id = ?";
	private static final String FIND_BY_PHONE_Q = FIND_ALL_Q + " where phone_no = ?";
	private static final String FIND_CUSTOMER_BY_ID_Q = FIND_ALL_Q + " where customer_id = ?";
	
	
	private PreparedStatement findAllPS;
	private PreparedStatement findCustomerAddressPS;
	private PreparedStatement findByPhonePS;
	private PreparedStatement findCustomerByID;
	
	public CustomerDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findCustomerAddressPS = con.prepareStatement(FIND_CUSTOMER_ADDRESS_Q);
			findByPhonePS = con.prepareStatement(FIND_BY_PHONE_Q);
			findCustomerByID = con.prepareStatement(FIND_CUSTOMER_BY_ID_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Prepare query at the moment, i could not - Yoda", e);
		}
	}
	
	public List<Customer> findAllCustomers() throws DataAccessException {
		List<Customer> res = new ArrayList<>();
		try {
			ResultSet rs = findAllPS.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not find customers", e);
		}
		return res;
	}
	

	@Override
	public Customer findCustomerByPhone(String phoneNo) throws DataAccessException {
		Customer res = null;
		try {
			findByPhonePS.setString(1, phoneNo);
			ResultSet rs = findByPhonePS.executeQuery();
			res = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not Find by Phone", e);
		}
		return res;
	}
	
	private List<Customer> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		ArrayList<Customer> res = new ArrayList<>();
		Customer c = buildObject(rs);
		while (c != null) {
			res.add(c);
			c = buildObject(rs);
		}
		return res;
	}
	
	private Customer buildObject(ResultSet rs) throws DataAccessException, SQLException {
		Customer c = null;
		if (rs.next()) {
				c = new Customer(
						rs.getInt("customer_id"),
						rs.getString("name"), 
						findAddress(rs.getInt("a_id")),
						rs.getString("phone_no"),
						rs.getString("email"));
		}
		return c;
	}
	
	private String findAddress(int addressId) throws DataAccessException, SQLException {
        String address = null;
        findCustomerAddressPS.setInt(1, addressId);
        try {
        	ResultSet rs = findCustomerAddressPS.executeQuery();
        	if(rs.next()) {
        		address = rs.getString("road_name") + " " + rs.getInt("house_no") + " " + rs.getInt("zip") + " " + rs.getString("city");
        	}
        } catch (Exception e) {
        	throw new DataAccessException("Could not find Customer address", e);
        }
		return address;
	}

	public Customer findCustomerByID(int id) throws DataAccessException, SQLException {
		Customer res = null;
		findCustomerByID.setInt(1, id);
		try {
			ResultSet rs = findCustomerByID.executeQuery();
			res = buildObject(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("Could not find customer by ID", e);
		}
		return res;
	}
}
