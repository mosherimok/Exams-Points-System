package ui_done_tests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DatabaseActions;
import database.StatementHandle;

public class Points {
	
	public static void updatePoints(int testID, int studentId,int grade){
		String scriptCategories = "SELECT category FROM Tests WHERE TestID = " + testID;
		StatementHandle handle = new StatementHandle(){
			@Override
			public void handle(Statement statement) throws SQLException {
				ResultSet result = statement.executeQuery(scriptCategories);
				result.next();
				String category = result.getString(1);
				String pointsScript = "SELECT points75, points85, points95 FROM "+
				"TestsCategories WHERE CategoryName = '" + category + "'";
				
				result = statement.executeQuery(pointsScript);
				result.next();
				
				int points75 = (int) result.getInt(1);
				int points85 = (int) result.getInt(2);
				int points95 = (int) result.getInt(3);
				
				int points = 0;
				if(grade>=75 && grade<85)
					points = points75;
				else if(grade >= 85 && grade < 95)
					points = points85;
				else if(grade>=95)
					points = points95;
				
				String update = "UPDATE Students SET\n"+ 
								"points = points + " + points + "\n"+
								"WHERE ID = " + studentId;
				System.out.println(update);
				statement.executeUpdate(update);
			}
		};
		DatabaseActions.executeQuery(handle);
	}

}
