package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Database {

	private static Connection connection;
	
	public static boolean closeConnectionWhenDoneOperation = true;
	
	private static OperationObserver observer;
	
	private static void initConnection(){
		try {
			Class.forName(ConnectionProperties.DRIVER_CLASS);
			connection = DriverManager.getConnection(ConnectionProperties.DATABASE_EPS_ADRESS,
					ConnectionProperties.eps_properties);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(){
		try{
			if(connection!=null){
				connection.close();
				connection = null;
			}
		}
		catch(SQLException ex){ex.printStackTrace();}
	}
	
	public static synchronized void createWriteableConnection(){
		if(connection==null)
			initConnection();
	}
	
	public static Object[][] executeQuery(String sql) throws SQLException{
		createWriteableConnection();
		
		synchronized (connection) {
			
			try(Statement statement = connection.createStatement()){
				ResultSet result = statement.executeQuery(sql);
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
				if(closeConnectionWhenDoneOperation)
					closeConnection();
			}
		}
	}
	
	
	
	public static int executeUpdate(String sql){
		createWriteableConnection();
		
		synchronized(connection){
			
			try(Statement statement = connection.createStatement()){
				return statement.executeUpdate(sql);
			}
			catch(SQLException ex){
				if(ex.getMessage().equals("UNIQUE constraint failed: students.ID")){
					System.err.println("This student is already exists");
					JOptionPane.showMessageDialog(null, "קיים כבר תלמיד עם ת\"ז כזה.\n כדי להוסיף אותו מחק קודם את הקיים");
				}
				ex.printStackTrace();
				return 0;
			}
			finally{
				if(closeConnectionWhenDoneOperation)
					closeConnection();
			}
		}
		
	}
	
	public static int executeSinglePreparedStatement(String preparedSql,ArrayList<Object> values) throws SQLException{
		synchronized (connection) {
			return executeSinglePreparedStatement(preparedSql, values.toArray());
		}
	}
	
	public static int executeSinglePreparedStatement(String sql,Object[] values) throws SQLException{
		createWriteableConnection();
		
		synchronized(connection){
			
			try(PreparedStatement prepared = connection.prepareStatement(sql)){
				int affectedRows = 0;
				for(int i=0;i<values.length;i++){
					prepared.setObject(i+1,values[i]);
				}
				affectedRows+=prepared.executeUpdate();
				prepared.clearParameters();
				return affectedRows;
			}
			finally{
				if(closeConnectionWhenDoneOperation)
					closeConnection();
			}
		}
	}
	
	public static int executePreparedStatement(String sql,Object[][] values) throws SQLException{
		createWriteableConnection();
		
		synchronized(connection){
			
			try(PreparedStatement prepared = connection.prepareStatement(sql)){
				int affectedRows = 0;
				for(Object[] row:values){
					for(int i=0;i<row.length;i++){
						prepared.setObject(i+1,row[i]);
					}
					affectedRows+=prepared.executeUpdate();
					prepared.clearParameters();
				}
				return affectedRows;
			}
			finally{
				if(closeConnectionWhenDoneOperation)
					closeConnection();
			}
		}
	}
	
	public static int executeOneTransactedPreparedStatement(String sql,Object[][] values,FailedUpdatesList faileds) throws SQLException{
		createWriteableConnection();
		
		synchronized(connection){
			
			try(PreparedStatement prepared = connection.prepareStatement(sql)){
				int affectedRows = 0;
				connection.setAutoCommit(false);
				for(Object[] row:values){
					for(int i=0;i<row.length;i++){
						prepared.setObject(i+1,row[i]);
					}
					try {
						affectedRows+=prepared.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
						if(faileds!=null)
							faileds.addFailed(row, "קיימת רשומה עם מזהה כזה");
						continue;
					}
					finally {
						if(observer!=null)
							observer.action();
					}
					
					prepared.clearParameters();
				}
				connection.commit();
				return affectedRows;
			}
			finally{
				connection.rollback();
				if(closeConnectionWhenDoneOperation)
					closeConnection();
			}
		}
	}
	
	public static int executeOneTransactedPreparedStatement(String sql,Object[][] values) throws SQLException{
		return executeOneTransactedPreparedStatement(sql, values, null);
	}
	
	public static void setOperationObserver(OperationObserver observer){
		Database.observer = observer;
	}
	
}
