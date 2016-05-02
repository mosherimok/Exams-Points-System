package tablesStructures;

import exceptions.InvalidStructure;
import tables.Table;
import tables.TableGetter;
import tables.TblDoneTests;
import tables.TblStudents;

public class DoneTest extends TableStructure{

	private Integer studentid;
	private Integer testid;
	private Integer grade;
	
	
	public int getStudentid() {
		return studentid;
	}


	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}


	public int getTestid() {
		return testid;
	}


	public void setTestid(int testid) {
		this.testid = testid;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	@Override
	public Object[] getValues() {
		return new Object[]{studentid,testid,grade};
	}


	@Override
	public void initFromArray(Object[] values) {
		if(values.length!=3)
			try {
				throw new Exception("Not enougth elements");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		studentid = (int)values[0];
		testid = (int) values[1];
		grade= (int)values[2];
	}
	
	@Override
	public Table getTableObject() {
		return TableGetter.getTable(TblDoneTests.class);
	}



	@Override
	public String getTableName() {
		return "DoneTests";
	}


	@Override
	public String[] getPrimaryKeyName() {
		return new String[]{"studentID","testID"};
	}


	@Override
	public Object[] getPrimaryKeyValue() {
		return new Object[]{studentid,testid};
	}

	
}
