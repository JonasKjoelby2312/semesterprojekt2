package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BookingType;

/**
 * Manages database operations related to booking types
 * Provides method to find all booking types and a specific booking type by its ID
 */
public class BookingTypeDB implements BookingTypeDAO {
	private static final String FIND_ALL_Q = "select booking_type_id, customer_type, name, booking_type_description, duration from booking_type";
	private static final String FIND_BOOKING_TYPE_BY_ID_Q = FIND_ALL_Q + " where booking_type_id = ?";
	private PreparedStatement findAllPS;
	private PreparedStatement findByBookingTypeIDPS;
	private BookingPriceDB bookingPriceDB;
	
	/**
	 * Constructs a new BookingTypeDB instance and prepares SQL statements
	 * @throws DataAccessException if the database access error appear or the SQL statements cannot be prepared
	 * @throws SQLException if the database access error appear or the SQL statements cannot be prepared
	 */
	public BookingTypeDB() throws DataAccessException, SQLException {
		bookingPriceDB = new BookingPriceDB();
		Connection con = DBConnection.getInstance().getConnection();
		findAllPS = con.prepareStatement(FIND_ALL_Q);
	
		findByBookingTypeIDPS = con.prepareStatement(FIND_BOOKING_TYPE_BY_ID_Q);
	}

	/**
	 * Returns all booking types from the database
	 * @return a list of all booking types
	 * @throws DataAccessException if a database access error appear
	 */
	@Override
	public List<BookingType> findAllBookingTypes() throws DataAccessException {
		List<BookingType> res = new ArrayList<BookingType>();
		try {
			ResultSet rs = findAllPS.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not find bookingTypes", e);
		}
		return res;
	}
	
	/**
	 * Construct a List of BookingType objects from a ResultSet
	 * @param rs is the ResultSet containing booking type data
	 * @return a list of BookingType objects
	 * @throws DataAccessException if a database access error appear
	 * @throws SQLException if a database access error appear
	 */

	private List<BookingType> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		ArrayList<BookingType> res = new ArrayList<>();
		BookingType bt = buildObject(rs);
		while(bt != null) {
			res.add(bt);
			bt = buildObject(rs);
		}
		return res;
	}
	
    /**
     * Returns a specific booking type by its ID from the database.
     * @param bookingTypeID is the ID of the booking type to return
     * @return a BookingType object that matches to the given ID, or null if not found
     * @throws DataAccessException if a database access error appear
     */
	@Override
	public BookingType findBookingType(int bookingTypeID) throws DataAccessException {
		BookingType res = null;
		try {
			findByBookingTypeIDPS.setInt(1, bookingTypeID);
			ResultSet rs = findByBookingTypeIDPS.executeQuery();
			res = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not find bookingType by ID", e);
		}
		return res;
	}
/**
 * Construct a BookingType object from the ResultSet
 * @param rs is the ResultSet containing booking type data
 * @return The method returns a booking type object or null if the ResultSet is empty
 * @throws DataAccessException if a database access error appear
 * @throws SQLException if a database access error appear
 */
	private BookingType buildObject(ResultSet rs) throws DataAccessException, SQLException {
		BookingType res = null;
		if(rs.next()) {
			res = new BookingType(
					rs.getInt("booking_type_id"),
					rs.getString("customer_type"),
					rs.getString("name"),
					rs.getString("booking_type_description"),
					rs.getInt("duration"),
					bookingPriceDB.findBookingPricesByBookingTypeID(rs.getInt("booking_type_id"))
					);
		}
		return res;
	}
/**
 * Returns a specific booking type by its id from the database
 * @param id is the id of the booking type to return
 * @return A BookingType object that matches the given ID, or null if not found
 * @throws DataAccessException if a database access error appear
 * @throws SQLException if a database access error appear
 */
	public BookingType findBookingTypeByID(int id) throws DataAccessException, SQLException {
		BookingType res = null;
		findByBookingTypeIDPS.setInt(1, id);
		try {
			ResultSet rs = findByBookingTypeIDPS.executeQuery();
			res = buildObject(rs);
		} catch (Exception e) {
			throw new DataAccessException("Could not find booking type", e);
		}
		return res;
	}
	}

