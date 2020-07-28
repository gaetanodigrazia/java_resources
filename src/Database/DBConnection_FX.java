/**
* This is a library class that allows a programmer to establish a connection with a mysql database
* 
* 
* @author  Gaetano Di Grazia
* @version 1.0
* @since   08-08-2018
* []
*/

package customlibrary.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DBConnection_FX {
	private String database_name, database_username, database_password, title_error, error_message, url;
	DatabaseConnection db;
	Connection conn;
	
	/**
	 * Default blank constructor
	 */
	public DBConnection_FX() {

	}

	/**
	 * It is the constructor that set all the info that are necessary to establish a
	 * connection to a Database.
	 *
	 *
	 * @param database_name     is the name of Database you want to connect to
	 * @param database_username is the username of the user that is allowed to
	 *                          access to the Database
	 * @param database_password is the connection password that allows you to access
	 *                          to the localhost connection. Usually default is "",
	 *                          in this case you can use the other constructor
	 * @param title_error       is the Error Title of an alert windows that
	 *                          communicate with the user/programmer that an error
	 *                          during the connection as occurred
	 * @param error_message     is the Error Message that the Windows show
	 */
	public DBConnection_FX(String database_name, String database_username, String database_password,
			String title_error, String error_message) {
		setDatabase_name(database_name);
		setDatabase_username(database_username);
		setDatabase_password(database_password);
		setTitle_error(title_error);
		setError_message(error_message);
		setUrl("jdbc:mysql://localhost:3306/" + database_name + "?useSSL=false&serverTimezone=UTC");
	}

	/**
	 * It is the constructor that set all the info that are necessary to establish a
	 * connection to a Database. This constructor set the database_password
	 * automatically to default password that is: ""
	 *
	 *
	 * @param database_name     is the name of Database you want to connect to
	 * @param database_username is the username of the user that is allowed to
	 *                          access to the Database Usually default is "", in
	 *                          this case you can use the other constructor
	 * @param title_error       is the Error Title of an alert windows that
	 *                          communicate with the user/programmer that an error
	 *                          during the connection as occurred
	 * @param error_message     is the Error Message that the Windows show
	 */
	public DatabaseConnection(String database_name, String database_username, String title_error,
			String error_message) {
		setDatabase_name(database_name);
		setDatabase_username(database_username);
		setDatabase_password("");
		setTitle_error(title_error);
		setError_message(error_message);
		setUrl("jdbc:mysql://localhost:3306/" + database_name + "?useSSL=false&serverTimezone=UTC");
	}

	/**
	 * Method that establish the connection to the database previously setted
	 *
	 * @return Connection object that allows you to access to the database and
	 *         manipulate data
	 */
	public Connection Connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(this.getUrl(), this.getDatabase_username(),
					this.getDatabase_password());
			return conn;
		} catch (ClassNotFoundException e) {
			Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
			ErrorMessage();
		}
		return null;
	}

	/**
	 * Method that create and show a modal windows when an error occurred
	 *
	 */
	public void ErrorMessage() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(this.getTitle_error());
		alert.setHeaderText(null);
		alert.setContentText(this.getError_message());
		alert.showAndWait();
	}

	/**
	 * Method that get Error Window Title previously setted
	 *
	 * @return the error window title: String title_error
	 */
	public String getTitle_error() {
		return title_error;
	}

	/**
	 * Method that set the Error Window Title
	 *
	 * @param the error window title: String title_error
	 */
	public void setTitle_error(String title_error) {
		this.title_error = title_error;
	}

	/**
	 * Method that get the Database name previously setted
	 *
	 * @return the database name: String database_name
	 */
	public String getDatabase_name() {
		return database_name;
	}

	/**
	 * Method that set the Database name
	 *
	 * @param the database name: String database_name
	 */
	public void setDatabase_name(String database_name) {
		this.database_name = database_name;
	}

	/**
	 * Method that get the Database username previously setted
	 *
	 * @return the database username: String database_username
	 */
	public String getDatabase_username() {
		return database_username;
	}

	/**
	 * Method that set the Database username
	 *
	 * @param the database username: String database_username
	 */
	public void setDatabase_username(String database_username) {
		this.database_username = database_username;
	}

	/**
	 * Method that get the Error Window message previously setted
	 *
	 * @return the error message: String error_message
	 */
	public String getError_message() {
		return error_message;
	}

	/**
	 * Method that set the Error Window message
	 *
	 * @param the error message: String error_message
	 */
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	/**
	 * Method that get the Database password previously setted
	 *
	 * @return the database password: String database_password
	 */
	public String getDatabase_password() {
		return database_password;
	}

	/**
	 * Method that set the Database Password
	 *
	 * @param the database password: String database_password
	 */
	public void setDatabase_password(String database_password) {
		this.database_password = database_password;
	}

	/**
	 * Method that get the Url for connecting to the database previously setted
	 *
	 * @return the database connection url: String url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Method that set the Url for connecting to the database
	 *
	 * @param the database connection url: String url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	/**
	 * Method that open a connection
	 *
	 */
	public void openConnection() throws SQLException {
		db = new DatabaseConnection();
		conn = db.Connect();
	}


	/**
	 * Method that close a connection
	 *
	 */
	public void closeConnection() throws SQLException {
		conn.close();
	}


}
