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
	private static final String INSERT_ORDER_Q = "insert into order_t values(?, ?, ?)";
	private static final String INSERT_BOOKING_Q = "insert into booking values(?, ?, ?, ?, ?)";
	private static final String INSERT_DOG_CUT_Q = "insert into dog_cut VALUES (?, ?, ?)";
	private static final String FIND_BOOKING_BY_DATE_AND_EMPLOYEE_ID_Q = FIND_ALL_Q + " where emp_id = ? and date = ?" ;
	private static final String FIND_DOG_CUT_BY_ID_Q = "select comment, b_id, d_id from dog_cut where b_id = ?";
	private static final String FIND_BOOKING_ORDER_BY_ASC_Q = FIND_ALL_Q + " ORDER BY date ASC";
	
	private PreparedStatement findAllPS;
	private PreparedStatement findBookingOrderByAscPS;
	private PreparedStatement insertOrderPS;
	private PreparedStatement insertBookingPS;
	private PreparedStatement insertDogCutPS;
	private PreparedStatement findBookingByDateAndEmployeeIDPS;
	private PreparedStatement findDogCutByIDPS;
	
	private CustomerDB customerDB;
	private EmployeeDB employeeDB;
	private BookingTypeDB bookingTypeDB;
	private DogDB dogDB;
	
	public BookingDB() throws DataAccessException, SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		customerDB = new CustomerDB();
		employeeDB = new EmployeeDB();
		bookingTypeDB = new BookingTypeDB();
		dogDB = new DogDB();
		
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			insertOrderPS = con.prepareStatement(INSERT_ORDER_Q, Statement.RETURN_GENERATED_KEYS);
			insertBookingPS = con.prepareStatement(INSERT_BOOKING_Q, Statement.RETURN_GENERATED_KEYS);
			insertDogCutPS = con.prepareStatement(INSERT_DOG_CUT_Q);
			findBookingByDateAndEmployeeIDPS = con.prepareStatement(FIND_BOOKING_BY_DATE_AND_EMPLOYEE_ID_Q);
			findDogCutByIDPS = con.prepareStatement(FIND_DOG_CUT_BY_ID_Q);
			findBookingOrderByAscPS = con.prepareStatement(FIND_BOOKING_ORDER_BY_ASC_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Could not preparedStatement", e);
		}
	}
	
	/**
	 * This method checks if the current booking could have any conflicts with bookings already in our database. 
	 * A local variable res is created. 
	 * A local variable otherBookings is created. 
	 * bStartTime and bEndTime is created, and gets the start and endtime from our current booking.
	 *  
	 * @param b
	 * @return true or false, depending on if the booking is conflicting with others. 
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private boolean confirmAvailability(Booking b) throws DataAccessException, SQLException {
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
	
	protected boolean checkConflict(LocalTime bStartTime, LocalTime bEndTime, LocalTime cbStartTime, LocalTime cbEndTime) {
		boolean res = bStartTime.compareTo(cbStartTime) >= 0 && bStartTime.compareTo(cbEndTime) < 0
				|| bEndTime.compareTo(cbStartTime) > 0 && bStartTime.compareTo(cbStartTime) <= 0;
			return res;
	}
	
	
	
	/**
	 * This method is used for inserting created bookings. 
	 * A local variable res is created with the type boolean. 
	 * A method confirmAvailability is called, to check if the date and employeeNO we want to insert, 
	 * are conflicting with a booking already in the database. 
	 * The method checks if the customerType is "Dog" or "Person"
	 * If the customerType is "Dog" the method goes into the first if statement, and the persists the dog cut. 
	 * If the customerType is "Person", the method goes into the second if statement and persists the booking
	 *@param booking b
	 *@returns either true or false, depending on if the inserting has gone through. 
	 *@throws DataAccesException
	 *@throws SQLException
	 */
	@Override
	public boolean insertBooking(Booking b) throws DataAccessException, SQLException {
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
			} catch (SQLException e) {
				DBConnection.getInstance().rollbackTransaction();
				throw new DataAccessException("Could not save booking or dog cut", e);
			}
		}
		return res;
	}
	
	
	/**
	 * This method is used for finding all bookings. 
	 * A local variable res is created with the type List. 
	 * The method then calls the buildObjects method
	 * @return a list of bookings
	 *@throws DataAccesException
	 *@throws SQLException
	 */
	@Override
	public List<Booking> findAllBookings() throws DataAccessException, SQLException {
		List<Booking> res = new ArrayList<>();
		ResultSet rs = findAllPS.executeQuery();
		res = buildObjects(rs);
		return res;
	}
	
	
	/**
	 * This method is used to finding all bookings by asc order. 
	 * A local variable res is created with the type List
	 * The method calls the buildObjects method. 
	 * @returns list of bookings
	 * @throws DataAccesException
	 * @throws SQLException
	 */
	@Override
	public List<Booking> findAllBookingsOrderByAsc() throws DataAccessException, SQLException {
		List<Booking> res = new ArrayList<>();
		ResultSet rs = findBookingOrderByAscPS.executeQuery();
		res = buildObjects(rs);
		return res;
	}
	
	
	/**
	 * This method is used for finding current bookings in the database by date and employeeID. 
	 * A local variable res is created with the type List. 
	 * The method calls the build object method. 
	 *@param date 
	 *@param employeeID
	 *@returns list of bookings
	 *@throws DataAccesException
	 *@throws SQLException
	 */
	public List<Booking> findAvailableTime(LocalDate date, int employeeID) throws DataAccessException, SQLException {
		List<Booking> res = new ArrayList<>();
		findBookingByDateAndEmployeeIDPS.setInt(1, employeeID);
		findBookingByDateAndEmployeeIDPS.setDate(2, Date.valueOf(date));
		try {
			ResultSet rs = findBookingByDateAndEmployeeIDPS.executeQuery();
			res = buildObjects(rs);
		} catch (Exception e) {
			throw new DataAccessException("Could not find available time", e);
		}
		
		return res;
	}
	
	/**
	 * This method is used fir building employee objects. 
	 * A local variable res is created with the type List. 
	 * The method continues to add employees to the list of employees until there is no more employees. 
	 * @param rs
	 * @return list of bookings. 
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private List<Booking> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		List<Booking> res = new ArrayList<>();
		Booking b = buildObject(rs);
		while(b != null) {
			res.add(b);
			b = buildObject(rs);
		}
		return res;
	} 
	
/**
 * This method is used for building booking and dogcut objects. 
 * The method checks the ResultSets customerType, if it is person the method goes into the first if statement,
 * if it is dog the method goes into the else statement. 
 * @param rs
 * @return returns either a booking or a dog cut. 
 * @throws DataAccessException
 * @throws SQLException
 */
 Booking buildObject(ResultSet rs) throws DataAccessException, SQLException {
		Booking res = null;
		try {
			if(rs.next()) {
				if(rs.getString("customer_type").equals("Person")) {
				res = new Booking(
						rs.getDate("date").toLocalDate(),
						customerDB.findCustomerByID(rs.getInt("c_id")),
						rs.getTime("start_time").toLocalTime(),
						employeeDB.findEmployeeByID(rs.getInt("emp_id")),
						bookingTypeDB.findBookingTypeByID(rs.getInt("bt_id")),
						rs.getString("customer_type")
						);
				} else {
					int bit = rs.getInt("booking_id");
					ResultSet nrs = dogCutInBooking(bit);
					if (nrs.next()) {
						
					
					//ResultSet nrs = dogCutInBooking(rs.getInt("b_id"));
					res = new DogCut(
							rs.getDate("date").toLocalDate(),
							customerDB.findCustomerByID(rs.getInt("c_id")),
							rs.getTime("start_time").toLocalTime(),
							employeeDB.findEmployeeByID(rs.getInt("emp_id")),
							bookingTypeDB.findBookingTypeByID(rs.getInt("bt_id")),
							rs.getString("customer_type"),
							dogDB.findDogByID(nrs.getInt("d_id")),
							nrs.getString("comment")
				
							);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Could not build booking", e);
		}
		
		return res;
	}
	
 	/**
 	 * This method is used fir finding dogCut by bookingID. 
 	 * @param bookingID b_id
 	 * @return what is found in the database by the querry. 
 	 * @throws DataAccessException
 	 * @throws SQLException
 	 */
	private ResultSet dogCutInBooking(int b_id) throws DataAccessException, SQLException {
		findDogCutByIDPS.setInt(1, b_id);
		return findDogCutByIDPS.executeQuery();
	}
}



	