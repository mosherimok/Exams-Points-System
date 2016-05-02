package tables;

import tablesStructures.TableStructure;

public abstract class Table {
	
	protected final String TABLE_NAME;
	protected String[] primaryKey;
	protected final String structureTypeString;
	
	public Table(String tableName,String[] pk,String structureTypeString){
		TABLE_NAME = tableName;
		primaryKey = pk;
		this.structureTypeString =  structureTypeString;
	}

	public String getTableName(){
		return TABLE_NAME;
	}
	
	public abstract String[] getColumnsIdentifiers();
	
	public abstract Class<?>[] getColumnsType();
	
	public abstract String[] getColumnsLabels();
	
	public abstract int getColumnsCount();
	
	public static String[] getFormatedValues(Object[] toFormat){
		String[] formated = new String[toFormat.length];
		for(int i=0;i<formated.length;i++){
			if(toFormat[i] instanceof Integer)
				formated[i] = toFormat[i].toString();
			else
				formated[i] = "'"+toFormat[i]+"'";
		}
		return formated;
	}
	
	public String[] getPrimaryKey(){
		return primaryKey;
	}

	public String getTableStructureName(){
		return structureTypeString;
	}
	
}
