package ui_done_tests;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import tablesStructures.Student;
import ui_donetests.Points;

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
		
		DatabaseActions.setCloseConnectionWhenDone(false);
	}
	
	@Before
	public void resetPoints(){
		try {
			String pointsScript = "SELECT points FROM Students WHERE ID = " + 
					STUDENT_ID;
			previousPoints = (int) DatabaseActions.
					getAllQueryData(pointsScript)[0][0];
			
			student.setPoints(0);
			String scriptUpdate = DatabaseUpdatingScripts.updateTable(student, condition);
			DatabaseActions.executeUpdate(scriptUpdate);
		
			int currentPoints = (int) DatabaseActions.
					getAllQueryData(pointsScript)[0][0];
			
			Assert.assertTrue("Points did not reset",currentPoints==0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*StatementHandle handle = new StatementHandle() {
			
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
		};*/
			

	}
	
	
	@Test
	public void pointsShouldBeUpdate(){
		try {
			Points.updatePoints(TEST_ID, STUDENT_ID, GRADE);
			
			String scriptValidate = "SELECT points FROM Students WHERE ID = " + STUDENT_ID;
			Object[][] points = DatabaseActions.getAllQueryData(scriptValidate);
			Assert.assertEquals("Points did not updated",
					EXPECTED_POINTS,(int)points[0][0]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*StatementHandle handle = new StatementHandle() {
			
			@Override
			public void handle(Statement stmt) throws SQLException {
				ResultSet result = stmt.executeQuery(scriptValidate);
				result.next();
				Assert.assertEquals("Points did not updated",
						EXPECTED_POINTS,result.getInt(1));
			}
		};
		
		DatabaseActions.executeQuery(handle);
		*/
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
	
	@AfterClass
	public static void setCloseConnectionWhenDone(){
		DatabaseActions.setCloseConnectionWhenDone(true);
		DatabaseActions.closeStuff();
	}
	
}
