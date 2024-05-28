package db;

import java.util.List;

import model.Customer;

public interface CustomerDAO {
	
	List<Customer> findAllCustomers() throws DataAccessException;
	Customer findCustomerByPhone(String phoneNo) throws DataAccessException;
}
