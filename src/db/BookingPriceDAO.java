package db;

import java.util.List;

import model.BookingPrice;

public interface BookingPriceDAO {
	BookingPrice findBookingPricesByBookingTypeID(int id) throws Exception;
	
}
