package stirling.cscu9yw.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class for the applications web service, when this is run the
 * spring service is started.
 * 
 * @author 2636157
 *
 */
@SpringBootApplication
public class Main {

	/**
	 * Starts the spring application service.
	 * 
	 * @param args An array of sequence of characters (Strings) that are passed to
	 *             the "main" function.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}