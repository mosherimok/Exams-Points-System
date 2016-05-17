package mv_TestsManagingDialog;

import java.sql.SQLException;

import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.Model;
import mvc_dialogs.ModifyRecord;
import tablesStructures.Test;

public class ModelModifyTest extends Model implements ModifyRecord{

	private Condition condition = new Condition();
	
	public ModelModifyTest(Test oldTest) {
		setStructure(oldTest);
		condition.addCondition(oldTest.getPrimaryKey());
//		String[] primnames = oldTest.getPrimaryKeyName();
//		Object[] primvals = oldTest.getPrimaryKeyValue();
//		condition.addCondition(primnames[0], primvals[0]);
//		condition.addCondition(primnames[1], primvals[1]);
//		condition.addCondition(primnames[2], primvals[2]);
	}

	@Override
	public void updateDatabase() throws SQLException {
		String script = DatabaseUpdatingScripts.updateTable(structure, condition);
		DatabaseActions.executeUpdate(script);
	}

}
