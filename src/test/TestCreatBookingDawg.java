package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingTypeController;
import controller.CustomerController;
import controller.EmployeeController;
import controller.OrderController;
import model.Booking;

class TestCreatBookingDawg {
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
	void testCreateBookingDawgCorrect() throws Exception {
		boolean currBooking = oc.createBookingDawg(2, 1, "+4551938113", "Christian", "SØD KAT", LocalDate.of(2024, 5, 18), LocalTime.of(14, 30));
		assertTrue(currBooking);
	}
	void testCreateBookingDawgWrong() throws Exception {
		boolean currBooking = oc.createBookingDawg(2, 17, "+4551938113", "Christian", "SØD KAT", LocalDate.of(2024, 5, 18), LocalTime.of(14, 30));
		assertFalse(currBooking);
	}
}
