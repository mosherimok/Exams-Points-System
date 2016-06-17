package ui_done_tests;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import components_done_tests.DoneTestUtilities;

import database.Condition;
import database.Database;
import database.DatabaseUpdatingScripts;
import tablesStructures.DoneTest;
import tablesStructures.Student;

public class PointsTest {

	private final int GRADE = 95;
	private final int EXPECTED_POINTS = 60;
	private static Student student;
	private static tablesStructures.Test test;
	private static Condition condition;
	
	@BeforeClass
	public static void initStuff(){
		Database.closeConnectionWhenDoneOperation = false;
		
		final int STUDENT_ID = 123456789;
		final String STUDENT_F_NAME = "Test";
		final String STUDENT_L_NAME ="Test";
		final short RECEPTION_YEAR = 2015;
		final int POINTS = 0;
		
		student = new Student(STUDENT_ID,STUDENT_F_NAME,STUDENT_F_NAME,RECEPTION_YEAR,POINTS);
		condition = new Condition(student.getPrimaryKeyValue());
		
		final String TEST_NAME = "TestForTesting";
		final String TEST_CATEGORY = "воша";
		final String TEST_DATE = "2015-1-1";
		
		test = new tablesStructures.Test(TEST_NAME,TEST_CATEGORY,TEST_DATE);
		
		try {
			String script = DatabaseUpdatingScripts.insertIntoPreparedStatementScript(student);
			Database.executeSinglePreparedStatement(script,student.getValues());
			
			script = DatabaseUpdatingScripts.insertIntoPreparedStatementScript(test);
			Database.executeSinglePreparedStatement(script,test.getValues());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		try {
			int testid = (int)Database.executeQuery(String.format("SELECT rowid FROM Tests WHERE name='%s' and testdate='%s'",
					TEST_NAME,TEST_DATE))[0][0];
			test.setTestid(testid);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Could not retrieve the testID");
		}
		
	}
	
	@Before
	public void resetPoints(){
		try {
			student.setPoints(0);
			String scriptUpdate = DatabaseUpdatingScripts.updateTable(student, condition);
			Database.executeUpdate(scriptUpdate);
		
			String pointsScript = "SELECT points FROM Students WHERE ID = " + 
					student.getId();
			int currentPoints = (int) Database.executeQuery(pointsScript)[0][0];
			
			Assert.assertTrue("Points did not reset",currentPoints==0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	
	@Test
	public void pointsShouldBeUpdate(){
		try {
			DoneTestUtilities.addDoneTest(new DoneTest(student.getId(),test.getTestid(),GRADE));
			
			String scriptValidate = "SELECT points FROM Students WHERE ID = " + student.getId();
			Object[][] points = Database.executeQuery(scriptValidate);
			Assert.assertEquals("Points did not updated",
					EXPECTED_POINTS,(int)points[0][0]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@AfterClass
	public static void setCloseConnectionWhenDone(){
		String script = "DELETE FROM Students WHERE ID=?";
		try {
			Database.executeSinglePreparedStatement(script,new Object[]{student.getId()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		script = "DELETE FROM Tests WHERE rowid=?";
		try {
			Database.executeSinglePreparedStatement(script,new Object[]{test.getTestid()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			Database.closeConnectionWhenDoneOperation = true;
			Database.closeConnection();
		}
	}
	
}
