package database;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

	// Connection properties values [see initPropeties() ]:
	private final static String USER_NAME="root"; // remote mysql server username: kbyroot
	private final static String PASSWORD = "Moshe124578";
	private final static String DRIVER_CLASS = "org.sqlite.JDBC";
	private final static String SERVER_ADDRES = "jdbc:sqlite:Resources/EPS.db";
//	private final String SERVER_ADDRES = "jdbc:mysql://johnny.heliohost.org/kbyroot_eps";
	private static String IS_YEAR_DATE_TYPE = "false";
	// End.
	
	private static Connection connection;
	
//	private static Connection connection = null;
	
	public static synchronized Connection getConnection(){
		if (connection==null){
			try {
				Class.forName(DRIVER_CLASS);
				connection = DriverManager.getConnection(SERVER_ADDRES,initProperties());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	private static Properties initProperties(){
		Properties properties = new Properties();
		properties.put("user",USER_NAME);
		properties.put("password", PASSWORD);
		properties.put("yearIsDateType",IS_YEAR_DATE_TYPE);
		return properties;
	}
	
	public static synchronized void closeConnection(){
		try{
			if(connection != null && !connection.isClosed()){
				connection.close();
				connection = null;
			}
		}catch(SQLException ex){ex.printStackTrace();}
		
	}
	
}
