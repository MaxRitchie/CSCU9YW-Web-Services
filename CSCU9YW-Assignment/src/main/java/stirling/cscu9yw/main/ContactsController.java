package stirling.cscu9yw.main;

import java.util.List;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The controller holds of all of the RESTful web services, any action that is
 * carried out with communicating with the web service is done through this
 * class.
 * 
 * @author 2636157
 *
 */

// The Spring RestController annotation is used to create RESTful web services using the Spring MVC. 
// The RestController takes care of mapping request data of the defined request.
@RestController
public class ContactsController {

	// The @Autowired annotation is added to the ContactService to be used by
	// Spring's dependency injection facilities. This allows method calls to be made
	// to class that holds all of the requests actions.
	@Autowired
	ContactService contactService;

	/**
	 * Annotation for mapping HTTP GET requests, retrieves and returns all contacts.
	 * 
	 * @return All contacts.
	 */
	@GetMapping("/contacts")
	public List<Contact> list() {
		return contactService.listAllContacts();
	}

	/**
	 * Annotation for mapping HTTP GET requests, retrieves a single contact using a
	 * telephone number.
	 * 
	 * @param telephoneNumber Passed in telephone number, the unique identifier.
	 * @return A single contact and a HTTP response.
	 */
	@GetMapping("/contacts/{telephoneNumber}")
	public ResponseEntity<Contact> get(@PathVariable String telephoneNumber) {
		try {
			Contact contact = contactService.getContact(telephoneNumber);
			// Returns the contact along with a successful HTTP response.
			return new ResponseEntity<Contact>(contact, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// Only returns a failure HTTP response.
			return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Annotation for mapping HTTP POST requests, creates a new contact, takes in a
	 * request body that holds all of the required data to create a new contact.
	 * 
	 * @param contact Passed in request body holding a new contacts information.
	 */
	@PostMapping("/contacts")
	public void add(@RequestBody Contact contact) {
		contactService.saveContact(contact);
	}

	/**
	 * Annotation for mapping HTTP PUT requests, retrieves and returns a contact by
	 * taking in a request body that holds the data for a contact and a telephone
	 * number to find the specific contact to be updated.
	 * 
	 * @param contact         Passed in request body holding updated information for
	 *                        the contact.
	 * @param telephoneNumber Passed in telephone number, the unique identifier.
	 * @return A HTTP response.
	 */
	@PutMapping("/contacts/{telephoneNumber}")
	public ResponseEntity<?> update(@RequestBody Contact contact, @PathVariable String telephoneNumber) {
		try {
			Contact existContact = contactService.getContact(telephoneNumber);
			contact.setTelephoneNumber(telephoneNumber);
			// Saves the contact information.
			contactService.saveContact(contact);
			// Returns a successful HTTP response.
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// Returns a failure HTTP response.
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Annotation for mapping HTTP DELETE requests, retrieves the required contact
	 * using the passed in phone number and deletes it.
	 * 
	 * @param telephoneNumber Passed in telephone number, the unique identifier.
	 */
	@DeleteMapping("/contacts/{telephoneNumber}")
	public void delete(@PathVariable String telephoneNumber) {
		contactService.deleteContact(telephoneNumber);
	}
}
