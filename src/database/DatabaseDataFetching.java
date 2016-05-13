package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import exceptions.InvalidStructure;
import tables.Table;
import tablesStructures.TableStructure;

public class DatabaseDataFetching {

	/*public static <Structure extends tablesStructures.TableStructure> 
					Vector<Structure> getAllTableData(Table table){
		Vector<Structure> data = new Vector<>();
			//TODO:improvement in retreiving table name.
			String script = table.getSelectAllScript();
			StatementHandle handle = new StatementHandle() {
				
				@Override
				public void handle(Statement stmt) throws SQLException {
					ResultSet result = stmt.executeQuery(script);
					while(result.next()){
						int columns = result.getMetaData().getColumnCount();
						Object[] values = new Object[columns];
						for(int i=0;i<columns;i++){
							values[i] = result.getObject(i+1);
						}
						Structure structure;
						structure = (Structure) table.createTableStructure(values);
						data.addElement(structure);

						
					}
				}
			};
			DatabaseActions.executeQuery(handle);
		return data;
	}*/
	
	/*public static <Structure extends tablesStructures.TableStructure> 
	Vector<Structure> getAllTableData(Table table) throws SQLException{
		
		Vector<Structure> data = new Vector<>();
		
		Object[][] records = DatabaseActions.getAllQueryData(table.getSelectAllScript());
		for (Object[] record : records){
			Structure structure = (Structure) table.createTableStructure(record);
			data.addElement(structure);
		}
		
		return data;
	}
	
	
	
	public static <T> T[] getDataOfColumnIdentifier(String tableName,String identifier,Class<T> resultClass) throws SQLException{
//		ArrayList<T> columnData = new ArrayList<>();
		String script = "SELECT " + identifier + " FROM " + tableName;
		Object[][] values = DatabaseActions.getAllQueryData(script);
		T[] columnData = (T[])java.lang.reflect.Array.newInstance(resultClass, values.length);
		for(int row=0;row<values.length;row++){
			columnData[row] = (T)values[row][0];
		}
		return columnData;
//		return values.toArray((T[])java.lang.reflect.Array.newInstance(resultClass, values.size()));
	}*/
	
}
