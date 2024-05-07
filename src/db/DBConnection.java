package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class DBConnection {
	private Connection connection = null;
	private static DBConnection dbConnection;

	
	  private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	  private static final String dbName = "DMA-CSD-S232_10503097"; 
	  private static final String serverAddress = "hildur.ucn.dk"; //private static final String serverAddress = "127.0.0.1";
	  private static final int serverPort = 1433; 
	  private static final String userName = "DMA-CSD-S232_10503097"; 
	  private static final String password = "Password1!";
	 
	
//	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private static final String dbName = "persistens";
//	private static final String serverAddress = "localhost";
//	private static final String serverAddress = "127.0.0.1";
//	private static final int serverPort = 1433;
//	private static final String userName = "sa";
//	private static final String password = "dockerStrongPwd123";

	private DBConnection() throws Exception { //DataAccessException before
		// Cheat sheet for the printf() method, the format is also used in the
		// String.format() method
		// http://alvinalexander.com/programming/printf-format-cheat-sheet
		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s;encrypt=false",
				serverAddress, serverPort, dbName, userName, password);
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			throw new Exception("Missing JDBC driver", e); //DataAccessException before
			// System.err.println("Could not load JDBC driver");
			// e.printStackTrace();

		} catch (SQLException e) {
			 System.out.println("Connection string was: " + connectionString);
			 e.printStackTrace();
			throw new Exception(String.format("Could not connect to database %s@%s:%d user %s", dbName, //DataAccessException before
					serverAddress, serverPort, userName), e);
			
		}
	}

	public static synchronized DBConnection getInstance() throws Exception { //DataAccessException before
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	public void startTransaction() throws Exception { //DataAccessException before
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new Exception("Could not start transaction.", e); //DataAccessException before
		}
	}

	public void commitTransaction() throws Exception { //DataAccessException before
		try {
			try {
				connection.commit();
			} catch (SQLException e) {
				throw e;
				// e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new Exception("Could not commit transaction", e); //DataAccessException before
		}
	}

	public void rollbackTransaction() throws Exception { //DataAccessException before
		try {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw e;
				// e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new Exception("Could not rollback transaction", e); //DataAccessException before
		}
	}

	public int executeInsertWithIdentity(String sql) throws Exception { //DataAccessException before
		System.out.println("DBConnection, Inserting: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (res > 0) {
				ResultSet rs = s.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
			// s.close(); -- the try block does this for us now

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new Exception("Could not execute insert (" + sql + ").", e); //DataAccessException before
		}
		return res;
	}

	public int executeInsertWithIdentity(PreparedStatement ps) throws Exception { //DataAccessException before
		// requires perpared statement to be created with the additional argument PreparedStatement.RETURN_GENERATED_KEYS  
		int res = -1;
		try {
			res = ps.executeUpdate();
			if (res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new Exception("Could not execute insert", e); //DataAccessException before
		}
		return res;
	}

	public Connection getConnection() {
		return connection;
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
