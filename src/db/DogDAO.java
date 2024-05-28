package db;

import java.sql.SQLException;

import model.Customer;
import model.Dog;

public interface DogDAO {
	Dog findDogByPhoneNoAndDogName(Customer c, String dogName) throws DataAccessException, SQLException;
}
