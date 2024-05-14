package model;

import java.time.LocalDate;

public class Invoice {
	private int invoiceNo;
	private LocalDate paymentDate;
	private double total;
	
	public Invoice(int invoiceNo, LocalDate paymentDate, double total) {
		this.invoiceNo = invoiceNo;
		this.paymentDate = paymentDate;
		this.total = total;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
