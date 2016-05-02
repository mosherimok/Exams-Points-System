package mvc_students;

import java.sql.SQLException;

import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.Model;
import mvc_dialogs.ModifyRecord;
import tables.TableGetter;
import tables.TblStudents;
import tablesStructures.Student;

public class ModelModifyStudent extends Model implements ModifyRecord{

	private Condition condition = new Condition();
	
	public ModelModifyStudent(Student oldStudent) {
		super(TableGetter.getTable(TblStudents.class));
		setStructure(oldStudent);
		condition.addCondition(oldStudent.getPrimaryKeyName()[0], oldStudent.getPrimaryKeyValue()[0]);
		
	}

	@Override
	public void updateDatabase() throws SQLException {
		String script = DatabaseUpdatingScripts.updateTable(structure, condition);
		DatabaseActions.executeUpdate(script);
	}
	
	

}
