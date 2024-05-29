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
	private DetailsGUI dGUI;
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
	 * 
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

	private synchronized void detailsClicked() {
		int index = tblBookings.getSelectedRow();
		if (index >= 0) {

			TableModel model = tblBookings.getModel();
			DetailsGUI dGUI = new DetailsGUI();

			String EmployeeName = model.getValueAt(index, 0).toString();
			String CustomerName = model.getValueAt(index, 1).toString();
			String Phone = model.getValueAt(index, 2).toString();
			String Date = model.getValueAt(index, 3).toString();
			String StartTime = model.getValueAt(index, 4).toString();
			String BookingType = model.getValueAt(index, 5).toString();
			String CustomerType = model.getValueAt(index, 6).toString();
			String DogName = model.getValueAt(index, 7).toString();
			String Total = model.getValueAt(index, 8).toString();

			dGUI.setVisible(true);

			dGUI.textFieldEmployeeName.setText(EmployeeName);
			dGUI.textFieldCustomerName.setText(CustomerName);
			dGUI.textFieldPhone.setText(Phone);
			dGUI.textFieldDate.setText(Date);
			dGUI.textFieldStartTime.setText(StartTime);
			dGUI.textFieldBookingType.setText(BookingType);
			dGUI.textFieldCustomerType.setText(CustomerType);
			dGUI.textFieldDogName.setText(DogName);
			dGUI.textFieldTotal.setText(Total);
		}
	}

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

	private void init() throws Exception {
		oc = new OrderController();
		dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
		stm = new SchemaTableModel(oc.findAllBookingsOrderByAsc());
		tblBookings.setModel(stm);

	}

	private void cancelClicked() {
		setVisible(false);
		dispose();
	}

	private synchronized void updateTableThread() throws Exception {
		int selectedRow = this.tblBookings.getSelectedRow();

		try {
			if(searchDate != null && searchID > 0) {
				bookings = oc.findAvailableTime(searchDate, searchID);
				System.out.println("serched");
			} else {
				System.out.println("not serched " + searchDate + " " + searchID);
				bookings = oc.findAllBookingsOrderByAsc();
			}
			stm.setData(bookings);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage());
			e.printStackTrace();
		}
		if (stm.getRowCount() >= selectedRow && selectedRow >= 0) {
			System.out.println(stm.getRowCount() + " >= " + selectedRow);
			this.tblBookings.setRowSelectionInterval(selectedRow, selectedRow);
		}
	}
}
