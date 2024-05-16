package db;

import model.Dog;

public interface DogDAO {
	Dog findDogByCustomerIDAndDogName(int id, String dogName) throws Exception;
}
