package stirling.cscu9yw.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

/**
 * This class holds all the requests used for the web service, it works along
 * side the ContactController and Unirest to send out requests and retrieve
 * data.
 * 
 * @author Max 2636157
 *
 */
public class Requests {
	private static final String serviceURI = "http://127.0.0.1:8080/";

	/**
	 * The get method retrieves a contact which gets converted into string through
	 * the convertString method, it then gets converted into a JSON object by
	 * getting passed through the convertData method.
	 * 
	 * @param telephoneNumber Passed in for searching a specific contact.
	 * @param enitity         Passed in String value to get the specific piece of
	 *                        data to be returned.
	 * @return A single value that from a contact that has been searched.
	 */
	public static String get(String telephoneNumber, String enitity) {
		String output = null;

		// Try catch statement used to catch any IOExeptions if nothing is returned
		// after searching, if an exception is thrown, the output is set to nothing
		// instead of null to prevent the application from crashing.
		try {
			// Passes in the uri that is used for sending out a request.
			output = (convertString(serviceURI + "/contacts/" + telephoneNumber).toString());
		} catch (IOException e) {
			output = "";
		}

		// If statement used to check if the output is not empty, if not, the output can
		// be converted into JSON format.
		if (!output.equals("")) {
			output = convertData(output, enitity);
			return output;
			// Else, the output is returned back empty.
		} else {
			return output;
		}

	}

	/**
	 * 
	 * The put method grants the ability to edit a contact, it takes in the
	 * parameters from a contact and sends out a HTTP request contain a request body
	 * with the updated contact data through the Unirest put call.
	 * 
	 * @param firstName       Passes in the contacts first name value.
	 * @param surname         Passes in the contacts surname value.
	 * @param street          Passes in the contacts street value.
	 * @param town            Passes in the contacts town value.
	 * @param postcode        Passes in the contacts town value.
	 * @param telephoneNumber Passes in the contacts telephone value.
	 */
	public static void put(String firstName, String surname, String street, String town, String postcode,
			String telephoneNumber) {
		Unirest.setTimeouts(0, 0);
		try {
			HttpResponse<String> response = Unirest.put(serviceURI + "/contacts/" + telephoneNumber)
					.header("Content-Type", "application/json")
					.body("{\r\n    \"firstName\":\"" + firstName + "\", \r\n    \"surname\":\"" + surname
							+ "\",\r\n    \"street\":\"" + street + "\",\r\n    \"town\":\"" + town
							+ "\",\r\n    \"postcode\":\"" + postcode + "\",\r\n    \"telephoneNumber\":\""
							+ telephoneNumber + "\"\r\n}")
					.asString();

		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The post method carries out the creation of a new contact, it does this by
	 * taking in the values for the contact and sending them out in request body
	 * from the Unirest post call.
	 * 
	 * @param firstName       Passes in the contacts first name value.
	 * @param surname         Passes in the contacts surname value.
	 * @param street          Passes in the contacts street value.
	 * @param town            Passes in the contacts town value.
	 * @param postcode        Passes in the contacts town value.
	 * @param telephoneNumber Passes in the contacts telephone number value.
	 */
	public static void post(String firstName, String surname, String street, String town, String postcode,
			String telephoneNumber) {

		Unirest.setTimeouts(0, 0);
		try {
			HttpResponse<String> response = Unirest.post(serviceURI + "/contacts")
					.header("Content-Type", "application/json")
					.body("{\r\n    \"firstName\":\"" + firstName + "\", \r\n    \"surname\":\"" + surname
							+ "\",\r\n    \"street\":\"" + street + "\",\r\n    \"town\":\"" + town
							+ "\",\r\n    \"postcode\":\"" + postcode + "\",\r\n    \"telephoneNumber\":\""
							+ telephoneNumber + "\"\r\n}")
					.asString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The delete method deletes a contact with the use of the contacts telephone
	 * number value number, it does with the Unirest delete call.
	 * 
	 * @param telephoneNumber Passes in the contacts telephone number.
	 */
	public static void delete(String telephoneNumber) {
		Unirest.setTimeouts(0, 0);
		try {
			HttpResponse<String> response = Unirest.delete(serviceURI + "/contacts/" + telephoneNumber).asString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method gets used with the get method to convert the output from the get
	 * request into String format, it uses the inputStream to do it and returns it
	 * with the UTF_8 charsets.
	 * 
	 * @param requestURL Passed in url that will be used to search and retrieve a
	 *                   contact.
	 * @return A string value of the contact retrieved.
	 * @throws IOException Signals that an I/O exception of some sort has occurred.
	 *                     This class is the general class of exceptions produced by
	 *                     failed or interrupted I/O operations.
	 */
	private static String convertString(String requestURL) throws IOException {
		URL url = new URL(requestURL);
		try (InputStream inputStream = url.openStream()) {
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		}
	}

	/**
	 * This method converts the String value of the contact passed through into a
	 * JSON object, this then gets used to search for a specific piece of data from
	 * the contact using a passed in string value called entity.
	 * 
	 * @param forConversion Passes in the contact that needs to be converted.
	 * @param enitity       Passes in a String value for what piece of data is to be
	 *                      pulled and returned.
	 * @return A single piece of data from the contact.
	 */
	public static String convertData(String forConversion, String enitity) {

		String model = null;
		JSONObject contact = new JSONObject(forConversion);

		model = contact.getString(enitity);

		return model;
	}

}
