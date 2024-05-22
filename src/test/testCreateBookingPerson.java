package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingTypeController;
import controller.CustomerController;
import controller.EmployeeController;
import controller.OrderController;
import db.BookingDB;
import model.Booking;
import model.BookingType;
import model.Customer;
import model.Employee;

class testCreateBookingPerson {
	
	private static OrderController oc;
	private Booking currBooking;
	private static CustomerController cc; 
	private static EmployeeController ec;
	private static BookingTypeController btc; 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		oc = new OrderController(); 
		cc = new CustomerController();
		ec = new EmployeeController();
		btc = new BookingTypeController();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateBookingPersonWrongInfo() throws Exception {
		//Første parameter er bookingTypeID, der ligger ikke en bookingType med id'et 17. 
		boolean currBooking = oc.createBookingPerson(1, 17, "+4551938113", LocalDate.of(2024, 5, 18), LocalTime.of(14, 30));
		assertFalse(currBooking);
		
	}
	
	void testCreateBookingPersonCorrectInfo() throws Exception {
		//Første parameter er bookingTypeID, der ligger en bookingType med id'et 1. 
		boolean currBooking = oc.createBookingPerson(1, 1, "+4551938113", LocalDate.of(2024, 5, 18), LocalTime.of(14, 30));
		assertTrue(currBooking);
		BookingDB bdb = new BookingDB();
		List<Booking> res = new ArrayList<>();
		res = bdb.findAvailableTime(LocalDate.of(2024, 5, 18), 1);
		
		assertEquals(res.get(0).getBookingID(), 1);
	
		
		
	}

}
