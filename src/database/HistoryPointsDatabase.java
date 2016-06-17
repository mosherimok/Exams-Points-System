package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HistoryPointsDatabase {

	private static Connection connection;
	public static boolean closeWhenFunctionDone = true;
	
	private static void initConnection() throws SQLException{
		if(connection == null){
			try {
				Class.forName(ConnectionProperties.DRIVER_CLASS);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new SQLException(e.getMessage());
			}
			//REMINDER: when not in dubugging change the path to the history points archive database.
			connection = DriverManager.getConnection(ConnectionProperties.DATABASE_POINTS_HISTORY_ADRESS,ConnectionProperties.points_history_properties);
		}
	}
	
	private static void closeConnection() throws SQLException{
		if(connection!=null){
			connection.close();
			connection = null;
		}
	}
	
	public static Object[][] getStudentHistoryByID(int id) throws SQLException{
		initConnection();
		
		String script = "SELECT Date,Points,Reason FROM PointsHistory WHERE StudentID = ? order by date";
		try(PreparedStatement stmt = connection.prepareStatement(script)){
			stmt.setInt(1, id);
			ArrayList<Object[]> data = new ArrayList<>();
			try(ResultSet result = stmt.executeQuery()){
				final int cols = result.getMetaData().getColumnCount();
				while(result.next()){
					Object[] row = new Object[cols];
					try {
						row[0] = getFormatedDate(result.getString(1), "dd-MM-yyyy");
					} catch (ParseException e) {
						e.printStackTrace();
					}
					for(int i=1;i<cols;i++)
						row[i] = result.getObject(i+1);
					data.add(row);
				}
				System.out.println("Array size: " + data.size() + " Resultset cols: " + cols);
				return data.toArray(new Object[data.size()][cols]);
			}
		}
		finally{
			if(closeWhenFunctionDone)
				closeConnection();
		}
	}
	
	public static void insertRecord(int studentID,String date,int points,String reason) throws SQLException{
		initConnection();
		
		String script = "INSERT INTO PointsHistory Values(?,?,?,?)";
		try(PreparedStatement stmt = connection.prepareStatement(script)){
			System.out.println(String.format("id = %d points = %d date = %s reason = %s",studentID,points,
					date,reason));
			stmt.setInt(1, studentID);
			stmt.setString(2, date);
			stmt.setInt(3, points);
			stmt.setString(4, reason);
			stmt.executeUpdate();
		}
		finally{
			if(closeWhenFunctionDone)
				closeConnection();
		}
	}
	
	public static void insertRecord(int studentID,String date, int points) throws SQLException{
		initConnection();

		String script = "INSERT INTO HistoryPoints(StudentID,Date,Points) Values(?,?,?)";
		try(PreparedStatement stmt = connection.prepareStatement(script)){
			stmt.setInt(1, studentID);
			stmt.setString(2, date);
			stmt.setInt(3, points);
			stmt.executeQuery();
		}
		finally{
			if(closeWhenFunctionDone)
				closeConnection();
		}
	}
	
	private static String getFormatedDate(String date, String pattern) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(formatter.parse(date));
	}
	
}
