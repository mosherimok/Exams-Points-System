package tables;

import java.util.Date;

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
	public Class<?>[] getColumnsType() {
		return new Class<?>[]{int.class,String.class,String.class,Date.class};
	}

	@Override
	public String[] getColumnsLabels() {
		return new String[]{"מזהה מבחן","שם מבחן","קטגורית מבחן","תאריך מבחן"};
	}

	@Override
	public int getColumnsCount() {
		return 4;
	}

	@Override
	public Test createTableStructure() {
		return new Test();
	}
	
	@Override
	public Test createTableStructure(Object[] data) {
		return new Test(data);
	}
	
	/*@Override
	public String getSelectAllScript() {
		return "SELECT rowid,* FROM Tests limit 5";
	}*/
	
	@Override
	public String getSelectAllScript(){
		return "SELECT rowid,* FROM Tests";
	}
}
