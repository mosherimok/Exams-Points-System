package mv_StudentManagingDialog;

import java.sql.SQLException;

import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.Model;
import mvc_dialogs.ModifyRecord;
import tablesStructures.Student;

public class ModelModifyStudent extends Model implements ModifyRecord{

	private Condition condition = new Condition();
	
	public ModelModifyStudent(Student oldStudent) {
		setStructure(oldStudent);
//		condition.addCondition(oldStudent.getPrimaryKeyName()[0], oldStudent.getPrimaryKeyValue()[0]);
		condition.addCondition(oldStudent.getPrimaryKey());
	}

	@Override
	public void updateDatabase() throws SQLException {
		String script = DatabaseUpdatingScripts.updateTable(structure, condition);
		DatabaseActions.executeUpdate(script);
	}
	
	

}
