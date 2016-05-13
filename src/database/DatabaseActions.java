package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import tables.Table;

public abstract class DatabaseActions{

	private static Connection connection;
	private static Statement statement;
	private static boolean closeConnectionWhenDone =  true;
	
	public static boolean isCloseConnectionWhenDone() {
		return closeConnectionWhenDone;
	}

	public static void setCloseConnectionWhenDone(boolean closeConnectionWhenDone) {
		DatabaseActions.closeConnectionWhenDone = closeConnectionWhenDone;
	}

	private static void createStatement() throws SQLException{
		if(connection == null)
			connection = DatabaseConnection.getConnection();
		
		statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
	}

	
	public static synchronized void closeStuff(){
		try {
			if(statement!=null && !statement.isClosed()){
				statement.close();
				statement=null;
				System.out.println("Statement Closed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseConnection.closeConnection();
		connection = null;
		System.out.println("Database closed!");
	}
	
	public static synchronized void executeUpdate(String script) throws SQLException{
		try{
			if(statement==null)
				createStatement();
			
			statement.executeUpdate(script);
		}
		finally{
			System.out.println(script);
			if(closeConnectionWhenDone)
				closeStuff();
		}
	}
	

	public static synchronized Object[][] getAllQueryData(String script) throws SQLException{
		try{
			if(statement==null)
				createStatement();
			
			ResultSet result = statement.executeQuery(script);
			int columns = result.getMetaData().getColumnCount();
			ArrayList<Object[]> data = new ArrayList<>();
			while(result.next()){
				Object[] row = new Object[columns];
				for(int col = 0;col<columns;col++){
					row[col] = result.getObject(col+1);
				}
				data.add(row);
			}
			return Arrays.copyOf(data.toArray(),data.size(), Object[][].class);
		}
		finally{
			if(closeConnectionWhenDone)	
				closeStuff();
		}
	}
	
	public synchronized static <Structure extends tablesStructures.TableStructure> 
	Vector<Structure> getAllTableData(Table table) throws SQLException{
		
		Vector<Structure> data = new Vector<>();
		
		Object[][] records = DatabaseActions.getAllQueryData(table.getSelectAllScript());
		for (Object[] record : records){
			Structure structure = (Structure) table.createTableStructure(record);
			data.addElement(structure);
		}
		
		return data;
	}
	
	public synchronized static <T> T[] getDataOfColumnIdentifier(String tableName,String identifier,Class<T> resultClass) throws SQLException{
		String script = "SELECT " + identifier + " FROM " + tableName;
		Object[][] values = DatabaseActions.getAllQueryData(script);
		T[] columnData = (T[])java.lang.reflect.Array.newInstance(resultClass, values.length);
		for(int row=0;row<values.length;row++){
			columnData[row] = (T)values[row][0];
		}
		return columnData;
	}

}
