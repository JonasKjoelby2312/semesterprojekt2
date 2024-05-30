package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import model.Dog;

/**
 * Manages database operations related to Dogs
 * Provides methods to find specific Dog either by ID or by customer and its name.
 */
public class DogDB implements DogDAO{
	private static final String FIND_DOG_BY_ID_Q = "select dog_id, name, dog_description, c_id from dog where dog_id = ?";
	private static final String FIND_DOG_BY_CUSTOMER_AND_DOG_NAME_Q = "select dog_id, name, dog_description, c_id from dog where c_id = ? and name = ?";
	
	//private PreparedStatement findAllDogsOfCustomerPS;
	private PreparedStatement findDogByIDPS;
	private PreparedStatement findDogByCustomerAndDogNamePS;
	
	
	public DogDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			findDogByIDPS = con.prepareStatement(FIND_DOG_BY_ID_Q); 
			findDogByCustomerAndDogNamePS = con.prepareStatement(FIND_DOG_BY_CUSTOMER_AND_DOG_NAME_Q);
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
	public Dog findDogByCustomerAndDogName(Customer c, String dogName) throws DataAccessException, SQLException {
		Dog res = null;
		findDogByCustomerAndDogNamePS.setInt(1, c.getCustomerID());
		findDogByCustomerAndDogNamePS.setString(2, dogName);
		
		ResultSet rs = findDogByCustomerAndDogNamePS.executeQuery();
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
	 * This method is used to find a Dog in the connected database with its ID.
	 * @param d_id is the id for dog. 
	 * @return a dog object. 
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	@Override
	public Dog findDogByID(int d_id) throws DataAccessException, SQLException {
		Dog res = null;
		findDogByIDPS.setInt(1, d_id);
		ResultSet rs = findDogByIDPS.executeQuery();
		res = buildObject(rs);
		return res;
		
	}
	
}
