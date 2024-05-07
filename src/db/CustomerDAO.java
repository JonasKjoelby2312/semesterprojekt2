package db;

import java.util.List;

import model.Customer;

public interface CustomerDAO {
	
	List<Customer> findAllCustomers() throws Exception;
	Customer findCustomerByPhone(String phoneNo) throws Exception;
}
