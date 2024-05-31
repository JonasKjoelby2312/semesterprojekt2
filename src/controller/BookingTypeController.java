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
 * and which prices each BookingType contains.
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
	
	/**
	 * This method is for finding all the different booking types 
	 * which is persisted in the database.
	 * 
	 * @returns a list of BookingType objects.
	 * @throws Exception
	 */
	public List<BookingType> findAllBookingTypes() throws Exception {
		return bookingTypeDB.findAllBookingTypes();
	}
	
	/**
	 * This method is for finding a specific booking type by its ID in the database.
	 * 
	 * @returns a BookingType object.
	 * @throws Exception
	 */
	public BookingType findBookingTypeByBookingTypeID(int bookingTypeID) throws Exception {
		return bookingTypeDB.findBookingTypeByID(bookingTypeID);
	}
	
	/**
	 * This method is used to find the latest BookingPrice attached to the BookingType
	 * with the matching ID, which is specified in the parameter
	 * 
	 * @returns a BookingPrice object attached to the BookingType with the matching ID, specified in the parameter.
	 * @throws Exception
	 */
	public BookingPrice findBookingPriceByBookingTypeID(int bookingTypeID) throws Exception {
		return bookingPriceDB.findBookingPriceByBookingTypeID(bookingTypeID);
	}
}
