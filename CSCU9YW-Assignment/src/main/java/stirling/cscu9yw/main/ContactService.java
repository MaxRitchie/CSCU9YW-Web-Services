package stirling.cscu9yw.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class holds all the method calls for the services that are used to
 * control the RESTful services with the database, it allows the actions being
 * carried out on the web service to be done to the database at the same time.
 * 
 * It does this by calling the ContactRepository which holds the JpaRepository,
 * this controls and handles the actions required for the web service and the
 * database to work together.
 * 
 * @author 2636157
 *
 */

// Indicates that the class is is to be used as a service.
@Service
// The @Transactional annotation provides the application the ability to declaratively control 
// transaction boundaries on CDI managed beans, as well as classes defined as managed beans.
@Transactional
public class ContactService {

	// The @Autowired annotation marks the constructor for the class that is to be
	// used by Spring's dependency injection facilities.
	@Autowired
	private ContactRepository contactRepository;

	/**
	 * Calls the ContactRepository to retrieve all contacts.
	 * 
	 * @return Returns all contacts.
	 */
	public List<Contact> listAllContacts() {
		return contactRepository.findAll();
	}

	/**
	 * Calls the ContactRepository to save a contact.
	 * 
	 * @param contact Passes in a contact.
	 */
	public void saveContact(Contact contact) {
		contactRepository.save(contact);
	}

	/**
	 * Calls the ContactRepository to get a single contact with the use of a phone
	 * number.
	 * 
	 * @param telephoneNumber Passes in the telephone number that is used to search
	 *                        for the contact.
	 * @return The searched for contact.
	 */
	public Contact getContact(String telephoneNumber) {
		return contactRepository.findById(telephoneNumber).get();
	}

	/**
	 * Calls the ContactRepository to delete a contact using the contacts telephone
	 * number.
	 * 
	 * @param telephoneNumber Passes in the telephone number of the contact that is
	 *                        used to delete the contact.
	 */
	public void deleteContact(String telephoneNumber) {
		contactRepository.deleteById(telephoneNumber);
	}
}
