package mvc_tests;

import java.sql.SQLException;

import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.AddRecord;
import mvc_dialogs.Model;
import tables.Table;
import tables.TableGetter;
import tables.TblTests;

public class ModelAddTest extends Model implements AddRecord{

	public ModelAddTest() {
		super(TableGetter.getTable(TblTests.class));
	}

	@Override
	public void updateDatabase() throws SQLException {
		String script = DatabaseUpdatingScripts.insertInto(structure);
		DatabaseActions.executeUpdate(script);
	}
	
}
