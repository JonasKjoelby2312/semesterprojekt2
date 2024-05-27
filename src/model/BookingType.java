package model;

/*
 * This is a constructor for BookingType with getters and setters. 
 */
public class BookingType {
	private int bookingTypeID;
	private String customerType;
	private String name;
	private String description;
	private int duration;
	private BookingPrice bookingPrice;
	
	public BookingType(int bookingTypeID, String customerType, String name, String description, int duration, BookingPrice bookingPrice) {
		this.bookingTypeID = bookingTypeID;
		this.customerType = customerType;
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.bookingPrice = bookingPrice;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getBookingTypeID() {
		return bookingTypeID;
	}
	public void setBookingTypeID(int bookingTypeID) {
		this.bookingTypeID = bookingTypeID;
	}
	public BookingPrice getBookingPrice() {
		return bookingPrice;
	}
	public void setBookingPrice(BookingPrice bookingPrice) {
		this.bookingPrice = bookingPrice;
	}
	
	
	
	
	
}
