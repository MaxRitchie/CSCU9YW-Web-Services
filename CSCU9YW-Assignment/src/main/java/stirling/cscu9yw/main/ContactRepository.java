package stirling.cscu9yw.main;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is used work with the database and the web service
 * simultaneously, it does this by using the JpaRepository which is a standard
 * way of persisting objects into a database as well as allowing requests to be
 * sent to the web service.
 * 
 * It does this by using the contact object along with a String value which is
 * used as the unique identifier for finding a contact, which is the telephone
 * number in this case.
 * 
 * @author 2636157
 *
 */
public interface ContactRepository extends JpaRepository<Contact, String> {

}
