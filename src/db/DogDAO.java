package db;

import java.sql.SQLException;

import model.Customer;
import model.Dog;

public interface DogDAO {
	Dog findDogByCustomerAndDogName(Customer c, String dogname) throws DataAccessException, SQLException;
	Dog findDogByID(int d_id) throws DataAccessException, SQLException;
}
