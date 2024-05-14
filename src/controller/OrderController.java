package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import db.BookingDAO;
import db.BookingDB;
import model.Booking;
import model.BookingPrice;
import model.BookingType;

public class OrderController {
	private BookingTypeController bookingTypeCtrl;
	private EmployeeController employeeCtrl;
	private CustomerController customerCtrl;
	private BookingDAO bookingDB;
	private Booking currBooking;

	public OrderController() throws Exception {
		bookingTypeCtrl = new BookingTypeController();
		employeeCtrl = new EmployeeController();
		customerCtrl = new CustomerController();
		bookingDB = new BookingDB();
	}

	public List<BookingType> findAllBookingTypes() throws Exception {
		return bookingTypeCtrl.findAllBookingTypes();
	}

	public BookingType findBookingTypeByBookingTypeNo(int bookingTypeNo) throws Exception {
		return bookingTypeCtrl.findBookingTypeByBookingTypeNo(bookingTypeNo);
	}

	public BookingPrice findBookingPriceByBookingTypeNo(int bookingTypeNo) throws Exception {
		return bookingTypeCtrl.findBookingPriceByBookingTypeNo(bookingTypeNo);
	}

	public List<Booking> findAvailableTime(LocalDate date, int employeeNo) throws Exception {
		return bookingDB.findAvailableTime(date, employeeNo);
	}
	
	public boolean createBookingPerson(int bookingTypeNo, int employeeNo, 
			String customerPhone, LocalDate date, LocalTime startTime) {
		boolean res = false;
		currBooking = new Booking(int bookingTypeNo, int employeeNo, String customerPhone, LocalDate date, LocalTime startTime);
		
		return res;
	}
}
