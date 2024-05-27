package model;

import java.time.LocalDate;
import java.time.LocalTime;
/*
 * This is a constructor for Customer with getters and setters
 * which extends Order so it can use the Order Objects(?). 
 */
public class Booking extends Order {
	private int bookingID;
	private LocalTime startTime;
	private Employee employee;
	private BookingType bookingType;
	private String customerType;

	
	public Booking (LocalDate date, Customer customer, 
			LocalTime startTime, Employee employee, BookingType bookingType, String customerType) {
		super(date, customer);
		this.startTime = startTime;
		this.employee = employee;
		this.bookingType = bookingType;
		this.customerType = customerType;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	@Override
	protected double calculateTotal() {
		return bookingType.getBookingPrice().getValue();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public BookingType getBookingType() {
		return bookingType;
	}

	public void setBookingType(BookingType bookingType) {
		this.bookingType = bookingType;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "Booking [startTime=" + startTime + ", employee=" + employee + ", bookingType=" + bookingType
				+ ", customerType=" + customerType + "]";
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
}
