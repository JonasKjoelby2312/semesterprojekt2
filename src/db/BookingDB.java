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
	private static final String FIND_ALL_Q = "select booking_id, start_time, emp_id, o_id, bt_id, customer_type, date, total, c_id from booking right outer join order_t on o_id = order_id";
	private static final String FIND_BOOKING_BY_CUSTOMER_PHONE = FIND_ALL_Q + " where c_id = ?";
	private static final String INSERT_ORDER_Q = "insert into order_t values(?, ?, ?)";
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
	
	private boolean confirmAvailability(Booking b) throws Exception {
		boolean res = true;
		List<Booking> otherBookings = findAvailableTime(b.getDate(), b.getEmployee().getEmployeeID());
		LocalTime bStartTime = b.getStartTime();
		LocalTime bEndTime = b.getStartTime().plusMinutes(b.getBookingType().getDuration());
		
		int index = 0;
		while(res && index < otherBookings.size()) {
			Booking currBooking = otherBookings.get(index);
			LocalTime cbStartTime = currBooking.getStartTime();
			LocalTime cbEndTime = cbStartTime.plusMinutes(currBooking.getBookingType().getDuration());
			
			if(checkConflict(bStartTime, bEndTime, cbStartTime, cbEndTime)) {
				res = false;
			}
			index++;
		}
		return res;
	}
	//TODO kog det ned - Test det
	protected boolean checkConflict(LocalTime bStartTime, LocalTime bEndTime, LocalTime cbStartTime, LocalTime cbEndTime) {
		boolean res = bStartTime.compareTo(cbStartTime) >= 0 && bStartTime.compareTo(cbEndTime) < 0
				|| bEndTime.compareTo(cbStartTime) > 0 && bStartTime.compareTo(cbStartTime) <= 0;
			return res;
	}
	
//	protected boolean checkConflict(LocalTime bStartTime, LocalTime bEndTime, Booking currBooking) {
//		boolean res = bStartTime.compareTo(currBooking.getStartTime()) >= 0 
//				&& bStartTime.compareTo(currBooking.getStartTime().plusMinutes(currBooking.
//			getBookingType().getDuration())) < 0 || bEndTime.compareTo(currBooking.
//			getStartTime()) > 0 && bStartTime.compareTo(currBooking.getStartTime()) <= 0;
//			return res;
//	}
	
	@Override
	public boolean insertBooking(Booking b) throws Exception {
		boolean res = false;
		if(confirmAvailability(b)) {
			res = true;
			try {
				DBConnection.getInstance().startTransaction();
				
				insertOrderPS.setDate(1, Date.valueOf(b.getDate()));
				insertOrderPS.setDouble(2, b.getTotal());
				insertOrderPS.setInt(3, b.getCustomer().getCustomerID());
				
				int ID = DBConnection.getInstance().executeInsertWithIdentity(insertOrderPS);
				
				insertBookingPS.setTime(1, Time.valueOf(b.getStartTime()));
				insertBookingPS.setInt(2, b.getEmployee().getEmployeeID());
				insertBookingPS.setInt(3, ID);
				insertBookingPS.setInt(4, b.getBookingType().getBookingTypeID());
				insertBookingPS.setString(5, b.getCustomerType());
				
				if(b.getCustomerType().equals("Dog")) {
					DogCut dc = (DogCut) b;
					
					int bookingID = DBConnection.getInstance().executeInsertWithIdentity(insertBookingPS);
					
					insertDogCutPS.setString(1, dc.getComment());
					insertDogCutPS.setInt(2, bookingID);
					insertDogCutPS.setInt(3, dc.getDog().getDogID());
					
					insertDogCutPS.executeUpdate();
				} else {
					insertBookingPS.executeUpdate();
				}
				
				DBConnection.getInstance().commitTransaction();
			} catch (Exception e) {
				DBConnection.getInstance().rollbackTransaction();
				throw new Exception("Could not save booking or dog cut");
			}
		}
		return res;
	}
	
	public List<Booking> findAvailableTime(LocalDate date, int employeeID) throws Exception {
		List<Booking> res = new ArrayList<>();
		findBookingByDateAndEmployeeIDPS.setInt(1, employeeID);
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
