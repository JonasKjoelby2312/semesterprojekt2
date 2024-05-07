package db;

import java.util.List;

import model.BookingType;

public interface BookingTypeDao {
	
	List<BookingType>findAllBookingTypes() throws Exception;
	BookingType findBookingType(int bookingTypeNo) throws Exception;

}
