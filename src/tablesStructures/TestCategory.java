package tablesStructures;

import tables.Table;
import tables.TblTestsCategories;

public class TestCategory extends TableStructure{

	private String categoryName;
	private Integer points75;
	private Integer points85;
	private Integer points95;
	
	public TestCategory(){}
	
	public TestCategory(Object[] values){
		initFromArray(values);
	}
	
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
		setCategoryName(values[0].toString());
		setPoints75((int)values[1]);
		setPoints85((int)values[2]);
		setPoints95((int)values[3]);
	}
	
	@Override
	public Table getTableObject() {
		return new TblTestsCategories();
	}

	@Override
	public String getTableName() {
		return "TestsCategories";
	}


	@Override
	public PrimaryKey getPrimaryKey() {
		return new PrimaryKey("categoryName",categoryName);
	}

}
