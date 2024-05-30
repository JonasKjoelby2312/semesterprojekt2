package controller;

import java.sql.SQLException;

import db.DataAccessException;
import db.DogDAO;
import db.DogDB;
import model.Customer;
import model.Dog;

public class DogController {
	private DogDAO dogDB;
	
	public DogController() throws DataAccessException {
		dogDB = new DogDB();
	}
	
	public Dog findDogByCustomerAndDogName(Customer c, String dogName) throws DataAccessException, SQLException {
		return dogDB.findDogByCustomerAndDogName(c, dogName);
	}
}
