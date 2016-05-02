package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseActions{

	private static DatabaseConnection dbconnection = new DatabaseConnection();
	private static Connection connection;
//	private static MiniStatement stmt;
	private static Statement stmt;
	private static int usedConnection = 0;
	
	private static void initStuff(){
		try {
			connection = dbconnection.getConnection();
//			stmt = new MiniStatement(connection.createStatement());
			stmt = connection.createStatement();
			usedConnection++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void closeStuff(){
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DB Closed");
	}
	
	public static void executeUpdate(String script) throws SQLException{
		if(usedConnection==0)
			initStuff();
		try{
			stmt.executeUpdate(script);
		}
		finally{
			System.out.println(script);
			usedConnection--;
			if(usedConnection==0)
			closeStuff();
		}
	}
	
	/*public static void executeQuery(ResultSetHandle handle){
		if(usedConnection==0)
			initStuff();
		
		try{
			ResultSet result = stmt.executeQuery(script);
			handle.handle(result);
		}
		catch(SQLException ex){ex.printStackTrace();}
		finally {
			usedConnection--;
			if(usedConnection==0)
			closeStuff();
		}
	}*/
	
	public static void executeQuery(StatementHandle handle){
		if(usedConnection==0)
			initStuff();
		try{
			handle.handle(stmt);
		}
		catch(SQLException ex){ex.printStackTrace();}
		finally {
			usedConnection--;
			if(usedConnection==0)
			closeStuff();
		}
	}
	
	/*public static synchronized void insert(final String TABLE_NAME,String[] columnsNames,Object[] values) {
			initStuff();
			String script = commonCommands.insertInto(TABLE_NAME,columnsNames,values);
			System.out.println(script);
			try {
				stmt.executeUpdate(script);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeStuff();
			}
	}
	
	public static synchronized void insert(final String TABLE_NAME,Object[] values) {
		initStuff();
		String script = commonCommands.insertInto(TABLE_NAME,values);
		System.out.println(script);
		try {
			stmt.executeUpdate(script);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeStuff();
		}
	}
	
	public static synchronized void update(final String TABLE_NAME,String[] columnsNames,Object[] values,
			String condition) {
		initStuff();
		String script = commonCommands.updateTable(TABLE_NAME,columnsNames,values,condition);
		try {
			stmt.executeUpdate(script);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeStuff();
		}
	}
	
	public static synchronized void delete(final String TABLE_NAME,String condition) {
		initStuff();
		try {
			String script = commonCommands.deleteFrom(TABLE_NAME,condition);
			stmt.executeUpdate(script);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeStuff();
		}
	}
	
	*//**
	 * 
	 * @param query Query for retrieving data from database
	 * @return
	 * @throws SQLException
	 *//*
	public static synchronized ResultSet executeQuery(String query){
		try {
			initStuff();
			ResultSet result =  stmt.executeQuery(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			closeStuff();
		}
		
	}*/
	
	/*public static synchronized void executeUpdate(String update){
		try {
			initStuff();
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeStuff();
		}
	}*/
	
	public static int getUsedConnection(){
		return usedConnection;
	}
	
}
