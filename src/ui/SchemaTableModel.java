package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Booking;
import model.DogCut;

public class SchemaTableModel extends AbstractTableModel {
	private List<Booking> data;
	
	public SchemaTableModel(List<Booking> data) {
		this.data = data;
		if(this.data == null) {
			this.data = new ArrayList<>();
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Booking b = data.get(rowIndex);
		String res = "";
		switch(columnIndex) {
		case 0: res = "" + b.getEmployee().getName(); break;
		case 1: res = "" + b.getDate(); break;
		case 2: res = "" + b.getStartTime(); break;
		case 3: res = b.getBookingType().getName(); break;
		case 4: res = "" + b.getCustomerType(); break;
		case 5:
			if(b.getCustomerType().equals("Dog")) {
				//DogCut dc = (DogCut) b; TODO
				//res = dc.getDog().getName();
				res = "NULL";
			} else {
				res = "NULL";
			}
			break;
		case 6: res = "" + b.getTotal(); break;
		default: res ="<UNKNOWN " + columnIndex + ">"; 
		}
		
		return res;
	}
	
	private static final String[] COL_NAMES = {
			"Employee Name", "Date", "Start Time", "Booking Type", "Customer Type", "Dog Name", "Total"
	};
	
	@Override
	public String getColumnName(int col) {
		return COL_NAMES[col];
	}
	
	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	
	public Booking getDataAt(int rowIndex) {
		return data.get(rowIndex);
	}
	
	public void setData(List<Booking> data) {
		this.data = data;
		super.fireTableDataChanged();
	}
}
