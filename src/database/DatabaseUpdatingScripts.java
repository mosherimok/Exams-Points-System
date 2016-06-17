package database;

import java.sql.PreparedStatement;

import tables.Table;
import tablesStructures.TableStructure;

public class DatabaseUpdatingScripts {

	public static String insertIntoPreparedStatementScript(TableStructure structure){
		String script = "INSERT INTO " + structure.getTableName() + "(";
		Table table = structure.getTableObject();
		
		for(int i = 0;i<table.getAllColumnsIdentifiers().length;i++){
			script+= table.getAllColumnsIdentifiers()[i];
			if (i!=table.getAllColumnsIdentifiers().length-1)
				script+=',';
		}
		
		script += ") VALUES(";
		for (int i = 0; i < structure.getValues().length; i++) {
			script+="?";
			if (i!=structure.getValues().length-1)
				script+=",";
		}
		
		script+= ")";
		
		return script;
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
			if(obj!=null){
				System.out.println(index);
				script+=String.format("%s='%s',",columnsNames[index],obj.toString());
			}

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
