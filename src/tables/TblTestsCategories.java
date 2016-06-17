package tables;

import tablesStructures.TestCategory;

public class TblTestsCategories extends Table{

	@Override
	public String[] getColumnsIdentifiers() {
		return new String[]{"CategoryName","Points75","Points85","Points95"};
	}
	
	@Override
	public String[] getColumnsLabels() {
		return new String[]{"������� ����","����� ���� ���� 75-84","����� ���� ���� 84-94","����� ���� ���� 95 �����"};
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
