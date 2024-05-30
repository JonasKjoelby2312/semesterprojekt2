package controller;

import java.sql.SQLException;

import db.DataAccessException;
import db.DogDAO;
import db.DogDB;
import model.Customer;
import model.Dog;

/*
 * DogController is for handling the different methods in DogDAO
 * which is used to return objects from the database.
 */
public class DogController {
	private DogDAO dogDB;
	
	public DogController() throws DataAccessException {
		dogDB = new DogDB();
	}
	
	/**
	 * This method is used to find a specific dog with its owner and name as parameters.
	 * 
	 * @param c
	 * @param dogName
	 * @return a Dog with a name matching the parameter, and owned by the customer given in the parameter as well.
	 * Can also return null if customer doesn't have a dog with given name.
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	public Dog findDogByCustomerAndDogName(Customer c, String dogName) throws DataAccessException, SQLException {
		return dogDB.findDogByCustomerAndDogName(c, dogName);
	}
}
