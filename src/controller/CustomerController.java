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

	/**
	 * This method is used to get all customers in the database.
	 * 
	 * @return a list filled with all the customers contained in the database.
	 * @throws Exception
	 */
	public List<Customer> findAllCustomers() throws Exception {
		return customerDB.findAllCustomers();
	}

	/**
	 * This method is used to find a customer with the matching phone number, given in the parameter.
	 * 
	 * @param phoneNo
	 * @return a customer with a matching phone number, specified in the parameter.
	 * @throws Exception
	 */
	public Customer findCustomerByPhone(String phoneNo) throws Exception {
		return customerDB.findCustomerByPhone(phoneNo);
	}
}
