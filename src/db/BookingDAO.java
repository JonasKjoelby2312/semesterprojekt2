package db;

import java.time.LocalDate;
import java.util.List;

import model.Booking;
import model.DogCut;

public interface BookingDAO { 
//	List<Booking> findAllBookings();
//	List<Booking> findAllBookingsByCustomerPhone(String no);
	boolean insertBooking(Booking b) throws Exception;
	List<Booking> findAvailableTime(LocalDate date, int employeeID) throws Exception;
}
