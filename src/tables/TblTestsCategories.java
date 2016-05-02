package tables;

import tablesStructures.TestCategory;

public class TblTestsCategories extends Table{

	public TblTestsCategories() {
		super("TestsCategories", new String[]{"CategoryName"},"TestCategory");
	}

	@Override
	public String[] getColumnsIdentifiers() {
		return new String[]{"CategoryName","Points75","Points85","Points95"};
	}

	@Override
	public Class<?>[] getColumnsType() {
		return new Class<?>[]{String.class,int.class,int.class,int.class};
	}

	@Override
	public String[] getColumnsLabels() {
		return new String[]{"קטגורית מבחן","ניקוד עבור ציון 75-84","ניקוד עבור ציון 84-94","ניקוד עבור ציון 95 ומעלה"};
	}

	@Override
	public int getColumnsCount() {
		return 4;
	}

}
