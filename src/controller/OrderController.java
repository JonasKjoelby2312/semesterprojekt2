package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import db.BookingDAO;
import db.BookingDB;
import db.DogDAO;
import db.DogDB;
import model.Booking;
import model.BookingPrice;
import model.BookingType;
import model.Customer;
import model.Dog;
import model.DogCut;
import model.Employee;

public class OrderController {
	private BookingTypeController bookingTypeCtrl;
	private EmployeeController employeeCtrl;
	private CustomerController customerCtrl;
	private BookingDAO bookingDB;
	private DogDAO dogDB;
	private Booking currBooking;
	private DogCut currDogCut;

	public OrderController() throws Exception {
		bookingTypeCtrl = new BookingTypeController();
		employeeCtrl = new EmployeeController();
		customerCtrl = new CustomerController();
		bookingDB = new BookingDB();
		dogDB = new DogDB();
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
		currDogCut = null;
		if(LocalDate.now().compareTo(date) < 0 || LocalDate.now().compareTo(date) == 0 && LocalTime.now().compareTo(startTime) < 0) {
			Customer c = customerCtrl.findCustomerByPhone(customerPhone);
			Employee e = employeeCtrl.findEmployeeByEmployeeNo(employeeNo);
			BookingType bt = bookingTypeCtrl.findBookingTypeByBookingTypeID(bookingTypeID);
			if(c != null && e != null && bt != null) {
				currBooking = new Booking(date, c, startTime, e, bt, "Person");
				res = true;
			}
		}
		return res;
	}
	
	public boolean createBookingDawg(int bookingTypeID, int employeeNo, 
			String customerPhone, String dogName, String comment,
			LocalDate date, LocalTime startTime) throws Exception {
		boolean res = false;
		currBooking = null;
		if(LocalDate.now().compareTo(date) < 0 || LocalDate.now().compareTo(date) == 0 && LocalTime.now().compareTo(startTime) < 0) {
			BookingType bt = bookingTypeCtrl.findBookingTypeByBookingTypeID(bookingTypeID);
			Customer c = customerCtrl.findCustomerByPhone(customerPhone);
			Employee e = employeeCtrl.findEmployeeByEmployeeNo(employeeNo);
			Dog d = dogDB.findDogByCustomerAndDogName(c, dogName);

			if(c != null && e != null && bt != null && d != null) {
				if(bt.getCustomerType().equals("Dog")) {
					currDogCut = new DogCut( date, c, startTime, e, bt, "Dog", d, comment);
					res = true;
				}
			}
		}
		return res;
	}
	
	public boolean completeBooking() throws Exception {
		boolean res = false;
		if(currBooking != null) {
			bookingDB.insertBooking(currBooking);
			currBooking = null;
			res = true;
		} else if(currDogCut != null) {
			bookingDB.insertDogCut(currDogCut);
			currDogCut = null;
			res = true;
		}
		return res;
	}
}
