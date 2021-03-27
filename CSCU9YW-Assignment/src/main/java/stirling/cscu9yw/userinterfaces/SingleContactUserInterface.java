package stirling.cscu9yw.userinterfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import stirling.cscu9yw.main.Requests;

import javax.swing.JLabel;
import java.awt.Font;

/**
 * 
 * This user interface is displayed when the new contact button has been clicked
 * on the main menu user interface.
 * 
 * @author 2636157
 *
 */
public class SingleContactUserInterface extends JFrame {

	private JPanel contentPane;

	private JLabel lblFirstName;
	private JLabel lblSurname;
	private JLabel lblStreet;
	private JLabel lblTown;
	private JLabel lblPostcode;
	private JLabel lblTelephoneNumber;

	/**
	 * Create the frame.
	 */
	public SingleContactUserInterface() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblContact.setBounds(10, 11, 100, 35);
		contentPane.add(lblContact);

		JLabel lbl1 = new JLabel("First Name");
		lbl1.setBounds(10, 54, 124, 20);
		contentPane.add(lbl1);

		JLabel lbl2 = new JLabel("Surname");
		lbl2.setBounds(10, 85, 124, 20);
		contentPane.add(lbl2);

		JLabel lbl3 = new JLabel("Street");
		lbl3.setBounds(10, 117, 124, 20);
		contentPane.add(lbl3);

		JLabel lbl4 = new JLabel("Town");
		lbl4.setBounds(10, 148, 124, 20);
		contentPane.add(lbl4);

		JLabel lbl5 = new JLabel("Postcode");
		lbl5.setBounds(10, 179, 124, 20);
		contentPane.add(lbl5);

		JLabel lbl6 = new JLabel("Telephone Number");
		lbl6.setBounds(10, 210, 124, 20);
		contentPane.add(lbl6);

		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(144, 54, 124, 20);
		contentPane.add(lblFirstName);

		lblSurname = new JLabel("Surname");
		lblSurname.setBounds(144, 85, 124, 20);
		contentPane.add(lblSurname);

		lblStreet = new JLabel("Street");
		lblStreet.setBounds(144, 117, 124, 20);
		contentPane.add(lblStreet);

		lblTown = new JLabel("Town");
		lblTown.setBounds(144, 148, 124, 20);
		contentPane.add(lblTown);

		lblPostcode = new JLabel("Postcode");
		lblPostcode.setBounds(144, 179, 124, 20);
		contentPane.add(lblPostcode);

		lblTelephoneNumber = new JLabel("Telephone Number");
		lblTelephoneNumber.setBounds(144, 210, 124, 20);
		contentPane.add(lblTelephoneNumber);
	}

	/**
	 * This method sets all of the labels displayed to the contact information that
	 * has been searched for. It calls the get method from the Requests calls,
	 * passing through the telephone number to be searched along with the single
	 * piece of data that is to be displayed.
	 * 
	 * @param telephoneNumberToSearch Passes in the telephone number to be searched.
	 */
	public void getContactDetails(String telephoneNumberToSearch) {
		lblFirstName.setText(Requests.get(telephoneNumberToSearch, "firstName").toString());
		lblSurname.setText(Requests.get(telephoneNumberToSearch, "surname").toString());
		lblStreet.setText(Requests.get(telephoneNumberToSearch, "street"));
		lblTown.setText(Requests.get(telephoneNumberToSearch, "town").toString());
		lblPostcode.setText(Requests.get(telephoneNumberToSearch, "postcode").toString());
		lblTelephoneNumber.setText(Requests.get(telephoneNumberToSearch, "telephoneNumber").toString());

	}
}
