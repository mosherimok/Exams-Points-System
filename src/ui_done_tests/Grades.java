package ui_done_tests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import tables.Table;
import tables.TblDoneTests;
import tablesStructures.DoneTest;

public class Grades {
	
	public static void addGradesToTest(DoneTest test,Statement statement) throws SQLException{
	
		/*String[] columnsIdentifiers = null;
		
		columnsIdentifiers = TABLE.getColumnsIdentifiers();*/
		
		DatabaseActions.executeUpdate(DatabaseUpdatingScripts.insertInto(test));
	}
	
	public static int getStudentID(String studentFullName,Statement statement) throws SQLException {
		String[] f_l_names = studentFullName.split(" ");
		String query = "SELECT ID FROM Students WHERE f_name = '" + f_l_names[0] + "'" + 
							" AND l_name = '" + f_l_names[1] + "'";
		
//		Vector<Vector<Object>> id = Database.executeQuery(query);
		ResultSet result = statement.executeQuery(query);
		result.next(); // cursor on first index
		int id = result.getInt(1);
		if(id!=0)
			return id;
		else
			throw new SQLException("No exists such student");
	}
	
	

}
