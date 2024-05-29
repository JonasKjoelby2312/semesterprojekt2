package controller;

import java.util.ArrayList;
import java.util.List;
import db.BookingPriceDAO;
import db.BookingPriceDB;
import db.BookingTypeDB;
import db.BookingTypeDAO;
import model.Booking;
import model.BookingPrice;
import model.BookingType;

/*
 * This Class is for handling the different types of bookings 
 * and which prices each booking contains.
 * It uses the methods from the DAO interface 
 * to return objects from the database.
 */

public class BookingTypeController {
	private BookingTypeDAO bookingTypeDB;
	private BookingPriceDAO bookingPriceDB;
	
	public BookingTypeController() throws Exception {
		bookingTypeDB = new BookingTypeDB();
		bookingPriceDB = new BookingPriceDB();
	}
	
	/*
	 * This method is for finding all the different booking types 
	 * which is persisted in the database.
	 */
	public List<BookingType> findAllBookingTypes() throws Exception {
		return bookingTypeDB.findAllBookingTypes();
	}
	
	/*
	 * This method is for finding a specific booking type by its ID in the database.
	 */
	public BookingType findBookingTypeByBookingTypeID(int bookingTypeID) throws Exception {
		return bookingTypeDB.findBookingTypeByID(bookingTypeID);
	}
	
	/*
	 * This method is for finding a booking price by using 
	 * the booking type which contains a booking price.
	 */
	public BookingPrice findBookingPriceByBookingTypeID(int bookingTypeID) throws Exception {
		BookingPrice bookingPrice = bookingPriceDB.findBookingPriceByBookingTypeID(bookingTypeID);
		
//		BookingPrice res = null;
//		for(BookingPrice bp : bookingPrices) {
//			if(res == null) {
//				res = bp;
//			} else if(res.getStartDate().compareTo(bp.getStartDate()) < 0) {
//				res = bp;
//			}
//		}
		return bookingPrice;
	}
}
