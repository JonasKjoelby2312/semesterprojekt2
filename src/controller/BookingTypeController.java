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

public class BookingTypeController {
	private BookingTypeDAO bookingTypeDB;
	private BookingPriceDAO bookingPriceDB;
	
	public BookingTypeController() throws Exception {
		bookingTypeDB = new BookingTypeDB();
		bookingPriceDB = new BookingPriceDB();
	}
	
	public List<BookingType> findAllBookingTypes() throws Exception {
		return bookingTypeDB.findAllBookingTypes();
	}
	
	public BookingType findBookingType(int bookingTypeNo) throws Exception {
		return bookingTypeDB.findBookingType(bookingTypeNo);
	}
	
	public BookingPrice findBookingPriceByBookingTypeNo(int bookingTypeNo) throws Exception {
		List<BookingPrice> bookingPrices = bookingPriceDB.findBookingPricesByBookingTypeNo(bookingTypeNo);
		
		BookingPrice res = null;
		for(BookingPrice bp : bookingPrices) {
			if(res == null) {
				res = bp;
			} else if(res.getStartDate().compareTo(bp.getStartDate()) < 0) {
				res = bp;
			}
		}
		return res;
	}
}
