package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import controller.OrderController;
import db.BookingDB;
import model.Booking;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;

public class SchemaGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmployeeID;
	private JTextField txtDate;
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnSearch;
	private List<Booking> bookings;
	private OrderController oc;
	private JTable tblBookings;
	private SchemaTableModel stm;
	DateTimeFormatter dtf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SchemaGUI dialog = new SchemaGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			//TODO
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	public SchemaGUI() throws Exception {
		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		
		JLabel lblDate = new JLabel("Date:");
		panel_1.add(lblDate);
		
		txtDate = new JTextField();
		panel_1.add(txtDate);
		txtDate.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.EAST);
		
		JLabel lblEmployeeID = new JLabel("Employee ID:");
		panel_2.add(lblEmployeeID);
		
		txtEmployeeID = new JTextField();
		panel_2.add(txtEmployeeID);
		txtEmployeeID.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					searchClicked();
				} catch (Exception e1) {
					//TODO ( "INVAILD DATA INUPT" ) eller noget
				}
			}
		});
		panel_2.add(btnSearch);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		contentPanel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		tblBookings = new JTable();
		scrollPane.setViewportView(tblBookings);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelClicked();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelClicked();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		init();
	}
	
	private void searchClicked() throws NumberFormatException, Exception {
		updateTable();
	}

	private void init() throws Exception {
		oc = new OrderController();
		dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
		stm = new SchemaTableModel(oc.findAllBooking());
		
		tblBookings.setModel(stm);
		
	}

	private void cancelClicked() {
		setVisible(false);
		dispose();
	}
	
	private void updateTable() throws NumberFormatException, Exception {
		bookings = oc.findAvailableTime(LocalDate.parse(txtDate.getText(), dtf), Integer.parseInt(txtEmployeeID.getText()));
		stm = new SchemaTableModel(bookings);
		tblBookings.setModel(stm);
	}
}
