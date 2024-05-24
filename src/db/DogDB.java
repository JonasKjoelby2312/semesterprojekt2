package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Customer;
import model.Dog;

public class DogDB implements DogDAO{
	private static final String FIND_ALL_DOGS_OF_CUSTOMER_Q = "select dog_id, name, dog_description, c_id from dog";
	private static final String FIND_DOG_BY_PHONENR_AND_DOGNAME_Q = FIND_ALL_DOGS_OF_CUSTOMER_Q + " where c_id = ? and name = ?";
	//private PreparedStatement findAllDogsOfCustomerPS;
	private PreparedStatement findDogByPhoneNoAndDogName;
	
	public DogDB() throws Exception {
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			findDogByPhoneNoAndDogName = con.prepareStatement(FIND_DOG_BY_PHONENR_AND_DOGNAME_Q);
		} catch (Exception e) {
			throw new Exception("Could not prepare statements");
		}
	}
	
	@Override
	public Dog findDogByPhoneNoAndDogName(Customer c, String dogName) throws Exception {
		Dog res = null;
		findDogByPhoneNoAndDogName.setInt(1, c.getCustomerID());
		findDogByPhoneNoAndDogName.setString(2, dogName);
		
		ResultSet rs = findDogByPhoneNoAndDogName.executeQuery();
		res = buildObject(rs);
		
		c.addDog(res);
		return res;
	}

	private Dog buildObject(ResultSet rs) throws Exception {
		Dog res = null;
		try {
			if(rs.next()) {
				res = new Dog(
						rs.getInt("dog_id"),
						rs.getString("name"),
						rs.getString("dog_description")
						);
			}
		} catch (Exception e) {
			throw new Exception("Could not build object");
		}
		return res;
	}

	
}
