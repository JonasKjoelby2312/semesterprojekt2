package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.OrderController;

class testCreateBookingWithTime {
	
	private static OrderController oc; 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		oc = new OrderController();
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
	void testCreateBookingPersonWithTimeAndDate() throws Exception {
		boolean currBooking = oc.createBookingPerson(1, 1, "51938113", 
		LocalDate.of(2024, 7, 1), LocalTime.of(15, 30));
		assertNotNull(currBooking);
		boolean res = oc.completeBooking();
		assertFalse(res);
		
	}

}
