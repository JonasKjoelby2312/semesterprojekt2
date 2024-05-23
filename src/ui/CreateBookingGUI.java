package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.OrderController;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class CreateBookingGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private OrderController oc;
	private JComboBox comboBoxDogPerson;
	private JTextField textFieldBookingTypeID;
	private JTextField textFieldEmployeeID;
	private JTextField textFieldPhoneNumber;
	private JTextField textFieldDate;
	private JTextField textFieldStartTime;
	private JTextField textFieldDogName;
	private JTextField textFieldComment;
	private DateTimeFormatter dtfDate;
	private DateTimeFormatter dtfTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateBookingGUI dialog = new CreateBookingGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateBookingGUI() throws Exception {
		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Choose dog or person:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel.add(lblNewLabel);
			}
			{
				String[] choices = { "Dog", "Person" };
				comboBoxDogPerson = new JComboBox<String>(choices);
				comboBoxDogPerson.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel.add(comboBoxDogPerson);
				
				comboBoxDogPerson.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if(comboBoxDogPerson.getSelectedItem().equals("Person")) { 
							textFieldDogName.setEnabled(false);
							textFieldComment.setEnabled(false);
						} else {
							textFieldDogName.setEnabled(true);
							textFieldComment.setEnabled(true);
						}
					}
				} );
			}
	
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel lblBookingType = new JLabel("Booking Type ID: ");
				lblBookingType.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblBookingType = new GridBagConstraints();
				gbc_lblBookingType.insets = new Insets(0, 0, 5, 5);
				gbc_lblBookingType.anchor = GridBagConstraints.WEST;
				gbc_lblBookingType.gridx = 1;
				gbc_lblBookingType.gridy = 0;
				panel.add(lblBookingType, gbc_lblBookingType);
			}
			{
				textFieldBookingTypeID = new JTextField();
				textFieldBookingTypeID.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_textFieldBookingTypeID = new GridBagConstraints();
				gbc_textFieldBookingTypeID.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldBookingTypeID.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldBookingTypeID.gridx = 2;
				gbc_textFieldBookingTypeID.gridy = 0;
				panel.add(textFieldBookingTypeID, gbc_textFieldBookingTypeID);
				textFieldBookingTypeID.setColumns(10);
			}
			{
				JLabel lblEmployeeID = new JLabel("Employee No : ");
				lblEmployeeID.setHorizontalAlignment(SwingConstants.LEFT);
				lblEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblEmployeeID = new GridBagConstraints();
				gbc_lblEmployeeID.anchor = GridBagConstraints.WEST;
				gbc_lblEmployeeID.insets = new Insets(0, 0, 5, 5);
				gbc_lblEmployeeID.gridx = 1;
				gbc_lblEmployeeID.gridy = 1;
				panel.add(lblEmployeeID, gbc_lblEmployeeID);
			}
			{
				textFieldEmployeeID = new JTextField();
				textFieldEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_textFieldEmployeeID = new GridBagConstraints();
				gbc_textFieldEmployeeID.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldEmployeeID.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldEmployeeID.gridx = 2;
				gbc_textFieldEmployeeID.gridy = 1;
				panel.add(textFieldEmployeeID, gbc_textFieldEmployeeID);
				textFieldEmployeeID.setColumns(10);
			}
			{
				JLabel lblPhoneNumber = new JLabel("Phone Number: ");
				lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
				gbc_lblPhoneNumber.anchor = GridBagConstraints.WEST;
				gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
				gbc_lblPhoneNumber.gridx = 1;
				gbc_lblPhoneNumber.gridy = 2;
				panel.add(lblPhoneNumber, gbc_lblPhoneNumber);
			}
			{
				textFieldPhoneNumber = new JTextField();
				textFieldPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_textFieldPhoneNumber = new GridBagConstraints();
				gbc_textFieldPhoneNumber.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldPhoneNumber.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldPhoneNumber.gridx = 2;
				gbc_textFieldPhoneNumber.gridy = 2;
				panel.add(textFieldPhoneNumber, gbc_textFieldPhoneNumber);
				textFieldPhoneNumber.setColumns(10);
			}
			{
				JLabel lblDate = new JLabel("Date: ");
				lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblDate = new GridBagConstraints();
				gbc_lblDate.anchor = GridBagConstraints.WEST;
				gbc_lblDate.insets = new Insets(0, 0, 5, 5);
				gbc_lblDate.gridx = 1;
				gbc_lblDate.gridy = 3;
				panel.add(lblDate, gbc_lblDate);
			}
			{
				textFieldDate = new JTextField();
				textFieldDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_textFieldDate = new GridBagConstraints();
				gbc_textFieldDate.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldDate.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldDate.gridx = 2;
				gbc_textFieldDate.gridy = 3;
				panel.add(textFieldDate, gbc_textFieldDate);
				textFieldDate.setColumns(10);
			}
			{
				JLabel lblStartTime = new JLabel("Start Time: ");
				lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblStartTime = new GridBagConstraints();
				gbc_lblStartTime.anchor = GridBagConstraints.WEST;
				gbc_lblStartTime.insets = new Insets(0, 0, 5, 5);
				gbc_lblStartTime.gridx = 1;
				gbc_lblStartTime.gridy = 4;
				panel.add(lblStartTime, gbc_lblStartTime);
			}
			{
				textFieldStartTime = new JTextField();
				textFieldStartTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_textFieldStartTime = new GridBagConstraints();
				gbc_textFieldStartTime.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldStartTime.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldStartTime.gridx = 2;
				gbc_textFieldStartTime.gridy = 4;
				panel.add(textFieldStartTime, gbc_textFieldStartTime);
				textFieldStartTime.setColumns(10);
			}
			{
				JLabel lblDogName = new JLabel("Dog Name: ");
				lblDogName.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblDogName = new GridBagConstraints();
				gbc_lblDogName.anchor = GridBagConstraints.WEST;
				gbc_lblDogName.insets = new Insets(0, 0, 5, 5);
				gbc_lblDogName.gridx = 1;
				gbc_lblDogName.gridy = 5;
				panel.add(lblDogName, gbc_lblDogName);
			}
			{
				textFieldDogName = new JTextField();
				textFieldDogName.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_textFieldDogName = new GridBagConstraints();
				gbc_textFieldDogName.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldDogName.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldDogName.gridx = 2;
				gbc_textFieldDogName.gridy = 5;
				panel.add(textFieldDogName, gbc_textFieldDogName);
				textFieldDogName.setColumns(10);
			}
			{
				JLabel lblComment = new JLabel("Comment: ");
				lblComment.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblComment = new GridBagConstraints();
				gbc_lblComment.anchor = GridBagConstraints.WEST;
				gbc_lblComment.insets = new Insets(0, 0, 0, 5);
				gbc_lblComment.gridx = 1;
				gbc_lblComment.gridy = 6;
				panel.add(lblComment, gbc_lblComment);
			}
			{
				textFieldComment = new JTextField();
				textFieldComment.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_textFieldComment = new GridBagConstraints();
				gbc_textFieldComment.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldComment.gridx = 2;
				gbc_textFieldComment.gridy = 6;
				panel.add(textFieldComment, gbc_textFieldComment);
				textFieldComment.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton createButton = new JButton("Create Booking");
				createButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							createBookingClicked();
						} catch (NumberFormatException e1) {
						} catch (Exception e1) {
						}
					}
				});
				createButton.setActionCommand("Create Booking");
				buttonPane.add(createButton);
				getRootPane().setDefaultButton(createButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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

	private void init() throws Exception {
		oc = new OrderController();
		dtfDate = DateTimeFormatter.ofPattern("d/MM/yyyy");
		dtfTime = DateTimeFormatter.ofPattern("H:mm");

	}

	private void cancelClicked() {
		setVisible(false);
		dispose();
	}

	private void createBookingClicked() throws NumberFormatException, Exception {
		if (comboBoxDogPerson.getSelectedItem().toString().equals("Person")) {
			oc.createBookingPerson(
					Integer.parseInt(textFieldBookingTypeID.getText()), 
					Integer.parseInt(textFieldEmployeeID.getText()),
					textFieldPhoneNumber.getText(), 
					LocalDate.parse(textFieldDate.getText(), dtfDate),
					LocalTime.parse(textFieldStartTime.getText(), dtfTime));
		} else {
			oc.createBookingDog(
					Integer.parseInt(textFieldBookingTypeID.getText()), 
					Integer.parseInt(textFieldEmployeeID.getText()),
					textFieldPhoneNumber.getText(), 
					textFieldDogName.getText(),
					textFieldComment.getText(),
					LocalDate.parse(textFieldDate.getText(), dtfDate),
					LocalTime.parse(textFieldStartTime.getText(), dtfTime));
		}
		oc.completeBooking();
		cancelClicked();
		
	}

}
