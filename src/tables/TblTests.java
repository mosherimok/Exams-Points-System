package tables;

import tablesStructures.Test;

public class TblTests extends Table{

	/*public TblTests() {
		super("Tests",new String[]{"testid"},"Test");
	}*/

	/**
	 * @return Columns needed for inserting new record.<br> <code>rowid</code> is not a column,
	 * but an attribute exists in every table, which represents the number of the row.
	 */
	@Override
	public String[] getColumnsIdentifiers() {
		return new String[]{"name","category","testDate"};
	}
	
	@Override 
	public String[] getAllColumnsIdentifiers(){
		return new String[]{"rowid","name","category","testDate"};
	}

	@Override
	public String[] getColumnsLabels() {
		return new String[]{"מזהה מבחן","שם מבחן","קטגורית מבחן","תאריך מבחן"};
	}

	@Override
	public Test createTableStructure() {
		return new Test();
	}
	
	@Override
	public Test createTableStructure(Object[] data) {
		return new Test(data);
	}
	
	@Override
	public String getSelectAllScript(){
		return "SELECT rowid,* FROM Tests";
	}
}
