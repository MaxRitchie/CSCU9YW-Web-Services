package stirling.cscu9yw.userinterfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import stirling.cscu9yw.main.Requests;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * This user interface is displayed when the edit contact button has been
 * clicked on the main menu user interface.
 * 
 * @author 2636157
 *
 */
public class EditContactUserInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTelephoneNumber;
	private JTextField textFieldPostcode;
	private JTextField textFieldTown;
	private JTextField textFieldStreet;
	private JTextField textFieldSurname;
	private JTextField textFieldFirstName;

	private String firstName;
	private String surname;
	private String street;
	private String town;
	private String postcode;
	private String telephoneNumber;

	/**
	 * Creates the frame to be displayed.
	 */
	public EditContactUserInterface() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 43, 124, 20);
		contentPane.add(lblFirstName);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(10, 74, 124, 20);
		contentPane.add(lblSurname);

		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(10, 106, 124, 20);
		contentPane.add(lblStreet);

		JLabel lblTown = new JLabel("Town");
		lblTown.setBounds(10, 137, 124, 20);
		contentPane.add(lblTown);

		JLabel lblPostcode = new JLabel("Postcode");
		lblPostcode.setBounds(10, 168, 124, 20);
		contentPane.add(lblPostcode);

		JLabel lblTelephoneNumber = new JLabel("Telephone Number");
		lblTelephoneNumber.setBounds(10, 199, 124, 20);
		contentPane.add(lblTelephoneNumber);

		JButton btnEditContact = new JButton("Edit Contact");
		btnEditContact.addActionListener(new ActionListener() {

			/**
			 * Carries out the actions when the edit contact button is clicked, each text
			 * field is set to the values stored in the relevant String. It carries out
			 * error handling and send outs a message to the screen if one of the errors
			 * occurs
			 */
			public void actionPerformed(ActionEvent e) {

				firstName = textFieldFirstName.getText().toString();
				surname = textFieldSurname.getText().toString();
				street = textFieldStreet.getText().toString();
				town = textFieldTown.getText().toString();
				postcode = textFieldPostcode.getText().toString();
				telephoneNumber = textFieldTelephoneNumber.getText().toString();

				if (!firstName.equals("") && !surname.equals("") && !street.equals("") && !town.equals("")
						&& !postcode.equals("") && !telephoneNumber.equals("")) {

					Requests.put(firstName, surname, street, town, postcode, telephoneNumber);

					JOptionPane.showMessageDialog(new JFrame(), "Contact Edited Successfully", "Contact Edited",
							JOptionPane.PLAIN_MESSAGE);

					setVisible(false);
					dispose();
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Missing Parameters", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditContact.setBounds(136, 230, 144, 23);
		contentPane.add(btnEditContact);

		textFieldTelephoneNumber = new JTextField();
		textFieldTelephoneNumber.setEditable(false);
		textFieldTelephoneNumber.setColumns(10);
		textFieldTelephoneNumber.setBounds(136, 199, 178, 20);
		contentPane.add(textFieldTelephoneNumber);

		textFieldPostcode = new JTextField();
		textFieldPostcode.setColumns(10);
		textFieldPostcode.setBounds(136, 168, 178, 20);
		contentPane.add(textFieldPostcode);

		textFieldTown = new JTextField();
		textFieldTown.setColumns(10);
		textFieldTown.setBounds(136, 137, 178, 20);
		contentPane.add(textFieldTown);

		textFieldStreet = new JTextField();
		textFieldStreet.setColumns(10);
		textFieldStreet.setBounds(136, 106, 178, 20);
		contentPane.add(textFieldStreet);

		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(136, 74, 178, 20);
		contentPane.add(textFieldSurname);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		textFieldFirstName.setBounds(136, 43, 178, 20);
		contentPane.add(textFieldFirstName);

		JLabel lblNewLabel = new JLabel("Edit Contact");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 0, 100, 35);
		contentPane.add(lblNewLabel);

	}

	/**
	 * Sets the contact details for the text fields relevant to the contact that has
	 * been searched for. Each text field calls the get method from the Requests
	 * class, passing in the telephone number and the entity of what needs to be
	 * displayed in the corresponding text field.
	 * 
	 * @param telephoneNumberToSearch Passes in the telephone number value for the
	 *                                contact that will be displayed to be edited.
	 */
	public void setContactDetails(String telephoneNumberToSearch) {
		textFieldFirstName.setText(Requests.get(telephoneNumberToSearch, "firstName").toString());
		textFieldSurname.setText(Requests.get(telephoneNumberToSearch, "surname").toString());
		textFieldStreet.setText(Requests.get(telephoneNumberToSearch, "street"));
		textFieldTown.setText(Requests.get(telephoneNumberToSearch, "town").toString());
		textFieldPostcode.setText(Requests.get(telephoneNumberToSearch, "postcode").toString());
		textFieldTelephoneNumber.setText(Requests.get(telephoneNumberToSearch, "telephoneNumber").toString());
	}
}
