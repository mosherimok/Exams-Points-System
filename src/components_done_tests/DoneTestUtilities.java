package components_done_tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.Condition;
import database.ConnectionProperties;
import database.Database;
import database.DatabaseUpdatingScripts;
import database.DatabaseUtilities;
import database.StudentUtility;
import tablesStructures.DoneTest;


public class DoneTestUtilities {
	
	private static void addPointsByGrade(int studentId, String testCategory ,int grade) throws SQLException{
		String pointsScript = "SELECT points75, points85, points95 FROM "+
				"TestsCategories WHERE CategoryName = '" + testCategory + "'";
		
		Object[][] pointsValues = Database.executeQuery(pointsScript);
		int points75 = (int) pointsValues[0][0];
		int points85 = (int) pointsValues[0][1];
		int points95 = (int) pointsValues[0][2];
		
		int points = 0;
		if(grade>=75 && grade<85)
			points = points75;
		else if(grade >= 85 && grade < 95)
			points = points85;
		else if(grade>=95)
			points = points95;
		
		String updateScript = "UPDATE Students SET\n"+ 
				"points = points + " + points + "\n"+
				"WHERE ID = " + studentId;
		
		Database.executeUpdate(updateScript);
	}
	
	private static void removeGradeToDoneTest(int studentID, String testCategory,int grade) throws SQLException{
		String pointsScript = "SELECT points75, points85, points95 FROM "+
				"TestsCategories WHERE CategoryName = '" + testCategory + "'";
		
		Object[][] pointsValues = Database.executeQuery(pointsScript);
		int points75 = (int) pointsValues[0][0];
		int points85 = (int) pointsValues[0][1];
		int points95 = (int) pointsValues[0][2];
		
		int points = 0;
		if(grade>=75 && grade<85)
			points = points75;
		else if(grade >= 85 && grade < 95)
			points = points85;
		else if(grade>=95)
			points = points95;
		
		String updateScript = "UPDATE Students SET\n"+ 
				"points = points - " + points + "\n"+
				"WHERE ID = " + studentID;
		
		Database.executeUpdate(updateScript);
	}
	
	public static void addDoneTest(DoneTest doneTest) throws SQLException{
		boolean previousState = Database.closeConnectionWhenDoneOperation;
		Database.closeConnectionWhenDoneOperation= false;
		
		String scriptCategories = "SELECT category,name FROM Tests WHERE rowid = " + doneTest.getTestid();
		String category = (String) Database.executeQuery(scriptCategories)[0][0];
		String name = (String) Database.executeQuery(scriptCategories)[0][1];

		Database.executeUpdate(DatabaseUpdatingScripts.insertInto(doneTest));
		
		addPointsByGrade(doneTest.getStudentid(), category, doneTest.getGrade());
		
		try {
			registerPointsInHistoryArchive(doneTest.getStudentid(),
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
					String.format("מבחן ב %s בשם %s",category,name));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		Database.closeConnectionWhenDoneOperation= previousState;
	}
	
	public static void changeDoneTestGrade(int studentID,int testID,int grade) throws SQLException{
		boolean previousState = Database.closeConnectionWhenDoneOperation;
		Database.closeConnectionWhenDoneOperation= false;
		
		String scriptCategories = "SELECT category,name FROM Tests WHERE rowid = " + testID;
		String category = (String) Database.executeQuery(scriptCategories)[0][0];
		String name = (String) Database.executeQuery(scriptCategories)[0][1];
		
		int oldGrade = (int) Database.executeQuery(String.format("SELECT grade FROM doneTests"
				+ " WHERE id = %d and testid = %d",studentID,testID))[0][0];

		Database.executeUpdate(String.format("UPDATE TABLE DoneTests SET grade = %d"
				+ " WHERE studentid = %d and testid = %d",grade,studentID,testID));
		
		removeGradeToDoneTest(studentID, category, oldGrade);
		
		addPointsByGrade(studentID, category, grade);
		
		try {
			registerPointsInHistoryArchive(studentID,
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
					String.format("השתנה ציון למבחן ב %s בשם %s",category,name));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		Database.closeConnectionWhenDoneOperation= previousState;
	}
	
	public static void registerPointsInHistoryArchive(int studentID,Integer points,String date,String reason) throws ClassNotFoundException{
		//REMINDER: when not in dubugging change the path to the history points archive database.
		try {
			if(points==null)
				points = StudentUtility.getCurrentPoints(studentID);
			
			Class.forName(ConnectionProperties.DRIVER_CLASS);
			Connection connection = DriverManager.getConnection(ConnectionProperties.DATABASE_POINTS_HISTORY_ADRESS,ConnectionProperties.points_history_properties);
			ResultSet rs = connection.getMetaData().getTables(null, null, "%", null);
		    while (rs.next()) {
		      System.out.println(rs.getString(3));
		    }
			PreparedStatement stmt = null;
			if(reason!=null){
				stmt = connection.prepareStatement("Insert Into PointsHistory Values(?,?,?,?)");
				stmt.setInt(1, studentID);
				stmt.setString(2, date);
				stmt.setInt(3, points);
				stmt.setString(4, reason);
				stmt.executeUpdate();
			}
			else{
				stmt = connection.prepareStatement("Insert Into PointsHistory(studentid,date,points)"
						+ " Values(?,?,?");
				stmt.setInt(1, studentID);
				stmt.setString(2, date);
				stmt.setInt(3, points);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(ArrayIndexOutOfBoundsException ex){ex.printStackTrace();}
		catch(NullPointerException ex){ex.printStackTrace();}
	}
	
	public static void registerPointsInHistoryArchive(int studentID,String date,String reason) throws ClassNotFoundException{
		registerPointsInHistoryArchive(studentID,null, date, reason);
	}

}
