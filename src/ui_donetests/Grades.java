package ui_donetests;

import java.sql.SQLException;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import tablesStructures.DoneTest;

public class Grades {
	
	public static void addGradesToDoneTest(DoneTest test) throws SQLException{
		DatabaseActions.executeUpdate(DatabaseUpdatingScripts.insertInto(test));
	}
	
	public static int getStudentID(String firstName,String lastName) throws SQLException {
		String query = "SELECT ID FROM Students WHERE f_name = '" + firstName + "'" + 
							" AND l_name = '" + lastName + "'";
		Object[][] value = DatabaseActions.getAllQueryData(query);
		if(value.length==0)
			throw new SQLException("No exists such student");
		int id = (int)value[0][0];
		if(id!=0)
			return id;
		else
			throw new SQLException("No exists such student");
	}
	
	

}
