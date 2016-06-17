package database;

import java.util.Properties;

import main.FilesConstants;

public class ConnectionProperties {

	public final static String DRIVER_CLASS = "org.sqlite.JDBC";
	public final static String DATABASE_EPS_ADRESS = "jdbc:sqlite:/"+FilesConstants.DATABASE_FILE_PATH;
	public final static String DATABASE_POINTS_HISTORY_ADRESS = "jdbc:sqlite:Resources/PointsHistory.db";
	public final static String IS_YEAR_DATE_TYPE = "false";
	
	public static final Properties  eps_properties = new Properties();
	public static final Properties  points_history_properties = new Properties();
	
	static{
		eps_properties.put("yearIsDateType",IS_YEAR_DATE_TYPE);
		points_history_properties.put("yearIsDateType",IS_YEAR_DATE_TYPE);
	}
	
}
