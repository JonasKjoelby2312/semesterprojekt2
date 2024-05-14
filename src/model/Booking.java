package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking extends Order {
	private LocalTime startTime;
	private Employee employee;
	private BookingType bookingType;
	
	public Booking (LocalDate date, double total, Customer customer, 
			LocalTime startTime, Employee employee, BookingType bookingType) {
		super(date, total, customer);
		this.startTime = startTime;
		this.employee = employee;
		this.bookingType = bookingType;	
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
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
	
	
}
