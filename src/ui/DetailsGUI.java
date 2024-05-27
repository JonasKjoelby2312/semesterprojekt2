package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetailsGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JTextField textFieldBookingTypeID;
	JTextField textFieldEmployeeID;
	JTextField textFieldPhoneNumber;
	JTextField textFieldDate;
	JTextField textFieldStartTime;
	JTextField textFieldDogName;
	JTextField textFieldComment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetailsGUI dialog = new DetailsGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetailsGUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblBookingTypeNo = new JLabel("Booking Type No:");
			lblBookingTypeNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblBookingTypeNo = new GridBagConstraints();
			gbc_lblBookingTypeNo.anchor = GridBagConstraints.WEST;
			gbc_lblBookingTypeNo.insets = new Insets(0, 0, 5, 5);
			gbc_lblBookingTypeNo.gridx = 0;
			gbc_lblBookingTypeNo.gridy = 0;
			contentPanel.add(lblBookingTypeNo, gbc_lblBookingTypeNo);
		}
		{
			textFieldBookingTypeID = new JTextField();
			textFieldBookingTypeID.setEditable(false);
			GridBagConstraints gbc_textFieldBookingTypeID = new GridBagConstraints();
			gbc_textFieldBookingTypeID.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldBookingTypeID.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldBookingTypeID.gridx = 1;
			gbc_textFieldBookingTypeID.gridy = 0;
			contentPanel.add(textFieldBookingTypeID, gbc_textFieldBookingTypeID);
			textFieldBookingTypeID.setColumns(10);
		}
		{
			JLabel lblEmployeeNo = new JLabel("Employee NO: ");
			lblEmployeeNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblEmployeeNo = new GridBagConstraints();
			gbc_lblEmployeeNo.anchor = GridBagConstraints.WEST;
			gbc_lblEmployeeNo.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmployeeNo.gridx = 0;
			gbc_lblEmployeeNo.gridy = 1;
			contentPanel.add(lblEmployeeNo, gbc_lblEmployeeNo);
		}
		{
			textFieldEmployeeID = new JTextField();
			textFieldEmployeeID.setEditable(false);
			GridBagConstraints gbc_textFieldEmployeeNo = new GridBagConstraints();
			gbc_textFieldEmployeeNo.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldEmployeeNo.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldEmployeeNo.gridx = 1;
			gbc_textFieldEmployeeNo.gridy = 1;
			contentPanel.add(textFieldEmployeeID, gbc_textFieldEmployeeNo);
			textFieldEmployeeID.setColumns(10);
		}
		{
			JLabel lblPhoneNumber = new JLabel("Phone Number:");
			lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
			gbc_lblPhoneNumber.anchor = GridBagConstraints.WEST;
			gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhoneNumber.gridx = 0;
			gbc_lblPhoneNumber.gridy = 2;
			contentPanel.add(lblPhoneNumber, gbc_lblPhoneNumber);
		}
		{
			textFieldPhoneNumber = new JTextField();
			textFieldPhoneNumber.setEditable(false);
			GridBagConstraints gbc_textFieldPhoneNumber = new GridBagConstraints();
			gbc_textFieldPhoneNumber.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldPhoneNumber.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPhoneNumber.gridx = 1;
			gbc_textFieldPhoneNumber.gridy = 2;
			contentPanel.add(textFieldPhoneNumber, gbc_textFieldPhoneNumber);
			textFieldPhoneNumber.setColumns(10);
		}
		{
			JLabel lblDate = new JLabel("Date: ");
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblDate = new GridBagConstraints();
			gbc_lblDate.anchor = GridBagConstraints.WEST;
			gbc_lblDate.insets = new Insets(0, 0, 5, 5);
			gbc_lblDate.gridx = 0;
			gbc_lblDate.gridy = 3;
			contentPanel.add(lblDate, gbc_lblDate);
		}
		{
			textFieldDate = new JTextField();
			textFieldDate.setEditable(false);
			GridBagConstraints gbc_textFieldDate = new GridBagConstraints();
			gbc_textFieldDate.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldDate.gridx = 1;
			gbc_textFieldDate.gridy = 3;
			contentPanel.add(textFieldDate, gbc_textFieldDate);
			textFieldDate.setColumns(10);
		}
		{
			JLabel lblStartTime = new JLabel("Start Time:");
			lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblStartTime = new GridBagConstraints();
			gbc_lblStartTime.anchor = GridBagConstraints.WEST;
			gbc_lblStartTime.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartTime.gridx = 0;
			gbc_lblStartTime.gridy = 4;
			contentPanel.add(lblStartTime, gbc_lblStartTime);
		}
		{
			textFieldStartTime = new JTextField();
			textFieldStartTime.setEditable(false);
			GridBagConstraints gbc_textFieldStartTime = new GridBagConstraints();
			gbc_textFieldStartTime.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldStartTime.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldStartTime.gridx = 1;
			gbc_textFieldStartTime.gridy = 4;
			contentPanel.add(textFieldStartTime, gbc_textFieldStartTime);
			textFieldStartTime.setColumns(10);
		}
		{
			JLabel lblDogName = new JLabel("Dog Name:");
			lblDogName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblDogName = new GridBagConstraints();
			gbc_lblDogName.anchor = GridBagConstraints.WEST;
			gbc_lblDogName.insets = new Insets(0, 0, 5, 5);
			gbc_lblDogName.gridx = 0;
			gbc_lblDogName.gridy = 5;
			contentPanel.add(lblDogName, gbc_lblDogName);
		}
		{
			textFieldDogName = new JTextField();
			textFieldDogName.setEditable(false);
			GridBagConstraints gbc_textFieldDogName = new GridBagConstraints();
			gbc_textFieldDogName.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldDogName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldDogName.gridx = 1;
			gbc_textFieldDogName.gridy = 5;
			contentPanel.add(textFieldDogName, gbc_textFieldDogName);
			textFieldDogName.setColumns(10);
		}
		{
			JLabel lblComment = new JLabel("Comment:");
			lblComment.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblComment = new GridBagConstraints();
			gbc_lblComment.anchor = GridBagConstraints.WEST;
			gbc_lblComment.insets = new Insets(0, 0, 5, 5);
			gbc_lblComment.gridx = 0;
			gbc_lblComment.gridy = 6;
			contentPanel.add(lblComment, gbc_lblComment);
		}
		{
			textFieldComment = new JTextField();
			textFieldComment.setEditable(false);
			GridBagConstraints gbc_textFieldComment = new GridBagConstraints();
			gbc_textFieldComment.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldComment.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldComment.gridx = 1;
			gbc_textFieldComment.gridy = 6;
			contentPanel.add(textFieldComment, gbc_textFieldComment);
			textFieldComment.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeClicked();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void closeClicked() {
		setVisible(false);
		dispose();
	}

}
