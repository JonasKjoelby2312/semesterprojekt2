package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingTypeController;
import model.BookingType;

class TestBookingTypeID {
	
	private static BookingTypeController bTCtrl;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bTCtrl = new BookingTypeController();
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
	void testCreateBookingTypeIDValid() throws Exception {
		BookingType res = bTCtrl.findBookingTypeByBookingTypeID(1);
		
		assertNotNull(res);
		assertEquals(1, res.getBookingTypeID());
		
	}

	@Test
	void testCreateBookingTypeIDInvalid() throws Exception {
		BookingType res = bTCtrl.findBookingTypeByBookingTypeID(4);
		assertNull(res);
		
		
	}
}
