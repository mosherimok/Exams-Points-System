package ui_done_tests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import database.ResultSetHandle;
import database.StatementHandle;
import tablesStructures.Student;
import ui_done_tests.Points;

public class PointsTest {

	private static final int STUDENT_ID = 318358587;
	private final int TEST_ID = 1;
	private final int GRADE = 95;
	private final int EXPECTED_POINTS = 10;
	private int previousPoints;
	private static Student student;
	private static Condition condition;
	
	@BeforeClass
	public static void initStudent(){
		student = new Student();
		
		condition = new Condition();
		condition.addCondition("id", STUDENT_ID);
	}
	
	@Before
	public void resetPoints(){
		StatementHandle handle = new StatementHandle() {
			
			@Override
			public void handle(Statement statement) throws SQLException {
				ResultSet result = statement.executeQuery("SELECT points FROM Students WHERE ID = " + 
						STUDENT_ID);
				result.next();
				
				previousPoints = result.getInt(1);
				
				student.setPoints(0);
				String scriptUpdate = DatabaseUpdatingScripts.updateTable(student, condition);
				
				result = statement.executeQuery("SELECT points FROM Students WHERE ID = " + STUDENT_ID);
				
				result.next();
				Assert.assertTrue("Points did not reset",result.getInt(1)==0);
			}
		};
			

	}
	
	
	@Test
	public void pointsShouldBeUpdate(){
		Points.updatePoints(TEST_ID, STUDENT_ID, GRADE);
		
		String scriptValidate = "SELECT points FROM Students WHERE ID = " + STUDENT_ID;
		StatementHandle handle = new StatementHandle() {
			
			@Override
			public void handle(Statement stmt) throws SQLException {
				ResultSet result = stmt.executeQuery(scriptValidate);
				result.next();
				Assert.assertEquals("Points did not updated",
						EXPECTED_POINTS,result.getInt(1));
			}
		};
		
		DatabaseActions.executeQuery(handle);
	}
	
	@After
	public void restorePoints(){
		Condition condition = new Condition();
		condition.addCondition("ID", STUDENT_ID);
		Student student = new Student();
		student.setPoints(previousPoints);
		try {
			DatabaseActions.executeUpdate(DatabaseUpdatingScripts.updateTable(student, condition));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
