package db;

import model.Customer;
import model.Dog;

public interface DogDAO {
	Dog findDogByPhoneNoAndDogName(Customer c, String dogName) throws Exception;
}
