package ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import controller.OrderController;
import db.BookingDB;
import model.Booking;

public class Main {
	
	
	
	public static void main(String[] args) throws Exception {
		OrderController orderCtrl = new OrderController();
		System.out.println(orderCtrl.findAvailableTime(LocalDate.of(2024, 5, 17), 1));
		//System.out.println(orderCtrl.createBookingPerson(1, 1, "+4551938113", LocalDate.now(), LocalTime.of(14, 50)));
		System.out.println(orderCtrl.createBookingDawg(2, 1, "+4551938113", "Christian", "Bider sig i skridtet", LocalDate.now(), LocalTime.of(14, 50)));
		System.out.println(orderCtrl.completeBooking());
		
		
		List<Booking> res = new ArrayList<>();
		BookingDB bdb = new BookingDB();
		res = bdb.findAvailableTime(LocalDate.of(2024, 5, 16), 1);
		System.out.println(res);
		
	}
}
