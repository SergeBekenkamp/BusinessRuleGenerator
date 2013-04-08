package databaseControl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {
	private Connection con;
	Properties properties;

	public DatabaseConnection(String config) {
		properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(config + ".properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {

			String url = properties.getProperty("jdbc.url");
			String driver = properties.getProperty("jdbc.driver");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");

			// Load (and therefore register) the Oracle Driver
			Class.forName(driver);

			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load database driver: "
					+ e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException caught: " + e.getMessage());
		}
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public boolean executeQuery(String query) {
		try {
			Statement statement = con.createStatement();
			statement.execute(query);
		} catch (java.sql.SQLException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}

	public ResultSet doQuery(String query) {
		ResultSet result = null;
		try {
			Statement statement = con.createStatement();
			result = statement.executeQuery(query);
		} catch (java.sql.SQLException e) {
			System.err.println(e);
		}
		return result;
	}

}
