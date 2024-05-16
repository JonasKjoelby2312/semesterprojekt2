package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Booking;
import model.BookingType;
import model.Customer;
import model.DogCut;
import model.Employee;

public class BookingDB implements BookingDAO{//
	private static final String FIND_ALL_Q = "select booking_id, start_time, emp_id, o_id, bt_id, customer_type, date, total, c_id, i_id from booking right outer join orde on o_id = order_id";
	private static final String FIND_BOOKING_BY_CUSTOMER_PHONE = FIND_ALL_Q + " where c_id = ?";
	private static final String INSERT_ORDER_Q = "insert into orde values(?, ?, ?, ?)";
	private static final String INSERT_BOOKING_Q = "insert into booking values(?, ?, ?, ?, ?)";
	private static final String INSERT_DOG_CUT_Q = "insert into dog_cut VALUES (?, ?, ?)";
	private static final String FIND_BOOKING_BY_DATE_AND_EMPLOYEE_ID = FIND_ALL_Q + " where emp_id = ? and date = ?" ;
	
	private PreparedStatement findAllQPS;
	private PreparedStatement findBookingByCustomerPhonePS;
	private PreparedStatement insertOrderPS;
	private PreparedStatement insertBookingPS;
	private PreparedStatement insertDogCutPS;
	private PreparedStatement findBookingByDateAndEmployeeIDPS;
	
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
			insertOrderPS = con.prepareStatement(INSERT_ORDER_Q, Statement.RETURN_GENERATED_KEYS);
			insertBookingPS = con.prepareStatement(INSERT_BOOKING_Q, Statement.RETURN_GENERATED_KEYS);
			insertDogCutPS = con.prepareStatement(INSERT_DOG_CUT_Q);
			findBookingByDateAndEmployeeIDPS = con.prepareStatement(FIND_BOOKING_BY_DATE_AND_EMPLOYEE_ID);
		} catch (SQLException e) {
			throw new Exception("Could not preparedStatement");
		}
	}
	
//	@Override
//	public List<Booking> findAllBookings() {
//		return null;
//	}
//	@Override
//	public List<Booking> findAllBookingsByCustomerPhone(String no) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public void insertBooking(Booking b) throws Exception {
		try {
			DBConnection.getInstance().startTransaction();
			
			insertOrderPS.setDate(1, Date.valueOf(b.getDate()));
			insertOrderPS.setDouble(2, b.getTotal());
			insertOrderPS.setInt(3, b.getCustomer().getCustomerID());
			insertOrderPS.setInt(4, 1); //invoice TODO
			
			int ID = DBConnection.getInstance().executeInsertWithIdentity(insertOrderPS);
			
			insertBookingPS.setTime(1, Time.valueOf(b.getStartTime()));
			insertBookingPS.setInt(2, b.getEmployee().getEmployeeID());
			insertBookingPS.setInt(3, ID);
			insertBookingPS.setInt(4, b.getBookingType().getBookingTypeID());
			insertBookingPS.setString(5, b.getCustomerType());
			
			insertBookingPS.executeUpdate();
			
			DBConnection.getInstance().commitTransaction();
		} catch (Exception e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new Exception("Could not save booking");
		}
	}
	
	public void insertDogCut(DogCut dc) throws Exception {
		try {
			DBConnection.getInstance().startTransaction();
			
			insertOrderPS.setDate(1, Date.valueOf(dc.getDate()));
			insertOrderPS.setDouble(2, dc.getTotal());
			insertOrderPS.setInt(3, dc.getCustomer().getCustomerID());
			insertOrderPS.setInt(4, 1); //invoice TODO
			
			int orderID = DBConnection.getInstance().executeInsertWithIdentity(insertDogCutPS);
			
			insertBookingPS.setTime(1, Time.valueOf(dc.getStartTime()));
			insertBookingPS.setInt(2, dc.getEmployee().getEmployeeID());
			insertBookingPS.setInt(3, orderID);
			insertBookingPS.setInt(4, dc.getBookingType().getBookingTypeID());
			insertBookingPS.setString(5, dc.getCustomerType());
			
			int bookingID = DBConnection.getInstance().executeInsertWithIdentity(insertBookingPS);
			
			insertDogCutPS.setString(1, dc.getComment());
			insertDogCutPS.setInt(2, bookingID);
			insertDogCutPS.setInt(3, dc.getDog().getDogID());
			
			insertBookingPS.executeUpdate();
			
			DBConnection.getInstance().commitTransaction();
		} catch (Exception e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new Exception("Could not save booking");
		}
	}
	
	public List<Booking> findAvailableTime(LocalDate date, int employeeNo) throws Exception {
		List<Booking> res = new ArrayList<>();
		findBookingByDateAndEmployeeIDPS.setInt(1, employeeNo);
		findBookingByDateAndEmployeeIDPS.setDate(2, Date.valueOf(date));
		try {
			ResultSet rs = findBookingByDateAndEmployeeIDPS.executeQuery();
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
						bookingTypeDB.findBookingTypeByID(rs.getInt("bt_id")),
						rs.getString("customer_type")
						);
			}
		} catch (Exception e) {
			throw new Exception("Could not build booking");
		}
		
		return res;
	}
}
