package db;

import java.sql.SQLException;

import model.Customer;
import model.Dog;

/**
 * An interface for accessing Dog related data in connected database.
 * Provides method to get specific dog by either its ID or its owner and name.
 */
public interface DogDAO {
	
	/**
	 * This method is used for finding a dog by its name and its owner, which is a customer. 
	 * 
	 * @param c is a Customer
	 * @param dogName
	 * @return a Dog object 
	 * @throws DataAccessException, SQLException
	 */
	Dog findDogByCustomerAndDogName(Customer c, String dogName) throws DataAccessException, SQLException;
	
	/**
	 * This method is used to find a Dog in the connected database with its ID.
	 * 
	 * @param d_id is the id for dog. 
	 * @return a Dog object.
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	Dog findDogByID(int d_id) throws DataAccessException, SQLException;
}
