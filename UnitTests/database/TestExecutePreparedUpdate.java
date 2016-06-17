package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestExecutePreparedUpdate {

	private final static int MAX_RECORDS = 5000;
	private final static int ID = 123456789;
	private final static String NAME = "MOSHE";
	
	private final static String CREATE_TABLE_SCRIPT="CREATE TABLE IF NOT EXISTS TESTING(ID integer, Name varchar(10))";
	private final static String PREPARED_INSERT_SCRIPT="INSERT INTO TESTING VALUES(?,?)";
	private final static String SIMPLE_INSERT_STATEMENT = String.format("INSERT INTO TESTING Values('%d','%s')",ID,NAME);
	
	
	private static Connection connection;
	private static PreparedStatement prestmt=null;
	private static Statement stmt=null;
	
	@Before
	public void initStuff() throws SQLException{
//		connection = DatabaseConnection.getConnection();
		
		stmt = connection.createStatement();
		
		stmt.executeUpdate(CREATE_TABLE_SCRIPT);
		
		prestmt = connection.prepareStatement(PREPARED_INSERT_SCRIPT);
	}
	
	@After
	public void closeStuff(){
		try{
			if(stmt!=null){
				stmt.executeUpdate("DELETE FROM TESTING");
				stmt.close();
			}
		}
		catch(SQLException ex){ex.printStackTrace();}
		try{
			if(prestmt!=null){
				prestmt.close();
			}
		}
		catch(SQLException ex){ex.printStackTrace();}
		
//		DatabaseActions.closeStuff();
	}
	
	@Test
	public void wayA(){
		System.out.println("IN A");
		try{
			long started = System.currentTimeMillis();
			for(int i=0;i<MAX_RECORDS;i++){
				stmt.executeUpdate(SIMPLE_INSERT_STATEMENT);
			}
			long ended = System.currentTimeMillis();
			System.out.println("Finshed with insertions in: " + (ended - started));
		}
		catch(SQLException ex){ex.printStackTrace();}
		System.out.println("A FINSHED");
	}
	
	@Test
	public void wayB(){
		System.out.println("IN B");
		try{
			long started = System.currentTimeMillis();
			
			for(int i=0;i<MAX_RECORDS;i++){
				prestmt.setInt(1, ID);
				prestmt.setString(1,NAME);
				prestmt.executeUpdate();
			}
			
			long ended = System.currentTimeMillis();
			System.out.println("Finshed with insertions in: " + (ended - started));
		}catch(SQLException ex){ex.printStackTrace();}
		System.out.println("B FINSHED");
	}
	
	@Test
	public void wayC(){
		System.out.println("IN C");
		try{
			long started = System.currentTimeMillis();
			connection.setAutoCommit(false);
			
			for(int i=0;i<MAX_RECORDS;i++){
				prestmt.setInt(1, ID);
				prestmt.setString(1,NAME);
				prestmt.executeUpdate();
			}
			connection.commit();
			
			connection.setAutoCommit(true);
			
			long ended = System.currentTimeMillis();
			System.out.println("Finshed with insertions in: " + (ended - started));
		}
		catch(SQLException ex){ex.printStackTrace();}
		System.out.println("C FINSHED");
	}
	
}
