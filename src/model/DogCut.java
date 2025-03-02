package model;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * This is a constructor for DogCut with getters and setters. 
 */
public class DogCut extends Booking{
	private String comment;
	private Dog dog;
	
	public DogCut(LocalDate date, Customer customer, 
			LocalTime startTime, Employee employee, BookingType bookingType,
			String customerType, Dog dog, String comment) {
		super(date, customer, startTime, employee, bookingType, customerType);
		this.comment = comment;
		this.dog = dog;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}
}
