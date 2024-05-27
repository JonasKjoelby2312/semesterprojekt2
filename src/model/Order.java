package model;

import java.time.LocalDate;

/*
 * This is a constructor for Order with getters and setters. 
 */
public abstract class Order {
	private int orderID;
	private LocalDate date;
	private double total;
	private Customer customer;
	
	public Order(LocalDate date, Customer customer) {
		this.date = date;
		this.customer = customer;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotal() {
		return calculateTotal();
	}
	
	protected abstract double calculateTotal();

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

}
