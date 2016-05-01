package tablesStructures;

import exceptions.InvalidStructure;
import tables.Table;
import tables.TableGetter;
import tables.TblStudents;
import tables.TblTestsCategories;

public class TestCategory extends TableStructure{

	private String categoryName;
	private Integer points75;
	private Integer points85;
	private Integer points95;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String name) {
		this.categoryName = name;
	}
	public int getPoints75() {
		return points75;
	}
	public void setPoints75(int points75) {
		this.points75 = points75;
	}
	public int getPoints85() {
		return points85;
	}
	public void setPoints85(int points85) {
		this.points85 = points85;
	}
	public int getPoints95() {
		return points95;
	}
	public void setPoints95(int points95) {
		this.points95 = points95;
	}
	@Override
	public Object[] getValues() {
		return new Object[]{categoryName,points75,points85,points95};
	}
	@Override
	public void initFromArray(Object[] values) {
		if(values.length!=4)
			try {
				throw new Exception("Not enougth elements");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		categoryName = values[0].toString();
		points75 = (int)values[1];
		points85= (int)values[2];
		points95 = (int)values[3];
	}
	
	@Override
	public Table getTableObject() {
		return TableGetter.getTable(TblTestsCategories.class);
	}

	@Override
	public String getTableName() {
		return "TestsCategories";
	}
	@Override
	public String[] getPrimaryKeyName() {
		return new String[]{"categoryName"};
	}
	@Override
	public Object[] getPrimaryKeyValue() {
		return new String[]{categoryName};
	}

}
