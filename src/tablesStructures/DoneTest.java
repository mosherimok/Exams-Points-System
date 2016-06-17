package tablesStructures;

import tables.Table;
//import tables.TableGetter;
import tables.TblDoneTests;

public class DoneTest extends TableStructure{

	private Integer studentid;
	private Integer testid;
	private Integer grade;
	
	public DoneTest(){}
		
	public DoneTest(Integer studentid, Integer testid, Integer grade) {
		setStudentid(studentid);
		setTestid(testid);
		setGrade(grade);
	}

	public DoneTest(Object[] values){
		initFromArray(values);
	}
	
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
		setStudentid((int)values[0]);
		setTestid((int) values[1]);
		setGrade((int)values[2]);
	}
	
	@Override
	public Table getTableObject() {
		return new TblDoneTests();
	}



	@Override
	public String getTableName() {
		return "DoneTests";
	}

	@Override
	public PrimaryKey getPrimaryKeyValue() {
		PrimaryKey prim = new PrimaryKey();
		try {
			prim.addKey("studentID", studentid);
			prim.addKey("testID", testid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prim;
	}
	
	

	
}
