package db;

/**
 * A class used to package an Exception with a customizable message.
 */
public class DataAccessException extends Exception {
	
		private static final long serialVersionUID = 1L;

		public DataAccessException(String message, Throwable e) {
			super(message, e);
		}
	}

