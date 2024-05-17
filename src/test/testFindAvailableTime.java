package test;

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
	void testFindAvailableTime() throws Exception {
		List<Booking> res = new ArrayList<>();
		res = bookingDB.findAvailableTime(LocalDate.of(2024, 5, 16), 1);
		System.out.println(res);
		assertNotNull(res);
	}

}
