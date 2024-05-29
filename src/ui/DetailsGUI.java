package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Booking;
import model.DogCut;

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
	JTextField textFieldEmployeeName;
	JTextField textFieldCustomerName;
	JTextField textFieldPhone;
	JTextField textFieldDate;
	JTextField textFieldStartTime;
	JTextField textFieldBookingType;
	JTextField textFieldCustomerType;
	JTextField textFieldDogName;
	JTextField textFieldTotal;
	private JTextField textFieldDescription;
	private JTextField textFieldComment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetailsGUI dialog = new DetailsGUI(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetailsGUI(Booking sb) {
		setBounds(100, 100, 517, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblEmployeeName = new JLabel("Employee Name:");
			lblEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblEmployeeName = new GridBagConstraints();
			gbc_lblEmployeeName.anchor = GridBagConstraints.WEST;
			gbc_lblEmployeeName.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmployeeName.gridx = 0;
			gbc_lblEmployeeName.gridy = 0;
			contentPanel.add(lblEmployeeName, gbc_lblEmployeeName);
		}
		{
			textFieldEmployeeName = new JTextField();
			textFieldEmployeeName.setEditable(false);
			GridBagConstraints gbc_textFieldEmployeeName = new GridBagConstraints();
			gbc_textFieldEmployeeName.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldEmployeeName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldEmployeeName.gridx = 1;
			gbc_textFieldEmployeeName.gridy = 0;
			contentPanel.add(textFieldEmployeeName, gbc_textFieldEmployeeName);
			textFieldEmployeeName.setColumns(10);
		}
		{
			JLabel lblCustomerName = new JLabel("Customer Name:");
			lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
			gbc_lblCustomerName.anchor = GridBagConstraints.WEST;
			gbc_lblCustomerName.insets = new Insets(0, 0, 5, 5);
			gbc_lblCustomerName.gridx = 0;
			gbc_lblCustomerName.gridy = 1;
			contentPanel.add(lblCustomerName, gbc_lblCustomerName);
		}
		{
			textFieldCustomerName = new JTextField();
			textFieldCustomerName.setEditable(false);
			GridBagConstraints gbc_textFieldCustomerName = new GridBagConstraints();
			gbc_textFieldCustomerName.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldCustomerName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldCustomerName.gridx = 1;
			gbc_textFieldCustomerName.gridy = 1;
			contentPanel.add(textFieldCustomerName, gbc_textFieldCustomerName);
			textFieldCustomerName.setColumns(10);
		}
		{
			JLabel lblPhone = new JLabel("Phone:");
			lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblPhone = new GridBagConstraints();
			gbc_lblPhone.anchor = GridBagConstraints.WEST;
			gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhone.gridx = 0;
			gbc_lblPhone.gridy = 2;
			contentPanel.add(lblPhone, gbc_lblPhone);
		}
		{
			textFieldPhone = new JTextField();
			textFieldPhone.setEditable(false);
			GridBagConstraints gbc_textFieldPhone = new GridBagConstraints();
			gbc_textFieldPhone.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldPhone.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPhone.gridx = 1;
			gbc_textFieldPhone.gridy = 2;
			contentPanel.add(textFieldPhone, gbc_textFieldPhone);
			textFieldPhone.setColumns(10);
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
			JLabel lblBookingType = new JLabel("Booking Type:");
			lblBookingType.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblBookingType = new GridBagConstraints();
			gbc_lblBookingType.anchor = GridBagConstraints.WEST;
			gbc_lblBookingType.insets = new Insets(0, 0, 5, 5);
			gbc_lblBookingType.gridx = 0;
			gbc_lblBookingType.gridy = 5;
			contentPanel.add(lblBookingType, gbc_lblBookingType);
		}
		{
			textFieldBookingType = new JTextField();
			textFieldBookingType.setEditable(false);
			GridBagConstraints gbc_textFieldBookingType = new GridBagConstraints();
			gbc_textFieldBookingType.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldBookingType.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldBookingType.gridx = 1;
			gbc_textFieldBookingType.gridy = 5;
			contentPanel.add(textFieldBookingType, gbc_textFieldBookingType);
			textFieldBookingType.setColumns(10);
		}
		{
			JLabel lblCustomerType = new JLabel("Customer Type:");
			lblCustomerType.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblCustomerType = new GridBagConstraints();
			gbc_lblCustomerType.anchor = GridBagConstraints.WEST;
			gbc_lblCustomerType.insets = new Insets(0, 0, 5, 5);
			gbc_lblCustomerType.gridx = 0;
			gbc_lblCustomerType.gridy = 6;
			contentPanel.add(lblCustomerType, gbc_lblCustomerType);
		}
		{
			textFieldCustomerType = new JTextField();
			textFieldCustomerType.setEditable(false);
			GridBagConstraints gbc_textFieldCustomerType = new GridBagConstraints();
			gbc_textFieldCustomerType.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldCustomerType.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldCustomerType.gridx = 1;
			gbc_textFieldCustomerType.gridy = 6;
			contentPanel.add(textFieldCustomerType, gbc_textFieldCustomerType);
			textFieldCustomerType.setColumns(10);
		}
		{
			JLabel lblDogName = new JLabel("Dog Name:");
			lblDogName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblDogName = new GridBagConstraints();
			gbc_lblDogName.anchor = GridBagConstraints.WEST;
			gbc_lblDogName.insets = new Insets(0, 0, 5, 5);
			gbc_lblDogName.gridx = 0;
			gbc_lblDogName.gridy = 7;
			contentPanel.add(lblDogName, gbc_lblDogName);
		}
		{
			textFieldDogName = new JTextField();
			textFieldDogName.setEditable(false);
			GridBagConstraints gbc_textFieldDogName = new GridBagConstraints();
			gbc_textFieldDogName.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldDogName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldDogName.gridx = 1;
			gbc_textFieldDogName.gridy = 7;
			contentPanel.add(textFieldDogName, gbc_textFieldDogName);
			textFieldDogName.setColumns(10);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblDescription = new GridBagConstraints();
			gbc_lblDescription.anchor = GridBagConstraints.WEST;
			gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescription.gridx = 0;
			gbc_lblDescription.gridy = 8;
			contentPanel.add(lblDescription, gbc_lblDescription);
		}
		{
			textFieldDescription = new JTextField();
			textFieldDescription.setEditable(false);
			GridBagConstraints gbc_textFieldDescription = new GridBagConstraints();
			gbc_textFieldDescription.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldDescription.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldDescription.gridx = 1;
			gbc_textFieldDescription.gridy = 8;
			contentPanel.add(textFieldDescription, gbc_textFieldDescription);
			textFieldDescription.setColumns(10);
		}
		{
			JLabel lblComment = new JLabel("Comment:");
			lblComment.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblComment = new GridBagConstraints();
			gbc_lblComment.anchor = GridBagConstraints.WEST;
			gbc_lblComment.insets = new Insets(0, 0, 5, 5);
			gbc_lblComment.gridx = 0;
			gbc_lblComment.gridy = 9;
			contentPanel.add(lblComment, gbc_lblComment);
		}
		{
			textFieldComment = new JTextField();
			textFieldComment.setEditable(false);
			GridBagConstraints gbc_textFieldComment = new GridBagConstraints();
			gbc_textFieldComment.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldComment.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldComment.gridx = 1;
			gbc_textFieldComment.gridy = 9;
			contentPanel.add(textFieldComment, gbc_textFieldComment);
			textFieldComment.setColumns(10);
		}
		{
			JLabel lblTotal = new JLabel("Total:");
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblTotal = new GridBagConstraints();
			gbc_lblTotal.anchor = GridBagConstraints.WEST;
			gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
			gbc_lblTotal.gridx = 0;
			gbc_lblTotal.gridy = 10;
			contentPanel.add(lblTotal, gbc_lblTotal);
		}
		{
			textFieldTotal = new JTextField();
			textFieldTotal.setEditable(false);
			GridBagConstraints gbc_textFieldTotal = new GridBagConstraints();
			gbc_textFieldTotal.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldTotal.gridx = 1;
			gbc_textFieldTotal.gridy = 10;
			contentPanel.add(textFieldTotal, gbc_textFieldTotal);
			textFieldTotal.setColumns(10);
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
		
		init(sb);
	}

	private void init(Booking sb) {
		textFieldEmployeeName.setText(sb.getEmployee().getName());
		textFieldCustomerName.setText(sb.getCustomer().getName());
		textFieldPhone.setText(sb.getCustomer().getPhoneNo());
		textFieldDate.setText("" + sb.getDate());
		textFieldStartTime.setText("" + sb.getStartTime());
		textFieldBookingType.setText("" + sb.getBookingType().getBookingTypeID());
		textFieldCustomerType.setText(sb.getCustomerType());
		textFieldTotal.setText("" + sb.getTotal());
		
		if(sb.getCustomerType().equals("Dog")) {
			DogCut dc = (DogCut) sb;
			
			textFieldDogName.setText(dc.getDog().getName());
			textFieldDescription.setText(dc.getDog().getDescription());
			textFieldComment.setText(dc.getComment());
		}
	}

	private void closeClicked() {
		setVisible(false);
		dispose();
	}

}
