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

	public static <Structure extends tablesStructures.TableStructure> 
					Vector<Structure> getAllTableData(Table table){
		Vector<Structure> data = new Vector<>();
			//TODO:improvement in retreiving table name.
			String script = "SELECT * FROM " + table.getTableName();
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
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] getDataOfColumnIdentifier(String tableName,String identifier,Class<T> resultClass) throws SQLException{
		ArrayList<T> values = new ArrayList<>();
		String script = "SELECT " + identifier + " FROM " + tableName;
		StatementHandle handle = new StatementHandle() {
			@Override
			public void handle(Statement stmt) throws SQLException {
/*				result.getStatement().executeQuery("SELECT COUNT("+identifier+") " +"FROM " + tableName);
				result.next();
				int length = result.getInt(1);
				
				result.close();
				*/
//				values = (T[]) java.lang.reflect.Array.newInstance(resultClass, length);
				ResultSet result = stmt.executeQuery(script);
				while(result.next())
					values.add((T) result.getObject(1));
			}
		};
		
		DatabaseActions.executeQuery(handle);
		return values.toArray((T[])java.lang.reflect.Array.newInstance(resultClass, values.size()));
	}
	
}
