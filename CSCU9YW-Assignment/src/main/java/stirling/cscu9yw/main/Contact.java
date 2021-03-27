package stirling.cscu9yw.main;

import javax.persistence.*;

// Specifies that the class is an entity. This annotation is applied to the entity class.
@Entity
// Identifies the table name that will be used from the database.
@Table(name = "contacts")

/**
 * This class is used to create a new contact, it contains the getters and
 * setters to retrieve a contacts information or create a completely new one.
 * 
 * @author 2636157
 *
 */
public class Contact {

	// The @Column tags are used to identify the column from the database and link
	// it to its relevant String, the @Id identifies the primary key.

	@Column(name = "first_name")
	private String firstName;
	@Column(name = "surname")
	private String surname;
	@Column(name = "street")
	private String street;
	@Column(name = "town")
	private String town;
	@Column(name = "postcode")
	private String postcode;
	@Column(name = "telephone_Number")
	@Id
	private String telephoneNumber;

	/**
	 * This is a constructor for the class without anything being passed in, this
	 * allows the contact class to be used and accessed throughout the application
	 * without creating a new contact.
	 */
	public Contact() {

	}

	/**
	 * This is the constructor for the class, when this is called, a new contact is
	 * created with the data that has been passed in.
	 * 
	 * @param firstName       Passes in the first name value for the contact.
	 * @param surName         Passes in the surname value for the contact.
	 * @param street          Passes in the street value for the contact.
	 * @param town            Passes in the town value for the contact.
	 * @param postcode        Passes in the post code value for the contact.
	 * @param telephoneNumber Passes in the telephone number value for the contact.
	 */
	public Contact(String firstName, String surName, String street, String town, String postcode,
			String telephoneNumber) {

		this.telephoneNumber = telephoneNumber;
		this.firstName = firstName;
		this.surname = surName;
		this.street = street;
		this.town = town;
		this.postcode = postcode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurName(String surName) {
		this.surname = surName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostCode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public String toString() {
		return "Contact First Name : " + firstName + ", Surname : " + surname + ", Street : " + street + ", Town : "
				+ town + ", Postcode : " + postcode + ", Telephone Number : " + telephoneNumber;
	}
}
