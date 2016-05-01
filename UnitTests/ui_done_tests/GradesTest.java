package ui_done_tests;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import database.Condition;
import database.DatabaseActions;
import database.DatabaseConnection;
import database.DatabaseUpdatingScripts;
import database.StatementHandle;
import tablesStructures.DoneTest;
import ui_done_tests.Grades;

public class GradesTest {

	private final String TABLE_NAME = "DoneTests";
	private final int TEST_ID = 2;
	private final String STUDENT_FULL_NAME = "Moshe Rimok";
	private final int STUDENT_ID = 318358587;
	private final int GRADE = 95;
	
	
	
	@Test
	public void gradeShouldBeAdd(){
		StatementHandle handle = new StatementHandle() {
			
			@Override
			public void handle(Statement statement) throws SQLException {
				DoneTest test = new DoneTest();
				test.setTestid(TEST_ID);
				test.setGrade(GRADE);
				test.setStudentid(Grades.getStudentID(STUDENT_FULL_NAME, statement));
				
				Grades.addGradesToTest(test,statement);
				
				statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s = %d AND %s = %d",
						"TestID",TABLE_NAME,"TestID",TEST_ID,"StudentID",STUDENT_ID));
				
				statement.getResultSet().next();
				
				Assert.assertTrue("Grade did not add to this test",statement.getResultSet().isLast());
			}
		};
		
	}
	
	@After
	public void deleteGrade(){
		Condition condition = new Condition();
		condition.addCondition("TestID", TEST_ID);
		condition.addCondition("StudentID", STUDENT_ID);
		try {
			DatabaseActions.executeUpdate(DatabaseUpdatingScripts.deleteFrom(TABLE_NAME,condition));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
