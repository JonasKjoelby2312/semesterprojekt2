package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import db.BookingDAO;
import db.BookingDB;
import db.DataAccessException;
import db.DogDAO;
import db.DogDB;
import model.Booking;
import model.BookingPrice;
import model.BookingType;
import model.Customer;
import model.Dog;
import model.DogCut;
import model.Employee;

/*
 * ---
 * It uses the methods from the BookingDAO and DogDAO interface 
 * to return objects from the database.
 */

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

	public List<Booking> findAvailableTime(LocalDate date, int employeeID) throws Exception {
		return bookingDB.findAvailableTime(date, employeeID);
	}
	
	public List<Booking> findAllBookings() throws Exception {
		return bookingDB.findAllBookings();
	}
	
	public List<Booking> findAllBookingsOrderByAsc() throws DataAccessException, SQLException {
		return bookingDB.findAllBookingsOrderByAsc();
	}
	/*
	 * This method is for creating a booking for a person.
	 */
	
	/**
	 * This method is used for creating bookings for persons. The method checks that the date is not
	 * before today, or before the current date and time. The method checks that the employee
	 * has the barber_type "person" to make sure the employee has the right set of cutting abilities
	 * The method then checks that neither customer, employee, and booking_type is not null
	 * @param bookingTypeID
	 * @param employeeID
	 * @param customerPhone
	 * @param date
	 * @param startTime
	 * @return either true or false, depending on if the booking has been created. 
	 * @throws Exception
	 */
	public boolean createBookingPerson(int bookingTypeID, int employeeID, 
			String customerPhone, LocalDate date, LocalTime startTime) throws Exception {
		boolean res = false;
		currDogCut = null;
		if(LocalDate.now().compareTo(date) < 0 || LocalDate.now().compareTo(date) == 0 && LocalTime.now().compareTo(startTime) < 0) {
			Employee e = employeeCtrl.findEmployeeByID(employeeID);
			if(e.getBarberType().equals("Person")) {
				Customer c = customerCtrl.findCustomerByPhone(customerPhone);
				BookingType bt = bookingTypeCtrl.findBookingTypeByBookingTypeID(bookingTypeID);
				if(c != null && e != null && bt != null) {
					currBooking = new Booking(date, c, startTime, e, bt, "Person");
					res = true;
				}
			}
		}
		return res;
	}
	/*
	 * This method is for creating a booking for a dog.
	 */
	
	/**
	 * This method is used for creating a booking for a dog cut. 
	 * The method checks that the date is not before today, or before the current date and time.
	 * We find an employee by employeeID, and if the employees barber_type is "dog", then we can go forward with the method. 
	 * The method checks that our employee, customer, booking_type and date is not NULL. 
	 * The method checks that the customer also has the customer_type is "dog"
	 * @param bookingTypeID
	 * @param employeeID
	 * @param customerPhone
	 * @param dogName
	 * @param comment
	 * @param date
	 * @param startTime
	 * @return either true or false, depending on if the booking has been created.
	 * @throws Exception
	 */
	public boolean createBookingDog(int bookingTypeID, int employeeID, 
			String customerPhone, String dogName, String comment,
			LocalDate date, LocalTime startTime) throws Exception {
		boolean res = false;
		currBooking = null;
		if(LocalDate.now().compareTo(date) < 0 || LocalDate.now().compareTo(date) 
				== 0 && LocalTime.now().compareTo(startTime) < 0) {
			Employee e = employeeCtrl.findEmployeeByID(employeeID);
			if(e.getBarberType().equals("Dog")) {
				BookingType bt = bookingTypeCtrl.findBookingTypeByBookingTypeID(bookingTypeID);
				Customer c = customerCtrl.findCustomerByPhone(customerPhone);
				Dog d = dogDB.findDogByCustomerAndDogName(c, dogName);
				if(c != null && e != null && bt != null && d != null) {
					if(bt.getCustomerType().equals("Dog")) {
						currDogCut = new DogCut( date, c, startTime, e, bt, "Dog", d, comment);
						res = true;
					}
				}
			}
		}
		return res;
	}
	
	
	/**
	 * This method takes the created sale, and persist it in our database. 
	 * The method checks if the current booking is a normal booking or a dog cut. 
	 * The method then calls the bookingDB, to insert the booking in our database. 
	 * @return either true or false, depending on if the booking has been inserted.
	 * @throws Exception
	 */
	public boolean completeBooking() throws Exception {
		boolean res = false;
		if(currBooking != null) {
			res = bookingDB.insertBooking(currBooking);
			currBooking = null;
		} else if(currDogCut != null) {
			res = bookingDB.insertBooking(currDogCut);
			currDogCut = null;
		}
		return res;
	}
}
