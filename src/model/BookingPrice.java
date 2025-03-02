package model;

import java.time.LocalDate;

public class BookingPrice {
	private LocalDate startDate;
	private Double value;
	
	/*
	 * This is a constructor for BookingPrice with getters and setters. 
	 */
	
	public BookingPrice(LocalDate startDate, Double value) {
		this.startDate = startDate;
		this.value = value;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}

}