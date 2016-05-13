package ui_donetests;

import java.sql.SQLException;
import java.sql.Statement;


import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import tablesStructures.DoneTest;

public class Grades {
	
	public static void addGradesToDoneTest(DoneTest test) throws SQLException{
		DatabaseActions.executeUpdate(DatabaseUpdatingScripts.insertInto(test));
	}
	
	public static int getStudentID(String studentFullName) throws SQLException {
		String[] f_l_names = studentFullName.split(" ");
		String query = "SELECT ID FROM Students WHERE f_name = '" + f_l_names[0] + "'" + 
							" AND l_name = '" + f_l_names[1] + "'";
		
		int id = (int)DatabaseActions.getAllQueryData(query)[0][0];
		if(id!=0)
			return id;
		else
			throw new SQLException("No exists such student");
	}
	
	

}
