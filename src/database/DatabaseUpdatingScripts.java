package database;

import tablesStructures.TableStructure;

public class DatabaseUpdatingScripts {

	public static String insertInto(TableStructure structure){
		String script = "INSERT INTO " + structure.getTableName() + " VALUES(";
		for(Object obj : structure){
			script+=String.format("'%s',", obj.toString());
		}
		return script.substring(0,script.length()-1)+")"; // -2 for the last ',' and white-space.
	}
	
	/**
	 * Updates the table by the columns of the given newStructure Structure not null or initialized fields.
	 * @param newStructure Structure to use to update the table by not null or initialized attributes.
	 * @param condition Condition to check. Equal to WHERE part in sql query.
	 * @return
	 */
	public static String updateTable(TableStructure newStructure,Condition condition){
		String script = "UPDATE " + newStructure.getTableName() + " SET ";
		String[] columnsNames = newStructure.getTableObject().getColumnsIdentifiers();
		int index = 0;
		for(Object obj : newStructure){
			if(!obj.equals("null"))
				script+=String.format("%s='%s',",columnsNames[index],obj);
			index++;
		}
		script =script.substring(0, script.length()-1) + " " +condition.toString();
		return script;
	}
	
	public static String deleteFrom(String tableName,Condition condition){
		String script = "DELETE FROM " + tableName + " "+condition.toString();
		return script;
	}
	
	
	
}
