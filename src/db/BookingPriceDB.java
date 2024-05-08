package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BookingPrice;

public class BookingPriceDB implements BookingPriceDAO {
	private static final String FIND_BOOKING_PRICE_BY_BOOKING_TYPE_NO_Q = "select booking_price_id, start_date, value, bt_id where booking_price_id = ?";

	private PreparedStatement findBookingPricesByBookingTypeNo;

	public BookingPriceDB() throws Exception {
		Connection con = DBConnection.getInstance().getConnection();
		findBookingPricesByBookingTypeNo = con.prepareStatement(FIND_BOOKING_PRICE_BY_BOOKING_TYPE_NO_Q);
	}

	@Override
	public List<BookingPrice> findBookingPricesByBookingTypeNo(int bookingTypeNo) throws Exception {
		List<BookingPrice> res = new ArrayList<>();
		findBookingPricesByBookingTypeNo.setInt(1, bookingTypeNo);
		ResultSet rs = findBookingPricesByBookingTypeNo.executeQuery();
		res = buildObjects(rs);
		return res;
	}

	private List<BookingPrice> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<BookingPrice> res = new ArrayList<>();
		BookingPrice bp = buildObject(rs);
		while (bp != null) {
			res.add(bp);
			bp = buildObject(rs);
		}
		return res;
	}

	private BookingPrice buildObject(ResultSet rs) throws SQLException {
		BookingPrice res = null;
		if (rs.next()) {
			res = new BookingPrice(rs.getDate("start_date").toLocalDate(), rs.getDouble("p_value"));
		}
		return res;
	}
}
