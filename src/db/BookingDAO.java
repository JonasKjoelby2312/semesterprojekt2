package db;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.Booking;
import model.DogCut;

public interface BookingDAO { 
	List<Booking> findAllBookings() throws DataAccessException, SQLException;
//	List<Booking> findAllBookingsByCustomerPhone(String no);
	boolean insertBooking(Booking b) throws DataAccessException, SQLException;
	List<Booking> findAvailableTime(LocalDate date, int employeeID) throws DataAccessException, SQLException;
	List<Booking> findAllBookingsOrderByAsc() throws DataAccessException, SQLException;

	
}
