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

	public BookingType findBookingTypeByBookingTypeID(int bookingTypeID) throws Exception {
		return bookingTypeCtrl.findBookingTypeByBookingTypeID(bookingTypeID);
	}

	public BookingPrice findBookingPriceByBookingTypeID(int bookingTypeID) throws Exception {
		return bookingTypeCtrl.findBookingPriceByBookingTypeID(bookingTypeID);
	}

	public List<Booking> findAvailableTime(LocalDate date, int employeeNo) throws Exception {
		return bookingDB.findAvailableTime(date, employeeNo);
	}
	
	public boolean createBookingPerson(int bookingTypeID, int employeeNo, 
			String customerPhone, LocalDate date, LocalTime startTime) throws Exception {
		boolean res = false;
		if(LocalDate.now().compareTo(date) < 0 || LocalDate.now().compareTo(date) == 0 && LocalTime.now().compareTo(startTime) < 0) {
			Customer c = customerCtrl.findCustomerByPhone(customerPhone);
			Employee e = employeeCtrl.findEmployeeByEmployeeNo(employeeNo);
			BookingType bt = bookingTypeCtrl.findBookingTypeByBookingTypeID(bookingTypeID);
			currBooking = new Booking(date, c, startTime, e, bt, "Person");
			res = true;
		}
		return res;
	}
	
	public boolean completeBooking() throws Exception {
		boolean res = false;
		System.out.println("Out");
		if(currBooking != null) {
			System.out.println("In");
			bookingDB.insertBooking(currBooking);
			// TODO Invoice
			currBooking = null;
			res = true;
		}
		return res;
	}
}
