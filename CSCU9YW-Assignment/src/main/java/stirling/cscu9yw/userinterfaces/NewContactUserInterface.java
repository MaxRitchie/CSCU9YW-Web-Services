package stirling.cscu9yw.userinterfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;

import stirling.cscu9yw.main.Requests;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

/**
 * 
 * This user interface is displayed when the new contact button has been clicked
 * on the main menu user interface.
 * 
 * @author 2636157
 *
 */
public class NewContactUserInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldSurname;
	private JTextField textFieldStreet;
	private JTextField textFieldTown;
	private JTextField textFieldPostcode;
	private JTextField textFieldTelephoneNumber;

	private String firstName;
	private String surname;
	private String street;
	private String town;
	private String postcode;
	private String telephoneNumber;

	/**
	 * Create the frame.
	 */
	public NewContactUserInterface() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 400);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New Contact");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(21, 11, 100, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(21, 54, 124, 20);
		contentPane.add(lblNewLabel_1);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(147, 54, 178, 20);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);

		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(147, 85, 178, 20);
		contentPane.add(textFieldSurname);

		textFieldStreet = new JTextField();
		textFieldStreet.setColumns(10);
		textFieldStreet.setBounds(147, 117, 178, 20);
		contentPane.add(textFieldStreet);

		textFieldTown = new JTextField();
		textFieldTown.setColumns(10);
		textFieldTown.setBounds(147, 148, 178, 20);
		contentPane.add(textFieldTown);

		textFieldPostcode = new JTextField();
		textFieldPostcode.setColumns(10);
		textFieldPostcode.setBounds(147, 179, 178, 20);
		contentPane.add(textFieldPostcode);

		textFieldTelephoneNumber = new JTextField();
		textFieldTelephoneNumber.setColumns(10);
		textFieldTelephoneNumber.setBounds(147, 210, 178, 20);
		contentPane.add(textFieldTelephoneNumber);

		JLabel lblNewLabel_1_1 = new JLabel("Surname");
		lblNewLabel_1_1.setBounds(21, 85, 124, 20);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Street");
		lblNewLabel_1_2.setBounds(21, 117, 124, 20);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Town");
		lblNewLabel_1_3.setBounds(21, 148, 124, 20);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Postcode");
		lblNewLabel_1_4.setBounds(21, 179, 124, 20);
		contentPane.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Telephone Number");
		lblNewLabel_1_5.setBounds(21, 210, 124, 20);
		contentPane.add(lblNewLabel_1_5);

		JButton btnAddContact = new JButton("Add Contact");

		/**
		 * A new contact is created when the add contact button is clicked, it takes the
		 * values from the text fields and uses them to create the new contact. Error
		 * handling is done to ensure that the inputs are valid and there is actually
		 * input there and not left empty. Error messages are displayed relevant to the
		 * error that has been caught.
		 * 
		 * The interface closes and returns to the main menu interface when the contact
		 * has been added.
		 */
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String contact;

				firstName = textFieldFirstName.getText().toString();
				surname = textFieldSurname.getText().toString();
				street = textFieldStreet.getText().toString();
				town = textFieldTown.getText().toString();
				postcode = textFieldPostcode.getText().toString();
				telephoneNumber = textFieldTelephoneNumber.getText().toString();

				if (!firstName.equals("") && !surname.equals("") && !street.equals("") && !town.equals("")
						&& !postcode.equals("") && !telephoneNumber.equals("")) {

					if (!(telephoneNumber.length() > 11)) {

						if (checkDigits(telephoneNumber)) {

							contact = Requests.get(telephoneNumber, "telephoneNumber");

							if (contact.equals("")) {

								Requests.post(firstName, surname, street, town, postcode, telephoneNumber);

								JOptionPane.showMessageDialog(new JFrame(), "Contact Created Successfully",
										"Contact Created", JOptionPane.PLAIN_MESSAGE);

								textFieldFirstName.setText("");
								textFieldSurname.setText("");
								textFieldStreet.setText("");
								textFieldTown.setText("");
								textFieldPostcode.setText("");
								textFieldTelephoneNumber.setText("");

								setVisible(false);
								dispose();

							} else {
								JOptionPane.showMessageDialog(new JFrame(), "This Telephone Number is Already in Use",
										"Error", JOptionPane.ERROR_MESSAGE);
							}

						} else if (telephoneNumber.equals("")) {
							JOptionPane.showMessageDialog(new JFrame(), "No Telephone Number Entered", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"Only Numbers can be Entered for the Telephone Number", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Telephone Number must be 11 or less digits",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Missing Parameters", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddContact.setBounds(147, 241, 144, 23);
		contentPane.add(btnAddContact);
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
