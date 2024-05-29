package db;

import java.sql.SQLException;
import java.util.List;

import model.BookingType;

/**
 * A interface for accessing booking type data Provides method to get all
 * booking types and a specific booking type by its ID
 */
public interface BookingTypeDAO {

	/**
	 * Returns all booking types from the database
	 * 
	 * @return a list of booking types
	 * @throws DataAccessException if a database access error appear
	 */
	List<BookingType> findAllBookingTypes() throws DataAccessException;

	/**
	 * returns a specific booking type by its ID from the database.
	 * 
	 * @param id is the id of the booking type to return
	 * @return The BookingType object that matches the given ID, or null if not found
	 * @throws DataAccessException if a database access error appear
	 */
	BookingType findBookingTypeByID(int id) throws DataAccessException, SQLException;

}
