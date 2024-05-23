package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookingConfirmation extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BookingConfirmation dialog = new BookingConfirmation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BookingConfirmation() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				JButton btnOkaybtn = new JButton("OKAY");
				btnOkaybtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okayClicked();
					}
				});
				panel.add(btnOkaybtn);
			}
		}
		{
			JLabel lblConfirmation = new JLabel("Booking Complete!");
			lblConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblConfirmation, BorderLayout.CENTER);
			lblConfirmation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		{
			{
				okayClicked();
			}
		}
	}

	private void okayClicked() {
		setVisible(false);
		dispose();

	}

}
