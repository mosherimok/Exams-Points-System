package database;

import java.net.URL;
import java.sql.*;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.sqlite.core.DB;

import main.Main;

public class DatabaseConnection {

	// Connection properties values [see initPropeties() ]:
	private final static String DRIVER_CLASS = "org.sqlite.JDBC";
	//For system-file native.
//	private final static String SERVER_ADDRES = "jdbc:sqlite:/"+"D:/Program Files (x86)/Exams-Points-System/Database/EPS.db";
	//for IDE running native.
	private final static String SERVER_ADDRES = "jdbc:sqlite:Resources/EPS.db";
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
//		properties.put("user",USER_NAME);
//		properties.put("password", PASSWORD);
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
