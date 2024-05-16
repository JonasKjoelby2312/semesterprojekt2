package db;

import model.Customer;
import model.Dog;

public interface DogDAO {
	Dog findDogByCustomerAndDogName(Customer c, String dogName) throws Exception;
}
