package tables;

import tablesStructures.TestCategory;

public class TblTestsCategories extends Table{

	@Override
	public String[] getColumnsIdentifiers() {
		return new String[]{"CategoryName","Points75","Points85","Points95"};
	}
	
	@Override
	public String[] getColumnsLabels() {
		return new String[]{"קטגורית מבחן","ניקוד עבור ציון 75-84","ניקוד עבור ציון 84-94","ניקוד עבור ציון 95 ומעלה"};
	}

	@Override
	public TestCategory createTableStructure() {
		return new TestCategory();
	}
	
	@Override
	public TestCategory createTableStructure(Object[] data) {
		return new TestCategory(data);
	}

}
