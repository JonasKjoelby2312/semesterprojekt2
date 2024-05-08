package db;

import java.util.List;

import model.Booking;

public interface BookingDAO {
	List<Booking> findAllBookings();
	List<Booking> findAllBookingsByCustomerPhone(String no);
	boolean insertBooking(Booking b);
}
