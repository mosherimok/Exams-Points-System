package tables;

import tablesStructures.TableStructure;

public abstract class Table {
	
/*//	protected String[] primaryKey;
	protected final String structureTypeString;
	
	public Table(String tableName,String[] pkString structureTypeString){
//		primaryKey = pk;
		this.structureTypeString =  structureTypeString;
	}*/

	public String getTableName(){
		return getClass().getSimpleName().substring(3);
	}
	
	public abstract String[] getColumnsIdentifiers();
	
	public abstract Class<?>[] getColumnsType();
	
	public abstract String[] getColumnsLabels();
	
	public abstract int getColumnsCount();
	
	/*public static String[] getFormatedValues(Object[] toFormat){
		String[] formated = new String[toFormat.length];
		for(int i=0;i<formated.length;i++){
			if(toFormat[i] instanceof Integer)
				formated[i] = toFormat[i].toString();
			else
				formated[i] = "'"+toFormat[i]+"'";
		}
		return formated;
	}*/
	
//	public String[] getPrimaryKey(){
//		return primaryKey;
//	}

	public abstract TableStructure createTableStructure();
	
	public abstract TableStructure createTableStructure(Object[] data);
	
	public String[] getAllColumnsIdentifiers(){return getColumnsIdentifiers();}
	
	public String getSelectAllScript(){return "SELECT * FROM " + getTableName(); }
	
}
