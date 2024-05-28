package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BookingPrice;

public class BookingPriceDB implements BookingPriceDAO {
	private static final String FIND_BOOKING_PRICE_BY_BOOKING_TYPE_ID_Q = "select Top 1 booking_price_id, start_date, booking_price_value, bt_id from booking_price where booking_price_id = ? Order by start_date Desc";

	private PreparedStatement findBookingPricesByBookingTypeNo;

	public BookingPriceDB() throws DataAccessException, SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		findBookingPricesByBookingTypeNo = con.prepareStatement(FIND_BOOKING_PRICE_BY_BOOKING_TYPE_ID_Q);
	}

	@Override
	public BookingPrice findBookingPricesByBookingTypeID(int bookingTypeID) throws DataAccessException, SQLException {
		findBookingPricesByBookingTypeNo.setInt(1, bookingTypeID);
		ResultSet rs = findBookingPricesByBookingTypeNo.executeQuery();
		BookingPrice res = buildObject(rs);
		return res;
	}

	private List<BookingPrice> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		ArrayList<BookingPrice> res = new ArrayList<>();
		BookingPrice bp = buildObject(rs);
		while (bp != null) {
			res.add(bp);
			bp = buildObject(rs);
		}
		return res;
	}

	private BookingPrice buildObject(ResultSet rs) throws DataAccessException, SQLException {
		BookingPrice res = null;
		if (rs.next()) {
			res = new BookingPrice(rs.getDate("start_date").toLocalDate(), rs.getDouble("booking_price_value"));
		}
		return res;
	}
}
