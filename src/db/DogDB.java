package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import model.Dog;

public class DogDB implements DogDAO{
	private static final String FIND_ALL_DOGS_OF_CUSTOMER_Q = "select dog_id, name, dog_description, c_id from dog";
	private static final String FIND_DOG_BY_PHONENR_AND_DOGNAME_Q = FIND_ALL_DOGS_OF_CUSTOMER_Q + " where c_id = ? and name = ?";
	private static final String FIND_DOG_BY_ID_Q = "select dog_id, name, dog_description, c_id from dog where dog_id = ?";
	
	//private PreparedStatement findAllDogsOfCustomerPS;
	private PreparedStatement findDogByPhoneNoAndDogName;
	private PreparedStatement findDogByIDPS;
	
	
	
	public DogDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			findDogByPhoneNoAndDogName = con.prepareStatement(FIND_DOG_BY_PHONENR_AND_DOGNAME_Q);
			findDogByIDPS = con.prepareStatement(FIND_DOG_BY_ID_Q); 
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statements", e);
		}
	}
	
	
	
	
	/**
	 * This method is used for finding a dog by dogname, and a customer. 
	 * @param c is a Customer
	 * @param dogName
	 * @return a dog object 
	 * @throws DataAccessException, SQLException
	 */
	@Override
	public Dog findDogByPhoneNoAndDogName(Customer c, String dogName) throws DataAccessException, SQLException {
		Dog res = null;
		findDogByPhoneNoAndDogName.setInt(1, c.getCustomerID());
		findDogByPhoneNoAndDogName.setString(2, dogName);
		
		ResultSet rs = findDogByPhoneNoAndDogName.executeQuery();
		res = buildObject(rs);
		
		c.addDog(res);
		return res;
	}
	
	/**
	 * This method is used for creating objects, when the data is retrieved from the database. 
	 * The method checks if the ResusltSet has more elements, and if it has we get all their values saved in a ResultSet. 
	 * @param rs
	 * @return the built object is returned from the method. 
	 * @throws DataAccessException
	 */
	private Dog buildObject(ResultSet rs) throws DataAccessException {
		Dog res = null;
		try {
			if(rs.next()) {
				res = new Dog(
						rs.getInt("dog_id"),
						rs.getString("name"),
						rs.getString("dog_description")
						);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return res;
	}
	
	/**
	 * This method is used for finding an dog for the dog cut. 
	 * @param d_id is the id for dog. 
	 * @return an dog object. 
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	public Dog findDogByID(int d_id) throws DataAccessException, SQLException {
		Dog res = null;
		findDogByIDPS.setInt(1, d_id);
		ResultSet rs = findDogByIDPS.executeQuery();
		res = buildObject(rs);
		return res;
		
	}
	
}
