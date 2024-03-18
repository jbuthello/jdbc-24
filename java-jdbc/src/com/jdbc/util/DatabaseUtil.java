package com.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	
	private static final String DRIVER_PATH="com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_URL="jdbc:mysql://localhost:3306/jdbcdb18032024";
	private static final String USERNAME="root";
	private static final String PASSWORD="root";
	
	public DatabaseUtil() {
		try {
			Class.forName(DRIVER_PATH);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Something is not right in DatabaseUtil constructor");
		}
	}//End of Constructor

	public Connection getConnection() {
		Connection connection = null;
		try {
				connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in getConnection method");
		}
		return connection;
	}//End of getConnection
}
