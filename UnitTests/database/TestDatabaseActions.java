package database;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;


public class TestDatabaseActions {

	@Test
	public void testConnectionInstances(){
		DatabaseActions.executeQuery(new StatementHandle() {
			
			@Override
			public void handle(Statement statement) throws SQLException {
				Assert.assertEquals("usedConnection must be now 1",1, DatabaseActions.getUsedConnection());
			}
		});
		Assert.assertEquals("usedConnection must be now 0", 0,DatabaseActions.getUsedConnection());
		
	}
	
}
