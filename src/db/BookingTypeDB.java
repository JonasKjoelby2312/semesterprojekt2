package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BookingType;

public class BookingTypeDB implements BookingTypeDAO {
	private static final String FIND_ALL_Q = "select booking_type_id, customer_type, name, booking_type_description, duration from booking_type";
	private static final String FIND_BOOKING_TYPE_BY_ID_Q = FIND_ALL_Q + " where booking_type_id = ?";
	private PreparedStatement findAllPS;
	private PreparedStatement findByBookingTypeIDPS;
	
	private BookingPriceDB bookingPriceDB;
	
	public BookingTypeDB() throws Exception {
		bookingPriceDB = new BookingPriceDB();
		Connection con = DBConnection.getInstance().getConnection();
		findAllPS = con.prepareStatement(FIND_ALL_Q);
	
		findByBookingTypeIDPS = con.prepareStatement(FIND_BOOKING_TYPE_BY_ID_Q);
	}

	@Override
	public List<BookingType> findAllBookingTypes() throws Exception {
		List<BookingType> res = new ArrayList<BookingType>();
		try {
			ResultSet rs = findAllPS.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new Exception("Could not find bookingTypes", e);
		}
		return res;
	}
	

	private List<BookingType> buildObjects(ResultSet rs) throws Exception {
		ArrayList<BookingType> res = new ArrayList<>();
		BookingType bt = buildObject(rs);
		while(bt != null) {
			res.add(bt);
			bt = buildObject(rs);
		}
		return res;
	}

	@Override
	public BookingType findBookingType(int bookingTypeID) throws Exception {
		BookingType res = null;
		try {
			findByBookingTypeIDPS.setInt(1, bookingTypeID);
			ResultSet rs = findByBookingTypeIDPS.executeQuery();
			res = buildObject(rs);
		} catch (SQLException e) {
			throw new Exception("Could not find bookingType by ID", e);
		}
		return res;
	}

	private BookingType buildObject(ResultSet rs) throws Exception {
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

	public BookingType findBookingTypeByID(int id) throws Exception {
		BookingType res = null;
		findByBookingTypeIDPS.setInt(1, id);
		try {
			ResultSet rs = findByBookingTypeIDPS.executeQuery();
			res = buildObject(rs);
		} catch (Exception e) {
			throw new Exception("Could not find booking type");
		}
		return res;
	}
	}

