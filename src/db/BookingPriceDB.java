package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BookingPrice;

/**
 * Manages database operation related to booking prices
 * Provides method to find all booking prices and a specific booking price by booking type ID
 */
public class BookingPriceDB implements BookingPriceDAO {
	private static final String FIND_BOOKING_PRICE_BY_BOOKING_TYPE_ID_Q = "select Top 1 booking_price_id, start_date, booking_price_value, bt_id from booking_price where booking_price_id = ? Order by start_date Desc";

	private PreparedStatement findBookingPricesByBookingTypeID;

	/**
	 * Constructs a new BookingPriceDB instance and prepares SQL statements
	 * @throws DataAccessException if the database access error appear or the SQL statements cannot be prepared
	 * @throws SQLException if the database access error appear or the SQL statements cannot be prepared
	 */
	public BookingPriceDB() throws DataAccessException, SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		findBookingPricesByBookingTypeID = con.prepareStatement(FIND_BOOKING_PRICE_BY_BOOKING_TYPE_ID_Q);
	}

	/**
     * Returns a specific booking price by its booking type ID from the database.
     * @param bookingTypeID is the ID of the booking type to return with the booking price
     * @return a BookingPrice object that matches to the given booking type ID, or null if not found
     * @throws DataAccessException if a database access error appear
     * @throws SQLException if a database access error appear
     */
	@Override
	public BookingPrice findBookingPricesByBookingTypeID(int bookingTypeID) throws DataAccessException, SQLException {
		findBookingPricesByBookingTypeID.setInt(1, bookingTypeID);
		ResultSet rs = findBookingPricesByBookingTypeID.executeQuery();
		BookingPrice res = buildObject(rs);
		return res;
	}

	/**
	 * Construct a List of BookingPrice objects from a ResultSet
	 * @param rs is the ResultSet containing booking price data
	 * @return a list of BookingPrice objects
	 * @throws DataAccessException if a database access error appear
	 * @throws SQLException if a database access error appear
	 */
	private List<BookingPrice> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		ArrayList<BookingPrice> res = new ArrayList<>();
		BookingPrice bp = buildObject(rs);
		while (bp != null) {
			res.add(bp);
			bp = buildObject(rs);
		}
		return res;
	}

	/**
	 * Construct a BookingPrice object from the ResultSet
	 * @param rs is the ResultSet containing booking type data
	 * @return The method returns a booking price object or null if the ResultSet is empty
	 * @throws DataAccessException if a database access error appear
	 * @throws SQLException if a database access error appear
	 */
	private BookingPrice buildObject(ResultSet rs) throws DataAccessException, SQLException {
		BookingPrice res = null;
		if (rs.next()) {
			res = new BookingPrice(rs.getDate("start_date").toLocalDate(), rs.getDouble("booking_price_value"));
		}
		return res;
	}
}
