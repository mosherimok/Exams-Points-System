package tables;

import tablesStructures.Student;

public class TblStudents extends Table{

	@Override
	public String[] getColumnsIdentifiers() {
		return new String[]{"id","f_name","l_name","reception_year","points"};
	}

	@Override
	public String[] getColumnsLabels() {
		return new String[]{"תז","שם פרטי","שם משפחה","שנת קבלה","נקודות"};
	}

	@Override
	public Student createTableStructure() {
		return new Student();
	}
	
	@Override
	public Student createTableStructure(Object[] data) {
		return new Student(data);
	}
}
