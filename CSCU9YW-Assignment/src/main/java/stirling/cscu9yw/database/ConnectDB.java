package stirling.cscu9yw.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.sql.Connection;

/**
 * 
 * This class controls the database display on the main menu display in swing
 * application.
 * 
 * @author 2636157
 *
 */
public class ConnectDB {

	static Connection connection = null;
	static String databaseName = "contactsdb";

	// Sets the url that is used to find the database running on the local host
	// using port 3306, it
	// uses the database name to find to correct database to establish a connection.
	static String url = "jdbc:mysql://localhost:3306/" + databaseName;

	// Setting the username and password that is required to gain access to the
	// database.
	static String username = "root";
	static String password = "root1";

	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;

	static Statement statement = null;

	/**
	 * This method retrieves everything that is currently being stored in the
	 * database, it does this by sending a query to the database and returns an
	 * array that holds all the rows and columns found.
	 * 
	 * @return An array containing all data currently being stored in the database.
	 */
	public String[][] db_select() {

		// Creating a initialising a new String array, the first int value (20) sets the
		// size of the array for how many rows with be allocated and the second int
		// value (6) sets the size of the array for how many columns with be allocated.
		String[][] data = new String[20][6];

		// Try catch statement used to ensure that the application does not crash if a
		// error were to occur when try to connect to the database. Displays an error
		// message to the command prompt if so.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM contacts");

			int i = 0;

			// While statement used to go through the results set that contains everything
			// from the database, it goes through each row until all have been rows have
			// been cycled through the for statement.
			while (resultSet.next()) {
				// For statement used to go through each column of the current row from the
				// resultSet and stores the value in the data array. It does this process six
				// times because there are six different columns in the database.
				for (int j = 0; j < 6; j++) {
					data[i][j] = resultSet.getString(j + 1);
				}
				i += 1;
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("Error: Cannot Connect to the Database");
			e.printStackTrace();
		}
		return data;
	}
}