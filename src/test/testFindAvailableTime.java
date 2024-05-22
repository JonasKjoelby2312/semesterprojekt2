package test;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.OrderController;
import db.BookingDB;
import model.Booking;

class testFindAvailableTime {
	
	private static OrderController orderCtrl;
	private static BookingDB bookingDB;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		orderCtrl = new OrderController();
		bookingDB = new BookingDB();
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
	void testFindAvailableTimeCorrect() throws Exception {
		//findAvailableTime() får en dato og et korrekt medarbejderID. 
		List<Booking> res = new ArrayList<>();
		res = bookingDB.findAvailableTime(LocalDate.of(2024, 5, 24), 1);
		assertNotNull(res);
	}
	
	@Test
	void testFindAvailableTimeWrong() throws Exception {
		//findAvailableTime() får en dato og et forkert medarbejderID. 
		List<Booking> res = new ArrayList<>();
		res = bookingDB.findAvailableTime(LocalDate.of(2024, 5, 16), 2);
		assertTrue(res.isEmpty());
	}

}
