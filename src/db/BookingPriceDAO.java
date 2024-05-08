package db;

import java.util.List;

import model.BookingPrice;

public interface BookingPriceDAO {
	List<BookingPrice> findBookingPricesByBookingTypeNo(int id) throws Exception;
	
}
