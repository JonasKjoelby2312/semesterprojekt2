package model;

public class BookingType {
	private int bookingTypeNo;
	private String type;
	private String name;
	private String description;
	private int duration;
	public BookingType(int bookingTypeNo, String type, String name, String description, int duration) {
		super();
		this.bookingTypeNo = bookingTypeNo;
		this.type = type;
		this.name = name;
		this.description = description;
		this.duration = duration;
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
	@Override
	public String toString() {
		return "BookingType [bookingTypeNo=" + bookingTypeNo + ", type=" + type + ", name=" + name + ", description="
				+ description + ", duration=" + duration + "]";
	}
	
	
	
	
	
}
