package database;

import java.sql.SQLException;
import java.sql.Statement;

public class MiniStatement {

	private Statement statement;
	
	public MiniStatement(Statement statement){
		this.statement = statement;
		
	}
	
	public MiniResultSet executeQuery(String sql) throws SQLException{
		return new MiniResultSet(statement.executeQuery(sql));
	}
	
	public int executeUpdate(String sql) throws SQLException{
		return statement.executeUpdate(sql);
	}
	
	public void close() throws SQLException{
		statement.close();
	}
	
	
	

	
}
