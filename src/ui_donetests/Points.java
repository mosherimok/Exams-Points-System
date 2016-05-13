package ui_donetests;

import java.sql.SQLException;

import database.DatabaseActions;

public class Points {
	
	public static void updatePoints(int testID, int studentId,int grade) throws SQLException{
		boolean previousState = DatabaseActions.isCloseConnectionWhenDone();
		DatabaseActions.setCloseConnectionWhenDone(false);
		String scriptCategories = "SELECT category FROM Tests WHERE rowid = " + testID;
		String category = (String) DatabaseActions.
				getAllQueryData(scriptCategories)[0][0];
		
		String pointsScript = "SELECT points75, points85, points95 FROM "+
				"TestsCategories WHERE CategoryName = '" + category + "'";
		Object[][] pointsValues = DatabaseActions.getAllQueryData(pointsScript);
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
		
		DatabaseActions.setCloseConnectionWhenDone(previousState);
		
		DatabaseActions.executeUpdate(updateScript);

	}

}
