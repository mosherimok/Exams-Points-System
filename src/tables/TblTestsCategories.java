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
		return new String[]{"������� ����","����� ���� ���� 75-84","����� ���� ���� 84-94","����� ���� ���� 95 �����"};
	}

	@Override
	public int getColumnsCount() {
		return 4;
	}

}
