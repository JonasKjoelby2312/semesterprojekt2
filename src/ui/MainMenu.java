package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.OrderController;
import model.Booking;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Booking> bookings;
	private OrderController oc;
	private JTable tblBookings;
	private SchemaTableModel stm;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnSchema = new JButton("Schema");
		btnSchema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					schemaClicked();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Schema could not open");
				}
			}
		});
		btnSchema.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSchema.setBounds(147, 40, 335, 51);
		panel.add(btnSchema);
		
		JButton btnCreateBooking = new JButton("Create Booking");
		btnCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createBookingClicked();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "CreatBooking could not open");
				}
				
			
			}
		});
		btnCreateBooking.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreateBooking.setBounds(147, 102, 335, 51);
		panel.add(btnCreateBooking);
		
		init();
	}
	
	private void init() {
		
	}

	private void createBookingClicked() throws Exception {
		CreateBookingGUI cbg = new CreateBookingGUI();
		cbg.setVisible(true);
	}

	private void schemaClicked() throws Exception {
		SchemaGUI s = new SchemaGUI();
		s.setVisible(true);
		SchemaGUI.startThread(s);
		//getBookings();
	}
	
//	private void getBookings() throws Exception {
//		bookings = oc.findAllBooking();
//		stm = new SchemaTableModel(bookings);
//		tblBookings.setModel(stm);
//		
//	}
}
