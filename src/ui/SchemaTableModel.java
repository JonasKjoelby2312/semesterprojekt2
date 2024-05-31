package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Booking;
import model.DogCut;

/**
 * This class extends the Java class AbstractTableModel, and is used to display
 * Booking objects in JTables.
 */
public class SchemaTableModel extends AbstractTableModel {
	private List<Booking> data;
	
	private static final String[] COL_NAMES = {
			"Employee Name", "Customer Name", "Phone", "Date", "Start Time", "Booking Type", "Customer Type", "Dog Name", "Total"
	};
	
	public SchemaTableModel(List<Booking> data) {
		this.data = data;
		if(this.data == null) {
			this.data = new ArrayList<>();
		}
	}
	
	/**
	 * This method is used to get the values that are displayed in the JTables
	 * different columns.
	 * @return res of type String
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Booking b = data.get(rowIndex);
		String res = "";
		switch(columnIndex) {
		case 0: res = "" + b.getEmployee().getName(); break;
		case 1: res = " " + b.getCustomer().getName(); break;
		case 2: res = " " + b.getCustomer().getPhoneNo(); break; 
		case 3: res = "" + b.getDate(); break;
		case 4: res = "" + b.getStartTime(); break;
		case 5: res = b.getBookingType().getName(); break;
		case 6: res = "" + b.getCustomerType(); break;
		case 7:
			if(b.getCustomerType().equals("Dog")) { 
				DogCut dc = (DogCut) b;
				res = dc.getDog().getName();
			} else {
				res = "";
			}
			break;
		case 8: res = "" + b.getTotal(); break;
		default: res ="<UNKNOWN " + columnIndex + ">"; 
		}
		
		return res;
	}
	
	/**
	 * This method is used to get the column name with the equivalent index
	 * given in the parameter.
	 * @return the name of the column as a String
	 * @param col of type Integer
	 */
	@Override
	public String getColumnName(int col) {
		return COL_NAMES[col];
	}
	
	/**
	 * This method is used to get the total number of columns in the TableModel.
	 * @return the number of columns as an Integer
	 */
	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	/**
	 * This method is used to get the total amount of rows in the TableModel.
	 * @return the number of rows in the TableModel
	 */
	@Override
	public int getRowCount() {
		return data.size();
	}
	
	/**
	 * This method is used to get the Booking object at the index given through the parameter.
	 * @param rowIndex
	 * @return Booking object
	 */
	public Booking getDataAt(int rowIndex) {
		return data.get(rowIndex);
	}
	
	/**
	 * This method is used to set the data of the TableModel after the creation of it.
	 * @return nothing
	 * @param data
	 */
	public void setData(List<Booking> data) {
		this.data = data;
		super.fireTableDataChanged();
	}
}
