package mvc_students;

import java.sql.SQLException;

import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.AddRecord;
import mvc_dialogs.Model;
import tables.TableGetter;
import tables.TblStudents;
import tablesStructures.Student;

public class ModelAddStudent extends Model implements AddRecord{
	
	public ModelAddStudent() {
		super(TableGetter.getTable(TblStudents.class));
		structure = new Student();
	}

	@Override
	public void updateDatabase() throws SQLException {
		String script = DatabaseUpdatingScripts.insertInto(structure);
		DatabaseActions.executeUpdate(script);
	}

}
