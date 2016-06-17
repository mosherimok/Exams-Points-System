package database;

import java.sql.SQLException;
import java.util.Vector;

import tables.Table;

public class DatabaseUtilities {
	
	public static <Structure extends tablesStructures.TableStructure> 
	Vector<Structure> getAllTableData(Table table) throws SQLException{
		
		Vector<Structure> data = new Vector<>();
		
		Object[][] records = Database.executeQuery(table.getSelectAllScript());
		for (Object[] record : records){
			Structure structure = (Structure) table.createTableStructure(record);
			data.addElement(structure);
		}
		
		return data;
	}
	
	public static <T> T[] getDataOfColumnIdentifier(String tableName,String identifier,Class<T> resultClass) throws SQLException{
		String script = "SELECT " + identifier + " FROM " + tableName;
		Object[][] values = Database.executeQuery(script);
		T[] columnData = (T[])java.lang.reflect.Array.newInstance(resultClass, values.length);
		for(int row=0;row<values.length;row++){
			columnData[row] = (T)values[row][0];
		}
		return columnData;
	}

}
