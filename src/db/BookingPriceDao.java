package db;

import java.util.List;

import model.BookingPrice;

public interface BookingPriceDao {
	List<BookingPrice> findBookingPricesByBookingTypeNo(int id) throws Exception;
	
}
