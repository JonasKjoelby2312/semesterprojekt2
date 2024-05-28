package controller;

import java.util.List;

import db.CustomerDAO;
import db.CustomerDB;
import model.Customer;

/*
 * CustomerController is for handling the different methods in CustomerDAO
 * which is used to return objects from the database.
 */

public class CustomerController {
	private CustomerDAO customerDB;

	public CustomerController() throws Exception {
		customerDB = new CustomerDB();
	}

	public List<Customer> findAllCustomers() throws Exception {
		return customerDB.findAllCustomers();
	}

	public Customer findCustomerByPhone(String phoneNo) throws Exception {
		return customerDB.findCustomerByPhone(phoneNo);
	}
}
