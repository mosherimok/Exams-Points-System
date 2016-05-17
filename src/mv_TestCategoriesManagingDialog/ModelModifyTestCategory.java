package mv_TestCategoriesManagingDialog;

import java.sql.SQLException;

import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.Model;
import mvc_dialogs.ModifyRecord;
import tablesStructures.TestCategory;

public class ModelModifyTestCategory extends Model implements ModifyRecord{

	private Condition condition = new Condition();

	
	public ModelModifyTestCategory(TestCategory tc) {
		setStructure(tc);
//		condition.addCondition(tc.getPrimaryKeyName()[0], tc.getPrimaryKeyValue()[0]);
		condition.addCondition(tc.getPrimaryKey());
	}

	@Override
	public void updateDatabase() throws SQLException {
		String script = DatabaseUpdatingScripts.updateTable(structure, condition);
		DatabaseActions.executeUpdate(script);
	}

	
	
}
