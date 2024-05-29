package db;

import java.util.List;

import model.Customer;

/**
 * Interface for accessing booking price data Provides method to get all
 * customers based on their phone no
 */

public interface CustomerDAO {

	
	/**
	 * Returns all customers from the database
	 * @return a list of customers
	 * @throws DataAccessException if a database access error appear
	 */
	List<Customer> findAllCustomers() throws DataAccessException;
	
	/**
	 * returns a specific customer by its phoneNO from the database.
	 * 
	 * @param phoneNo is the phone number of the customer to return
	 * @return The CustomerByPhone object that matches the given phoneNo, or null if not found
	 * @throws DataAccessException if a database access error appear
	 */
	Customer findCustomerByPhone(String phoneNo) throws DataAccessException;
}
