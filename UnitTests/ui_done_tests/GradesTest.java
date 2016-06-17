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
import database.StudentUtility;
import tablesStructures.DoneTest;

public class GradesTest {

	private final String TABLE_NAME = "DoneTests";
	private final int TEST_ID = 1;
	private final String F_NAME = "Moshe";
	private final String L_NAME = "Rimok";
	private final int STUDENT_ID = 318358587;
	private final int GRADE = 95;
	private DoneTest doneTest;
	
	@BeforeClass
	public static void setCloseConnectionWhenDone(){
		Database.closeConnectionWhenDoneOperation = false;
	}
	
	@Before
	public void initDoneTest(){
		doneTest = new DoneTest();
		doneTest.setTestid(TEST_ID);
		doneTest.setGrade(GRADE);
		try {
			doneTest.setStudentid(StudentUtility.getStudentIDbyFullName(F_NAME,L_NAME));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void gradeShouldBeAdd(){
		try {			
			DoneTestUtilities.addDoneTest(doneTest);
			
			String currentGradeScript = String.format("SELECT %s FROM %s WHERE %s = %d AND %s = %d",
					"grade",TABLE_NAME,"testid",TEST_ID,"StudentID",STUDENT_ID);
			
			Object[][] grade = Database.executeQuery(currentGradeScript);
			System.out.println(grade.length);
			Assert.assertTrue("Grade did not add to this test",grade.length==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void deleteGrade(){
		Condition condition = new Condition();
		condition.addCondition(doneTest.getPrimaryKeyValue());
		try {
			Database.executeQuery(DatabaseUpdatingScripts.deleteFrom(TABLE_NAME,condition));
			Object[][] doneTest = Database.executeQuery("SELECT * FROM DoneTests WHERE " + 
								"studentID = " + STUDENT_ID);
			Assert.assertTrue("Done test didn't deleted", doneTest.length==0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void closeStuff(){
		Database.closeConnectionWhenDoneOperation = true;
		Database.closeConnection();
	}
	
}
