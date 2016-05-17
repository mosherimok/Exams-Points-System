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
import tablesStructures.DoneTest;
import ui_donetests.Grades;

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
		DatabaseActions.setCloseConnectionWhenDone(false);
	}
	
	@Before
	public void initDoneTest(){
		doneTest = new DoneTest();
		doneTest.setTestid(TEST_ID);
		doneTest.setGrade(GRADE);
		try {
			doneTest.setStudentid(Grades.getStudentID(F_NAME,L_NAME));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void gradeShouldBeAdd(){
		try {			
			Grades.addGradesToDoneTest(doneTest);
			
			String currentGradeScript = String.format("SELECT %s FROM %s WHERE %s = %d AND %s = %d",
					"grade",TABLE_NAME,"testid",TEST_ID,"StudentID",STUDENT_ID);
			
			Object[][] grade = DatabaseActions.getAllQueryData(currentGradeScript);
			System.out.println(grade.length);
			Assert.assertTrue("Grade did not add to this test",grade.length==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void deleteGrade(){
		Condition condition = new Condition();
		condition.addCondition(doneTest.getPrimaryKey());
		try {
			DatabaseActions.executeUpdate(DatabaseUpdatingScripts.deleteFrom(TABLE_NAME,condition));
			Object[][] doneTest = DatabaseActions.getAllQueryData("SELECT * FROM DoneTests WHERE " + 
								"studentID = " + STUDENT_ID);
			Assert.assertTrue("Done test didn't deleted", doneTest.length==0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void closeStuff(){
		DatabaseActions.setCloseConnectionWhenDone(true);
		DatabaseActions.closeStuff();
	}
	
}
