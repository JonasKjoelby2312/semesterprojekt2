package db;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.Booking;
import model.DogCut;

public interface BookingDAO { 
	/**
	 * This method is used for finding all bookings in the connected database.
	 * @return a list of bookings
	 * @throws DataAccesException
	 * @throws SQLException
	 */
	List<Booking> findAllBookings() throws DataAccessException, SQLException;
	
	/**
	 * This method is used for inserting bookings into the connected database.
	 * @param booking b
	 * @returns either true or false, depending on if the insertion is a success or not. 
	 * @throws DataAccesException
	 * @throws SQLException
	 */
	boolean insertBooking(Booking b) throws DataAccessException, SQLException;
	
	/**
	 * This method is used for finding bookings containing a matching barber and date, given in parameters. 
	 * 
	 * @param date 
	 * @param employeeID
	 * @returns list of bookings
	 * @throws DataAccesException
	 * @throws SQLException
	 */
	List<Booking> findAvailableTime(LocalDate date, int employeeID) throws DataAccessException, SQLException;
	
	/**
	 * This method is used to find all bookings in the connected database, and sort them by ascending order. 
	 * 
	 * @returns list of bookings
	 * @throws DataAccesException
	 * @throws SQLException
	 */
	List<Booking> findAllBookingsOrderByAsc() throws DataAccessException, SQLException;

	
}
