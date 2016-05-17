package tablesStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tables.Table;
import tables.TblTests;

public class Test extends TableStructure{
	//IMPORTANT:TODO: Change the pk in this table in mysql. name,category and testdate
	// should be the right pk.
	private Integer testid; // "rowid" in sqlite.
	private String name;
	private String category;
	private String testDate;
	
	public Test(){}
	
	public Test(Object[] values){
		initFromArray(values);
	}
	
	public Integer getTestid() {
		return testid;
	}

	public void setTestid(Integer testid) {
		this.testid = testid;
	}

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
		if(values.length<3)
			try {
				throw new Exception("Not enougth values");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		if(values.length==4){
			testid=(int)values[0];
			setName(values[1].toString());
			setCategory(values[2].toString());
			setTestDate(values[3].toString());
		}
		else{
			setName(values[0].toString());
			setCategory(values[1].toString());
			setTestDate(values[2].toString());
		}
	}

	@Override
	public Table getTableObject() {
		return new TblTests();
	}
	

	@Override
	public String getTableName() {
		return "Tests";
	}

	@Override
	public PrimaryKey getPrimaryKey() {
		PrimaryKey prim = new PrimaryKey();
		prim.addKey("category", category);
		prim.addKey("testDate", testDate);
		return prim;
	}
	
	@Override
	public Iterator<Object> iterator() {
		return new Iterator<Object>() {
			Object[] values = getValues();
			int index= 1;
			@Override
			public Object next() {
				if(index==values.length)
					throw new NoSuchElementException("No more elements!");
				return values[index++];
			}
			
			@Override
			public boolean hasNext() {
				return index<values.length;
			}
		};
	}
	
}
