package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import db.BookingDAO;
import db.BookingDB;
import model.Booking;
import model.BookingPrice;
import model.BookingType;
import model.Customer;
import model.Employee;

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
			String customerPhone, LocalDate date, LocalTime startTime) throws Exception {
		boolean res = false;
		if(LocalDate.now().compareTo(date) <= 0 && LocalTime.now().compareTo(startTime) < 0) {
			Customer c = customerCtrl.findCustomerByPhone(customerPhone);
			Employee e = employeeCtrl.findEmployeeByEmployeeNo(employeeNo);
			BookingType bt = bookingTypeCtrl.findBookingTypeByBookingTypeNo(bookingTypeNo);
			currBooking = new Booking(date, c, startTime, e, bt);
			res = true;
		}
		return res;
	}
	
	public boolean compelteBooking() {
		boolean res = false;
		if(currBooking != null) {
			bookingDB.insertBooking(currBooking);
			currBooking = null;
			res = true;
		}
		return res;
	}
}
