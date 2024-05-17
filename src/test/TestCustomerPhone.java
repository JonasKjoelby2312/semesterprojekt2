package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.CustomerController;
import model.Customer;

class TestCustomerPhone {
	private static CustomerController cCtrl;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		cCtrl = new CustomerController();
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
	void testCustomerPhoneRightNumber() throws Exception {
		Customer res = cCtrl.findCustomerByPhone("+4551938113");
		assertNotNull(res);
		
		assertEquals("+4551938113", res.getPhoneNo());
	}
	
	@Test
	void testCustomerPhoneWrongNumber() throws Exception {
		Customer res = cCtrl.findCustomerByPhone("+4551938114");
		assertNull(res);
	}

}
