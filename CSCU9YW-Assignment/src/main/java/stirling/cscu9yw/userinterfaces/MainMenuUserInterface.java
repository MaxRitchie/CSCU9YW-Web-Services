package stirling.cscu9yw.userinterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import stirling.cscu9yw.database.ConnectDB;
import stirling.cscu9yw.main.Requests;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextPane;

/**
 * 
 * This user interface is displayed when the java application is launched;
 * 
 * @author 2636157
 *
 */
public class MainMenuUserInterface {

	private JFrame frame;
	private JTextField textFieldTelephoneNumber;

	private String telephoneNumber;
	private JTable table;

	private JScrollPane scrollPane = new JScrollPane();

	public JButton btnDisplayAllContacts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuUserInterface window = new MainMenuUserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenuUserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1017, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Telephone Number");
		lblNewLabel.setBounds(20, 73, 134, 20);
		frame.getContentPane().add(lblNewLabel);

		textFieldTelephoneNumber = new JTextField();
		textFieldTelephoneNumber.setBounds(20, 92, 208, 20);
		frame.getContentPane().add(textFieldTelephoneNumber);
		textFieldTelephoneNumber.setColumns(10);

		scrollPane.setBounds(238, 27, 735, 255);
		frame.getContentPane().add(scrollPane);

		btnDisplayAllContacts = new JButton("Display All Contacts");
		btnDisplayAllContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] column = { "Telephone Number", "First Name", "Surname", "Street", "Town", "Postcode" };
				ConnectDB connectDB = new ConnectDB();

				table = new JTable(connectDB.db_select(), column);
				scrollPane.setViewportView(table);
			}
		});

		btnDisplayAllContacts.setBounds(20, 123, 208, 23);
		frame.getContentPane().add(btnDisplayAllContacts);

		JButton btnNewButton = new JButton("New Contact");
		btnNewButton.addActionListener(new ActionListener() {

			/**
			 * Launches and displays the new contact interface when the new contact button
			 * is clicked.
			 */
			public void actionPerformed(ActionEvent e) {

				NewContactUserInterface newcontactUserInterface = new NewContactUserInterface();
				newcontactUserInterface.setVisible(true);

			}
		});
		btnNewButton.setBounds(20, 191, 208, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewDelete = new JButton("Delete Contact ");

		/**
		 * Deletes a contact using the telephone number entered when the delete button
		 * is clicked. Carries out error checking to ensure that the value that has been
		 * entered is valid, if not error messages will be displayed.
		 */
		btnNewDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telephoneNumber = textFieldTelephoneNumber.getText().toString();

				if (validation(telephoneNumber)) {
					Requests.delete(telephoneNumber);
					JOptionPane.showMessageDialog(new JFrame(), "Contact Successfully Deleted", "Contact Deleted",
							JOptionPane.PLAIN_MESSAGE);

					textFieldTelephoneNumber.setText("");

					btnDisplayAllContacts.doClick();

				}
			}
		});

		btnNewDelete.setBounds(20, 259, 208, 23);
		frame.getContentPane().add(btnNewDelete);

		/**
		 * Searches a contact using the telephone number entered when the search by
		 * telephone number button is clicked. Carries out error checking to ensure that
		 * the value that has been entered is valid and displays the contacts info, if
		 * not error messages will be displayed.
		 */
		JButton btnSearchByTelephoneNumber = new JButton("Search By Telephone Number");
		btnSearchByTelephoneNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SingleContactUserInterface singleContactUserInterfacen = new SingleContactUserInterface();

				telephoneNumber = textFieldTelephoneNumber.getText().toString();

				if (validation(telephoneNumber)) {
					singleContactUserInterfacen.setVisible(true);
					singleContactUserInterfacen.getContactDetails(telephoneNumber);

					textFieldTelephoneNumber.setText("");
				}
			}
		});
		btnSearchByTelephoneNumber.setBounds(20, 157, 208, 23);
		frame.getContentPane().add(btnSearchByTelephoneNumber);

		/**
		 * Launches and displays the edit contact interface when the edit contact button
		 * is clicked. Carries out error checking to ensure that the value that has been
		 * entered is valid and displays the contacts info, if not error messages will
		 * be displayed.
		 */
		JButton btnEditContact = new JButton("Edit Contact");
		btnEditContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EditContactUserInterface editContactUserInterface = new EditContactUserInterface();

				telephoneNumber = textFieldTelephoneNumber.getText().toString();

				if (validation(telephoneNumber)) {
					editContactUserInterface.setVisible(true);
					editContactUserInterface.setContactDetails(telephoneNumber);

					textFieldTelephoneNumber.setText("");
				}
			}
		});
		btnEditContact.setBounds(20, 225, 208, 23);
		frame.getContentPane().add(btnEditContact);

		JLabel lblContactsDatabase = new JLabel("Contacts Database");
		lblContactsDatabase.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblContactsDatabase.setBounds(20, 27, 164, 35);
		frame.getContentPane().add(lblContactsDatabase);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldTelephoneNumber.setText("");
			}
		});
		btnClear.setBounds(164, 76, 64, 15);
		frame.getContentPane().add(btnClear);

	}

	/**
	 * Handles the validation for the input of the telephone number, it check if the
	 * input is empty, if the telephone number exists , if it's already being used
	 * and if the input is only numbers.
	 * 
	 * @param telephoneNumber Passes in the inputed telephone number.
	 * @return True if the input passes all the validation, false if the input gets
	 *         caught.
	 */
	public Boolean validation(String telephoneNumber) {

		String contact;

		if (checkDigits(telephoneNumber)) {

			contact = Requests.get(telephoneNumber, "telephoneNumber");

			if (!contact.equals("")) {
				return true;

			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Contact does not exist", "Error",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}

		} else if (telephoneNumber.equals("")) {
			JOptionPane.showMessageDialog(new JFrame(), "No Telephone Number Entered", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Only Numbers can be Entered", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;
		}
	}

	/**
	 * Checks the input for the telephone number to ensure that only numbers have
	 * been inputed.
	 * 
	 * @param telephoneNumber Passes in the telephone number to be checked.
	 * @return A Boolean value whether the telephone number is valid or not.
	 */
	public static boolean checkDigits(String telephoneNumber) {
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);

		if (telephoneNumber == null) {
			return false;
		}

		Matcher matcher = pattern.matcher(telephoneNumber);
		return matcher.matches();
	}
}
