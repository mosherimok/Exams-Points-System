package mv_StudentManagingDialog;

import java.sql.SQLException;

import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.AddRecord;
import mvc_dialogs.Model;

public class ModelAddStudent extends Model implements AddRecord{
	

	@Override
	public void updateDatabase() throws SQLException {
		String script = DatabaseUpdatingScripts.insertInto(structure);
		DatabaseActions.executeUpdate(script);
	}

}
