package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Booking;

public class BookingDB implements BookingDAO{//
	private static final String FIND_ALL_Q = "select booking_id, start_time, emp_id, bt_id, booking.type as b_type,ØØØØØ  from booking right outer join dogCut on ";
	private static final String FIND_BOOKING_BY_CUSTOMER_PHONE = FIND_ALL_Q + " where ";
	private static final String INSERT_BOOKING_Q = "insert into booking values(?, ?, ?, ?, ?)";
	
	private PreparedStatement findAllQPS;
	private PreparedStatement findBookingByCustomerPhonePS;
	
	private DogDB dogDB;
	private CustomerDB customerDB;
	private EmployeeDB employeeDB;
	private BookingTypeDB bookingTypeDB;
	
	public BookingDB() throws Exception {
		Connection con = DBConnection.getInstance().getConnection();
		customerDB = new CustomerDB();
		employeeDB = new EmployeeDB();
		bookingTypeDB = new BookingTypeDB();
		
		try {
			findAllQPS = con.prepareStatement(FIND_ALL_Q);
		} catch (SQLException e) {
		}
	}
	
	@Override
	public List<Booking> findAllBookings() {
		return null;
	}
	@Override
	public List<Booking> findAllBookingsByCustomerPhone(String no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean insertBooking(Booking b) {
		// TODO Auto-generated method stub
		return false;
	}
}
