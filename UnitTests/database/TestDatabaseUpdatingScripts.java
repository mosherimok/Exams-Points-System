package database;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import tablesStructures.Student;

public class TestDatabaseUpdatingScripts {

	private final String EXPECTED_INSERT = "INSERT INTO Students VALUES('213931777','Yehoda','Polak','2015','25')";
	private final String EXPECTED_UPDATE = "UPDATE Students SET id='213931777',f_name='Gershon',l_name='Polak',reception_year='2015',points='25' WHERE id='213931777'";
	private final String EXPECTED_UPDATE_BY_COLUMNS = "UPDATE Students SET id='213931777',points='25' WHERE id='213931777'";
	private final String EXPECTED_DELETE = "DELETE FROM Students WHERE f_name='Gershon'";
	
	private static Student student;
	
	@BeforeClass
	public static void initStudent(){
		student = new Student();
		student.setId(213931777);
		student.setFirstName("Yehoda");
		student.setLastName("Polak");
		student.setReceptionYear((short)2015);
		student.setPoints(25);
	}
	
	@Test
	public void testInsertInto(){
		student.setFirstName("Yehoda");
		String script = DatabaseUpdatingScripts.insertInto(student);
		Assert.assertEquals("Script is not valid",EXPECTED_INSERT,script);
	}
	
	@Test
	public void testUpdateTable(){
		student.setFirstName("Gershon");
		Condition condition = new Condition(student.getPrimaryKeyValue());
//		condition.addCondition(student.getPrimaryKeyName()[0], student.getPrimaryKeyValue()[0]);
		String script = DatabaseUpdatingScripts.updateTable(student, condition);
		Assert.assertEquals("Script is not valid",EXPECTED_UPDATE,script);
	}
	
	@Test
	public void testUpdateTableByColums(){
		Student student = new Student();
		student.setPoints(25);
		student.setId(213931777);
		Condition condition = new Condition(student.getPrimaryKeyValue());
//		condition.addCondition(student.getPrimaryKeyName()[0],"213931777");
		String script = DatabaseUpdatingScripts.updateTable(student, condition);
		Assert.assertEquals("Script is not valid",EXPECTED_UPDATE_BY_COLUMNS,script);
	}
	
	@Test
	public void testDeleteFrom(){
		Condition condition = new Condition();
		condition.addCondition("f_name", "Gershon");
		String script = DatabaseUpdatingScripts.deleteFrom(student.getTableName(), condition);
		Assert.assertEquals("Script is not valid",EXPECTED_DELETE,script);
	}
	
}
