package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Booking;
import model.BookingType;
import model.Customer;
import model.Employee;

public class BookingDB implements BookingDAO{//
	private static final String FIND_ALL_Q = "select booking_id, start_time, emp_id, o_id, bt_id, type from booking right outer join order on o_id = order_id";
	private static final String FIND_BOOKING_BY_CUSTOMER_PHONE = FIND_ALL_Q + " where c_id = ?";
	private static final String INSERT_ORDER_Q = "insert into order values(?, ?, ?, ?)";
	private static final String INSERT_BOOKING_Q = "insert into booking values(?, ?, ?, ?, ?)";
	private static final String FIND_BOOKING_BY_DATE_AND_EMPLOYEE_ID = FIND_ALL_Q + " where emp_id = ? and date = ?" ;
	
	
	private PreparedStatement findAllQPS;
	private PreparedStatement findBookingByCustomerPhonePS;
	private PreparedStatement insertOrderQPS;
	private PreparedStatement insertBookingQPS;
	private PreparedStatement findBookingByDateAndEmployeeID;
	
//	private DogDB dogDB;
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
			findBookingByCustomerPhonePS = con.prepareStatement(FIND_BOOKING_BY_CUSTOMER_PHONE);
			insertOrderQPS = con.prepareStatement(INSERT_ORDER_Q);
			insertBookingQPS = con.prepareStatement(INSERT_BOOKING_Q);
			findBookingByDateAndEmployeeID = con.prepareStatement(FIND_BOOKING_BY_DATE_AND_EMPLOYEE_ID);
		} catch (SQLException e) {
			throw new Exception("Could not preparedStatement");
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
	
	public List<Booking> findAvailableTime(LocalDate date, int employeeNo) throws Exception {
		List<Booking> res = new ArrayList<>();
		findBookingByDateAndEmployeeID.setInt(1, employeeNo);
		findBookingByDateAndEmployeeID.setDate(2, Date.valueOf(date));
		try {
			ResultSet rs = findBookingByDateAndEmployeeID.executeQuery();
			res = buildObjects(rs);
		} catch (Exception e) {
			throw new Exception("Could not find available time");
		}
		
		return res;
	}

	private List<Booking> buildObjects(ResultSet rs) throws Exception {
		List<Booking> res = new ArrayList<>();
		Booking b = buildObject(rs);
		while(b != null) {
			res.add(b);
			b = buildObject(rs);
		}
		return res;
	}

	private Booking buildObject(ResultSet rs) throws Exception {
		Booking res = null;
		try {
			if(rs.next()) {
				res = new Booking(
						rs.getDate("date").toLocalDate(),
						customerDB.findCustomerByID(rs.getInt("c_id")),
						rs.getTime("start_time").toLocalTime(),
						employeeDB.findEmployeeByID(rs.getInt("emp_id")),
						bookingTypeDB.findBookingTypeByID(rs.getInt("booking_type_id"))
						);
			}
		} catch (Exception e) {
			throw new Exception("Could not build booking");
		}
		
		return res;
	}
}
