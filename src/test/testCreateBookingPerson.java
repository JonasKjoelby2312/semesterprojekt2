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
		boolean currBooking = oc.createBookingPerson(1, 1, "51938114", 
				LocalDate.of(2024, 5, 30), LocalTime.of(14, 30));
				assertFalse(currBooking);	
	}
	
	void testCreateBookingPersonCorrectInfo() throws Exception {
		boolean currBooking = oc.createBookingPerson(1, 1, "51938113", 
				LocalDate.of(2024, 5, 30), LocalTime.of(14, 30));
				assertTrue(currBooking);		
	}

}
