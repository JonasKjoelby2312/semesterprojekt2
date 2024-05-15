package ui;

import java.time.LocalDate;
import java.time.LocalTime;

import controller.OrderController;

public class Main {
	
	
	
	public static void main(String[] args) throws Exception {
		OrderController orderCtrl = new OrderController();
		
		System.out.println(orderCtrl.findAvailableTime(LocalDate.of(2024, 5, 16), 1));
		System.out.println("findAvailable Succes");
		orderCtrl.createBookingPerson(1, 1, "+4551938113", LocalDate.now(), LocalTime.of(12, 30));
		System.out.println("createBooking Succes");
		orderCtrl.completeBooking();
		System.out.println("completeBooking Succes");
	}

}
