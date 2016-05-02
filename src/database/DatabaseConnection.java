package database;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

	// Connection properties values [see initPropeties() ]:
	private final String USER_NAME="root";
	private final String PASSWORD = "Moshe124578";
	private final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private final String SERVER_ADDRES = "jdbc:mysql://localhost:3306/eps";
	private String IS_YEAR_DATE_TYPE = "false";
	// End.
	
	private Connection connection;
	
	public DatabaseConnection(){
		try {
			Class.forName(DRIVER_CLASS);
			connection = DriverManager.getConnection(SERVER_ADDRES,initProperties());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	private static Connection connection = null;
	
	public Connection getConnection(){
		/*if (connection==null){
			try {
				Class.forName(DRIVER_CLASS);
				connection = DriverManager.getConnection(SERVER_ADDRES,initProperties());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}*/
		try {
			if(connection.isClosed()){
				connection = DriverManager.getConnection(SERVER_ADDRES,initProperties());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	private Properties initProperties(){
		Properties properties = new Properties();
		properties.put("user",USER_NAME);
		properties.put("password", PASSWORD);
		properties.put("yearIsDateType",IS_YEAR_DATE_TYPE);
		return properties;
	}
	
}
