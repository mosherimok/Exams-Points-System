package database;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementHandle {

	public void handle(Statement statement) throws SQLException;
	
}
