package tables;

import tablesStructures.TableStructure;

public abstract class Table {

	public String getTableName(){
		return getClass().getSimpleName().substring(3);
	}
	
	public abstract String[] getColumnsIdentifiers();
	
	public abstract String[] getColumnsLabels();
	
	public abstract TableStructure createTableStructure();
	
	public abstract TableStructure createTableStructure(Object[] data);
	
	public String[] getAllColumnsIdentifiers(){return getColumnsIdentifiers();}
	
	public String getSelectAllScript(){return "SELECT * FROM " + getTableName(); }
	
}
