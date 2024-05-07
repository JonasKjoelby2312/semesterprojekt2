package controller;

import java.util.ArrayList;
import java.util.List;

import db.BookingPriceDao;
import db.BookingTypeDB;
import db.BookingTypeDao;
import model.Booking;
import model.BookingPrice;
import model.BookingType;

public class BookingTypeController {
	private BookingTypeDao bookingTypeDB;
	private BookingPriceDao bookingPriceDB;
	
	public BookingTypeController() {
		bookingTypeDB = new BookingTypeDB();
		bookingPriceDB = new BookingPriceDB();
	}
	
	public List<Booking> findAllBookingTypes() {
		return bookingTypeDB.findAllBookingTypes();
	}
	
	public BookingType findBookingType(int bookingTypeNo) {
		return bookingTypeDB.findBookingType(bookingTypeNo);
	}
	
	public List<BookingPrice> findBookingPriceByBookingTypeNo(int bookingTypeNo) {
		ArrayList<BookingPrice> bookingPrices = bookingPriceDB.findBookingPriceByBookingTypeNo(bookingTypeNo);
		
		BookingPrice res = null;
		for(BookingPrice bp : bookingPrices) {
			if(res == null) {
				res = bp;
			} else if(res.getDate()) {
				
			}
		}
	}
}
