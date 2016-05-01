package tables;

import java.util.Date;

import tablesStructures.Test;

public class TblTests extends Table{

	public TblTests() {
		super("Tests",new String[]{"testid"},"Test");
	}

	@Override
	public String[] getColumnsIdentifiers() {
		return new String[]{"testid","name","category","testDate"};
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
	
}
