package db;

import java.time.LocalDate;
import java.util.List;

import model.Booking;
import model.DogCut;

public interface BookingDAO { 
//	List<Booking> findAllBookings();
//	List<Booking> findAllBookingsByCustomerPhone(String no);
	void insertBooking(Booking b) throws Exception;
	void insertDogCut(DogCut dc) throws Exception;
	List<Booking> findAvailableTime(LocalDate date, int employeeNo) throws Exception;
}
