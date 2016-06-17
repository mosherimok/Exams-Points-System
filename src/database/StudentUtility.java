package database;

import java.sql.SQLException;

public class StudentUtility {

	public static int getCurrentPoints(int studentId) throws SQLException {
		return (int)Database.executeQuery("SELECT points FROM Students WHERE ID = " + studentId)[0][0];
	}
	
	public static String getStudentFirstName(int studentId) throws SQLException{
		return (String)Database.executeQuery("SELECT f_name FROM Students WHERE studentID = " + studentId)[0][0];
	}
	
	public static String getStudentLastName(int studentId) throws SQLException{
		return (String)Database.executeQuery("SELECT l_name FROM Students WHERE studentID = " + studentId)[0][0];
	}
	
	public static int getStudentIDbyFullName(String firstName,String lastName) throws SQLException{
		String query = "SELECT ID FROM Students WHERE f_name = '" + firstName + "'" + 
				" AND l_name = '" + lastName + "'";
		Object[][] value = Database.executeQuery(query);
		if(value.length==0)
			throw new SQLException("No exists such student");
		int id = (int)value[0][0];
		if(id!=0)
			return id;
		else
			throw new SQLException("No exists such student");
	}
}
