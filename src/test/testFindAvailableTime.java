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
import model.Booking;

class testFindAvailableTime {
	
	private static OrderController orderCtrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		orderCtrl = new OrderController();
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
	void testFindAvailableTime(LocalDate Date, int employeeNo) {
		
	}

}
