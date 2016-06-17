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
import database.HistoryPointsDatabase;
import database.StudentUtility;
import tablesStructures.DoneTest;


public class DoneTestUtilities {
	
	private static void addStudentPointsByCategoryAndGrade(int studentId, String testCategory ,int grade) throws SQLException{
		int points = calculatePointsByCategoryAndGrade(testCategory, grade);
		
		String updateScript = "UPDATE Students SET\n"+ 
				"points = points + " + points + "\n"+
				"WHERE ID = " + studentId;
		
		Database.executeUpdate(updateScript);
	}
	
	private static void lowerPointsByCategoryAndGrade(int studentID, String testCategory,int grade) throws SQLException{
		
		int points = calculatePointsByCategoryAndGrade(testCategory, grade);
		
		String updateScript = "UPDATE Students SET\n"+ 
				"points = points - " + points + "\n"+
				"WHERE ID = " + studentID;
		
		Database.executeUpdate(updateScript);
	}
	
	public static void addDoneTest(DoneTest doneTest) throws SQLException{
		boolean previousState = Database.closeConnectionWhenDoneOperation;
		Database.closeConnectionWhenDoneOperation= false;
		
		String scriptCategories = "SELECT category,name FROM Tests WHERE rowid = " + doneTest.getTestid();
		Object[][] values = Database.executeQuery(scriptCategories);
		String category = (String)values[0][0];
		String name = (String)values[0][1];

		String script = DatabaseUpdatingScripts.insertIntoPreparedStatementScript(doneTest);
		Database.executeSinglePreparedStatement(script,doneTest.getValues());
		
		addStudentPointsByCategoryAndGrade(doneTest.getStudentid(), category, doneTest.getGrade());
		
		registerPointsInHistoryArchive(doneTest.getStudentid(),
				new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
				String.format("מבחן ב%s בשם %s",category,name));
	
	
		Database.closeConnectionWhenDoneOperation= previousState;
		if(previousState){
			Database.closeConnection();
		}
	}
	
	public static void changeDoneTestGrade(int studentID,int testID,int grade) throws SQLException{
		boolean previousState = Database.closeConnectionWhenDoneOperation;
		Database.closeConnectionWhenDoneOperation= false;
		
		String scriptCategories = "SELECT category,name FROM Tests WHERE rowid = " + testID;
		Object[][] values = Database.executeQuery(scriptCategories);
		String category = (String)values[0][0];
		String name = (String)values[0][1];
		
		int oldGrade = (int) Database.executeQuery(String.format("SELECT grade FROM doneTests"
				+ " WHERE studentid = %d and testid = %d",studentID,testID))[0][0];

		Database.executeUpdate(String.format("UPDATE DoneTests SET grade = %d"
				+ " WHERE studentid = %d and testid = %d",grade,studentID,testID));
		
		lowerPointsByCategoryAndGrade(studentID, category, oldGrade);
		
		addStudentPointsByCategoryAndGrade(studentID, category, grade);
		

		registerPointsInHistoryArchive(studentID,
				new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
				String.format("השתנה ציון למבחן ב%s בשם %s",category,name));
	
	
		Database.closeConnectionWhenDoneOperation= previousState;
		if(previousState){
			Database.closeConnection();
		}
	}
	
	public static void registerPointsInHistoryArchive(int studentID,Integer points,String date,String reason) throws SQLException{
		if(points==null)
			points = StudentUtility.getCurrentPoints(studentID);
		HistoryPointsDatabase.insertRecord(studentID,date, points);
	}
	
	public static void registerPointsInHistoryArchive(int studentID,String date,String reason) throws SQLException{
		registerPointsInHistoryArchive(studentID,null, date, reason);
	}
	
	private static int calculatePointsByCategoryAndGrade(String category,int grade) throws SQLException{
		String pointsScript = "SELECT points75, points85, points95 FROM "+
				"TestsCategories WHERE CategoryName = " + category;
		
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
		
		return points;
	}

}
