package db;

import java.sql.SQLException;
import java.util.List;

import model.BookingPrice;

/**
 * Interface for accessing booking price data
 * Provides method to get all booking prices based on their booking type ID
 */

public interface BookingPriceDAO {
	
	/**
	 * Returns booking price for a specific booking type ID from the database
	 * @param ID is the ID of the booking type
	 * @return The booking price object that matches to the given booking type ID, or null if not found
	 * @throws DataAccessException and SQLException if a database access error appear
	 */
	BookingPrice findBookingPricesByBookingTypeID(int id) throws DataAccessException, SQLException;
	
}
