package mv_TestsManagingDialog;

import java.sql.SQLException;

import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.AddRecord;
import mvc_dialogs.Model;

public class ModelAddTest extends Model implements AddRecord{

	public ModelAddTest() {
	}

	@Override
	public void updateDatabase() throws SQLException {
		String script = DatabaseUpdatingScripts.insertInto(structure);
		DatabaseActions.executeUpdate(script);
	}

	
}
