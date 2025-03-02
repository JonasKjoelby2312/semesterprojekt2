package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import controller.OrderController;
import db.BookingDB;
import model.Booking;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;

public class SchemaGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private static SchemaGUI dialog;
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
	private DateTimeFormatter dtf;
	private LocalDate searchDate;
	private int searchID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new SchemaGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An Error has occured while opening SchemaGUI");
			e.printStackTrace();
		}
		startThread(dialog);
	}

	/**
	 * This method is used to create and start a thread with the parameter, used to
	 * call the 'updateTableThread' method after 5 second intervals.
	 * @return nothing
	 * @param s of type SchemaGUI
	 */
	public static void startThread(SchemaGUI s) {
			dialog = s;
			new Thread(() -> {
				while (true) {
					try {
						Thread.sleep(5000);
						dialog.updateTableThread();

					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}


	/**
	 * Create the dialog.
	 * @throws Exception
	 */
	public SchemaGUI() throws Exception {
		setBounds(100, 100, 877, 541);
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

		JLabel lblEmployeeID = new JLabel("Employee No:");
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
					JOptionPane.showMessageDialog(null,
							"Date needs to be 'dd/mm/yyyy' and employee ID needs to a number!");
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

				JButton btnDetails = new JButton("Details");
				btnDetails.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						detailsClicked();
					}
				});
				buttonPane.add(btnDetails);
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

	/**
	 * This method is called when the 'details' button is clicked, and calls the
	 * constructor of DetailsGUI while passing it the selected Booking in
	 * tblBookings.
	 * @return nothing
	 */
	private synchronized void detailsClicked() {
		int index = tblBookings.getSelectedRow();
		if (index >= 0) {
			Booking selectedBooking = bookings.get(index);
			DetailsGUI dGUI = new DetailsGUI(selectedBooking);
			dGUI.setVisible(true);
		}
	}

	/**
	 * This method is run when the 'search' button is clicked, and filters the
	 * displayed Bookings in tblBookings to match the users inputs,
	 * which are a date and an Employee ID.
	 * @return nothing
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private void searchClicked() throws NumberFormatException, Exception {
		if(txtEmployeeID.getText().equals("") && txtDate.getText().equals("")) {
			searchDate = null;
			searchID = -1;
		} else {
			searchDate = LocalDate.parse(txtDate.getText(), dtf);
			searchID = Integer.parseInt(txtEmployeeID.getText());
		}
		updateTableThread();
	}

	/**
	 * This method is run when a window of type SchemaGUI is created, and
	 * instantiates different fields as well as setting the
	 * TableModel of tblBookings.
	 * @return nothing
	 * @throws Exception
	 */
	private void init() throws Exception {
		oc = new OrderController();
		dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
		stm = new SchemaTableModel(oc.findAllBookingsOrderByAsc());
		tblBookings.setModel(stm);

	}

	/**
	 * This method is run when the 'close' button is clicked, which closes the window.
	 */
	private void cancelClicked() {
		setVisible(false);
		dispose();
	}

	/**
	 * This method is called repeatedly by the thread, created when SchemaGUI is created.
	 * The method is used to get and display all the Bookings in the connected database.
	 * @return nothing
	 * @throws Exception
	 */
	private synchronized void updateTableThread() throws Exception {
		int selectedRow = this.tblBookings.getSelectedRow();

		try {
			if(searchDate != null && searchID > 0) {
				bookings = oc.findAvailableTime(searchDate, searchID);
			} else {
				bookings = oc.findAllBookingsOrderByAsc();
			}
			stm.setData(bookings);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage());
			e.printStackTrace();
		}
		if (stm.getRowCount() >= selectedRow && selectedRow >= 0) {
			this.tblBookings.setRowSelectionInterval(selectedRow, selectedRow);
		}
	}
}
