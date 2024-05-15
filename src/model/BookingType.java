package model;

public class BookingType {
	private int bookingTypeID;
	private int bookingTypeNo;
	private String type;
	private String name;
	private String description;
	private int duration;
	private BookingPrice bookingPrice;
	
	public BookingType(int bookingTypeID, int bookingTypeNo, String type, String name, String description, int duration, BookingPrice bookingPrice) {
		super();
		this.bookingTypeID = bookingTypeID;
		this.bookingTypeNo = bookingTypeNo;
		this.type = type;
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.bookingPrice = bookingPrice;
	}
	public int getBookingTypeNo() {
		return bookingTypeNo;
	}
	public void setBookingTypeNo(int bookingTypeNo) {
		this.bookingTypeNo = bookingTypeNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
