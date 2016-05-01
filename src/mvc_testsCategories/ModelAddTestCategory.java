package mvc_testsCategories;

import java.sql.SQLException;

import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.AddRecord;
import mvc_dialogs.Model;
import tables.TableGetter;
import tables.TblTestsCategories;

public class ModelAddTestCategory extends Model implements AddRecord{

	public ModelAddTestCategory() {
		super(TableGetter.getTable(TblTestsCategories.class));
	}

	@Override
	public void updateDatabase() throws SQLException {
		String script = DatabaseUpdatingScripts.insertInto(structure);
		DatabaseActions.executeUpdate(script);
	}

}
