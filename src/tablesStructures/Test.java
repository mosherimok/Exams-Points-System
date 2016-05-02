package tablesStructures;

import tables.Table;
import tables.TableGetter;
import tables.TblTests;

public class Test extends TableStructure{
	//IMPORTANT:TODO: Change the pk in this table in mysql. name,category and testdate
	// should be the right pk.
	private Integer testid; // auto_increment.
	private String name;
	private String category;
	private String testDate;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public Object[] getValues() {
		return new Object[]{testid,name,category,testDate};
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
		testid = (int)values[0];
		name = values[1].toString();
		category=values[2].toString();
		testDate = values[3].toString();
	}

	@Override
	public Table getTableObject() {
		return TableGetter.getTable(TblTests.class);
	}
	

	@Override
	public String getTableName() {
		return "Tests";
	}

	@Override
	public String[] getPrimaryKeyName() {
		return new String[]{"name","Category","testDate"};
	}

	@Override
	public Object[] getPrimaryKeyValue() {
		return new Object[]{name,category,testDate};
	}
	
}
